package com.ase.newsfeedservice.controllers;

import com.ase.newsfeedservice.components.NewsPost;
import com.ase.newsfeedservice.components.NewsPostHistoryItemDto;
import com.ase.newsfeedservice.services.NewsPostService;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
<<<<<<< HEAD

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
<<<<<<< HEAD
<<<<<<< HEAD

=======
=======

import org.springframework.data.domain.Page;
>>>>>>> c7a96a8 (cleaned up merge conflict)
import org.springframework.format.annotation.DateTimeFormat;
>>>>>>> 5aeb80b (init)
=======
import org.springframework.format.annotation.DateTimeFormat;
>>>>>>> ebbc246 (init)
=======

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
>>>>>>> ee8f946 (fixed errors)
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.OffsetDateTime;
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
  public List<NewsPost> list(
<<<<<<< HEAD
      // Optional text search on title and summary
      @RequestParam(required = false) String query,

      // Optional start of the date range
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,

      // Optional end of the date range
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to,

      // Page number, REQUIRED
      @RequestParam int page,

      // Number of items per page, REQUIRED
      @RequestParam int pageSize) {

    int zeroBasedPage = page > 0 ? page - 1 : 0;
    Page<NewsPost> newsPage = service.listNewsPosts(query, from, to, zeroBasedPage, pageSize);
    return newsPage.getContent();
=======
    // Optional text search on title and summary
    @RequestParam(required = false) String query,

    // Optional start of the date range
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,

    // Optional end of the date range
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to,

    // Page number, REQUIRED
    @RequestParam int page,

    // Number of items per page, REQUIRED
    @RequestParam int pageSize
  ) {
<<<<<<< HEAD
    return newsPostService.listNewsPosts(query, from, to, page, pageSize);
>>>>>>> 5aeb80b (init)
=======
    return service.listNewsPosts(query, from, to, page, pageSize);
>>>>>>> 00005be (fixed errors)
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
