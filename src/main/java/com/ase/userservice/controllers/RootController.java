package com.ase.userservice.controllers;

import com.ase.userservice.components.BlogPost;
import com.ase.userservice.services.BlogPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class RootController {
  private final BlogPostService service;

  public RootController(BlogPostService service) {
    this.service = service;
  }

	@GetMapping("/")
	public ResponseEntity<String> root() {
		return ResponseEntity.ok("API Root: /api/v1/");
	}

  @GetMapping("/api/blogposts")
  public ResponseEntity<List<BlogPost>> getAllBlogPosts() {
    return ResponseEntity.ok(service.getAllBlogPosts());
  }

  @PutMapping("/api/blogposts")
  public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPost blogPost) {
    BlogPost saved = service.saveBlogPost(blogPost);
    return ResponseEntity.ok(saved);
  }
}
