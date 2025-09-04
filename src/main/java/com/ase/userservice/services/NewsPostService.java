package com.ase.userservice.services;

import com.ase.userservice.components.NewsPost;
import com.ase.userservice.components.NewsPostHistoryItemDto;
import com.ase.userservice.repositories.NewsPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsPostService {

  private final NewsPostRepository repository;

  public List<NewsPost> getAllNewsPosts(String filter) {
    return repository.findAll();
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
