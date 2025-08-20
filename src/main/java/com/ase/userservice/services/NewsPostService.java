package com.ase.userservice.services;

import com.ase.userservice.components.NewsPost;
import com.ase.userservice.repositories.NewsPostRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NewsPostService {
  private final NewsPostRepository repository;

  public NewsPostService(NewsPostRepository repository) {
    this.repository = repository;
  }

  public List<NewsPost> getAllNewsPosts() {
    return repository.findAll();
  }

  public NewsPost saveNewsPost(NewsPost newsPost) {
    return repository.save(newsPost);
  }

  public void deleteNewsPost(String id) {
    repository.deleteById(id);
  }
}
