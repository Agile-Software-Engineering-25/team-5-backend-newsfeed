package com.ase.userservice.controllers;

import com.ase.userservice.components.NewsPost;
import com.ase.userservice.components.NewsPostHistoryItemDto;
import com.ase.userservice.services.NewsPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RootController {

  private final NewsPostService service;

  @GetMapping("/")
  public ResponseEntity<String> root() {
    return ResponseEntity.ok("API Root: /newsfeed");
  }

  @PutMapping("/newsfeed")
  public ResponseEntity<NewsPost> create(@RequestBody NewsPost newsPost) {
    NewsPost saved = service.saveNewsPost(newsPost);
    return ResponseEntity.ok(saved);
  }

  @GetMapping("/newsfeed")
  public ResponseEntity<List<NewsPost>> list(@RequestParam(required = false) String filter) {
    return ResponseEntity.ok(service.getAllNewsPosts(filter));
  }

  @PutMapping("/newsfeed/{id}")
  public ResponseEntity<NewsPost> update(@PathVariable String id, @RequestBody NewsPost newsPost) {
    newsPost.setId(id); // Ensure the ID matches the path
    NewsPost updated = service.updateNewsPost(newsPost);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/newsfeed/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    service.deleteNewsPost(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/newsfeed/{id}/history")
  public ResponseEntity<List<NewsPostHistoryItemDto>> history(@PathVariable String id) {
    return ResponseEntity.ok(service.getNewsPostHistory(id));
  }
}
