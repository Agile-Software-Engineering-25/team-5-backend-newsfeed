package com.ase.userservice.controllers;

import com.ase.userservice.components.NewsPost;
import com.ase.userservice.services.NewsPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class RootController {
  private final NewsPostService service;

  public RootController(NewsPostService service) {
    this.service = service;
  }

	@GetMapping("/")
	public ResponseEntity<String> root() {
		return ResponseEntity.ok("API Root: /api/blogposts/");
	}

  @GetMapping("/api/newsposts")
  public ResponseEntity<List<NewsPost>> getAllBlogPosts() {
    return ResponseEntity.ok(service.getAllNewsPosts());
  }

  @PutMapping("/api/newsposts")
  public ResponseEntity<NewsPost> createNewsPost(@RequestBody NewsPost newsPost) {
    NewsPost saved = service.saveNewsPost(newsPost);
    return ResponseEntity.ok(saved);
  }

  @DeleteMapping("/api/newsposts/{id}")
  public ResponseEntity<Void> deleteNewsPost(@PathVariable String id) {
    service.deleteNewsPost(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/api/newsposts/{id}")
  public ResponseEntity<NewsPost> updateNewsPost(@PathVariable String id, @RequestBody NewsPost newsPost) {
    newsPost.setId(id); // Ensure the ID matches the path
    NewsPost updated = service.saveNewsPost(newsPost);
    return ResponseEntity.ok(updated);
  }
}
