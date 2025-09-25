package com.ase.newsfeedservice.services;

import com.ase.newsfeedservice.components.NewsPost;
import com.ase.newsfeedservice.components.NewsPostHistoryItemDto;
import com.ase.newsfeedservice.exceptions.NewsPostNotFoundException;
import com.ase.newsfeedservice.repositories.NewsPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NewsPostService {

  private final NewsPostRepository repository;

  @Transactional(readOnly = true)
  public Page<NewsPost> listNewsPosts(String query, OffsetDateTime from, OffsetDateTime to, int page, int pageSize) {
    Pageable pageable = PageRequest.of(page, pageSize);
    return repository.listNewsPosts(query, from, to, pageable);
  }

  @Transactional
  public NewsPost saveNewsPost(NewsPost newsPost) {
    return repository.save(newsPost);
  }

  @Transactional
  public NewsPost updateNewsPost(NewsPost newsPost) {
    Objects.requireNonNull(newsPost.getId(), "newsPost.id must not be null");

    NewsPost existingPost = repository.findById(newsPost.getId())
        .orElseThrow(() -> new NewsPostNotFoundException(newsPost.getId()));

    // Achtung: überschreibt auch mit null -> ok, wenn ihr "vollständige Updates" wollt
    BeanUtils.copyProperties(newsPost, existingPost, "id", "version");

    return repository.save(existingPost);
  }

  @Transactional
  public void deleteNewsPost(String id) {
    repository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<NewsPostHistoryItemDto> getNewsPostHistory(String id) {
    return List.of();
  }
}
