package com.ase.newsfeedservice.controllers;

import com.ase.newsfeedservice.components.NewsPost;
import com.ase.newsfeedservice.components.NewsPostRevisionDto;
import com.ase.newsfeedservice.services.NewsPostService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RootController {

  private final NewsPostService service;

  @GetMapping("/")
  public ResponseEntity<String> root() {
    return ResponseEntity.ok("API Root: /newsfeed");
  }

  @PostMapping("/newsfeed")
  public ResponseEntity<NewsPost> create(@RequestBody NewsPost newsPost) {
    NewsPost saved = service.saveNewsPost(newsPost);
    return ResponseEntity.ok(saved);
  }

  @GetMapping("/newsfeed")
  public List<NewsPost> list(
      // Optional text search on title and summary
      @RequestParam(required = false) String query,

      // Optional start of the date range
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String from,

      // Optional end of the date range
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String to,

      // Page number, REQUIRED
      @RequestParam int page,

      // Number of items per page, REQUIRED
      @RequestParam int pageSize) {

    OffsetDateTime offsetDateTimeFrom = null;
    OffsetDateTime offsetDateTimeTo = null;
    if (to != null && from != null) {
      LocalDate localDateFrom = LocalDate.parse(from);
      LocalDate localDateTo = LocalDate.parse(to);

      offsetDateTimeFrom = localDateFrom.atStartOfDay().atOffset(ZoneOffset.systemDefault()
          .getRules().getOffset(localDateFrom.atStartOfDay()));
      offsetDateTimeTo = localDateTo.plusDays(1).atStartOfDay().atOffset(ZoneOffset.systemDefault()
          .getRules().getOffset(localDateTo.atStartOfDay()));
    }

    int zeroBasedPage = page > 0 ? page - 1 : 0;
    Page<NewsPost> newsPage = service.listNewsPosts(query, offsetDateTimeFrom, offsetDateTimeTo, zeroBasedPage,
        pageSize);
    return newsPage.getContent();
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
  public ResponseEntity<List<NewsPostRevisionDto>> history(@PathVariable String id) {
    return ResponseEntity.ok(service.getNewsPostHistory(id));
  }
}
