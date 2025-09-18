package com.ase.userservice.services;

import com.ase.userservice.components.NewsPost;
import com.ase.userservice.components.NewsPostHistoryItemDto;
import com.ase.userservice.repositories.NewsPostRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsPostService {

  private final NewsPostRepository repository;

  public Page<NewsPost> listNewsPosts(String query, OffsetDateTime from, OffsetDateTime to, int page, int pageSize) {
    Pageable pageable = PageRequest.of(page, pageSize);
    return repository.listNewsPosts(query, from, to, pageable);
  }

  public NewsPost saveNewsPost(NewsPost newsPost) {
    return repository.save(newsPost);
  }

  public NewsPost updateNewsPost(NewsPost newsPost) {
    NewsPost existingPost = repository.findById(newsPost.getId())
        .orElseThrow(() -> new RuntimeException("NewsPost not found"));
    BeanUtils.copyProperties(newsPost, existingPost, "id", "version");
    return repository.save(existingPost);
  }

  public void deleteNewsPost(String id) {
    repository.deleteById(id);
  }

  public List<NewsPostHistoryItemDto> getNewsPostHistory(String id) {
    return List.of();
  }
}
