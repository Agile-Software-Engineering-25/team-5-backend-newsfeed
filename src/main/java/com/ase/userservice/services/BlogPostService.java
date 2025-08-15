package com.ase.userservice.services;

import com.ase.userservice.components.BlogPost;
import com.ase.userservice.repositories.BlogPostRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogPostService {
  private final BlogPostRepository repository;

  public BlogPostService(BlogPostRepository repository) {
    this.repository = repository;
  }

  public List<BlogPost> getAllBlogPosts() {
    return repository.findAll();
  }

  public BlogPost saveBlogPost(BlogPost blogPost) {
    return repository.save(blogPost);
  }

  public void deleteBlogPost(String id) {
    repository.deleteById(id);
  }
}
