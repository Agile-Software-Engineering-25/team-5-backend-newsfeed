package com.ase.newsfeedservice.controllers;

import com.ase.newsfeedservice.components.NewsPost;
import com.ase.newsfeedservice.components.NewsPostHistoryItemDto;
import com.ase.newsfeedservice.services.NewsPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @PutMapping("/newsfeed")
  public ResponseEntity<NewsPost> create(@RequestBody NewsPost newsPost) {
    NewsPost saved = service.saveNewsPost(newsPost);
    return ResponseEntity.ok(saved);
  }

  @GetMapping("/newsfeed")
  public List<NewsPost> list(
      @RequestParam(required = false) String query,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String from,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String to,
      @RequestParam int page,
      @RequestParam int pageSize) {

    LocalDate localDateFrom = LocalDate.parse(from);
    LocalDate localDateTo = LocalDate.parse(to);

    OffsetDateTime offsetDateTimeFrom = localDateFrom.atStartOfDay().atOffset(
        ZoneOffset.systemDefault().getRules().getOffset(localDateFrom.atStartOfDay()));
    OffsetDateTime offsetDateTimeTo = localDateFrom.atStartOfDay().atOffset(
        ZoneOffset.systemDefault().getRules().getOffset(localDateTo.atStartOfDay()));

    int zeroBasedPage = page > 0 ? page - 1 : 0;
    Page<NewsPost> newsPage =
        service.listNewsPosts(query, offsetDateTimeFrom, offsetDateTimeTo, zeroBasedPage, pageSize);
    return newsPage.getContent();
  }

  @PutMapping("/newsfeed/{id}")
  public ResponseEntity<NewsPost> update(@PathVariable String id, @RequestBody NewsPost newsPost) {
    newsPost.setId(id);
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
