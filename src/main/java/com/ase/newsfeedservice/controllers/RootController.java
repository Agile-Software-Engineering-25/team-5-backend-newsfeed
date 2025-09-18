package com.ase.newsfeedservice.controllers;

import com.ase.newsfeedservice.components.NewsPost;
import com.ase.newsfeedservice.components.NewsPostHistoryItemDto;
import com.ase.newsfeedservice.services.NewsPostService;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
<<<<<<< HEAD

import org.springframework.data.domain.Page;
=======
>>>>>>> 6fc83c0 (init)
import org.springframework.format.annotation.DateTimeFormat;
=======

import org.springframework.data.domain.Page;
>>>>>>> ff505d1 (cleaned up merge conflict)
import org.springframework.format.annotation.DateTimeFormat;
<<<<<<< HEAD

<<<<<<< HEAD
=======
=======
import org.springframework.format.annotation.DateTimeFormat;
>>>>>>> 5aeb80b (init)
=======
import org.springframework.format.annotation.DateTimeFormat;
>>>>>>> ebbc246 (init)
>>>>>>> c62854e (init)
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
=======
import java.time.OffsetDateTime;
>>>>>>> 6fc83c0 (init)
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
<<<<<<< HEAD
<<<<<<< HEAD
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

    LocalDate localDateFrom = LocalDate.parse(from);
    LocalDate localDateTo = LocalDate.parse(to);

    OffsetDateTime offsetDateTimeFrom = localDateFrom.atStartOfDay().atOffset(ZoneOffset.systemDefault()
        .getRules().getOffset(localDateFrom.atStartOfDay()));
    OffsetDateTime offsetDateTimeTo = localDateFrom.atStartOfDay().atOffset(ZoneOffset.systemDefault()
        .getRules().getOffset(localDateTo.atStartOfDay()));

    int zeroBasedPage = page > 0 ? page - 1 : 0;
    Page<NewsPost> newsPage = service.listNewsPosts(query, offsetDateTimeFrom, offsetDateTimeTo, zeroBasedPage,
        pageSize);
    return newsPage.getContent();
=======
  public Page<NewsPost> list(
=======
  public List<NewsPost> list(
>>>>>>> 7e4539e (fix data format)
      // Optional text search on title and summary
      @RequestParam(required = false) String query,

      // Optional start of the date range
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,

      // Optional end of the date range
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to,

      // Page number, REQUIRED
      @RequestParam int page,

<<<<<<< HEAD
    // Number of items per page, REQUIRED
    @RequestParam int pageSize
  ) {
<<<<<<< HEAD
    return newsPostService.listNewsPosts(query, from, to, page, pageSize);
>>>>>>> 4761264 (added queries)
=======
=======
      // Number of items per page, REQUIRED
      @RequestParam int pageSize) {
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 3d8d8d6 (make the linter shut up)
    return service.listNewsPosts(query, from, to, page, pageSize);
>>>>>>> d1a2d60 (fixed errors)
=======
        // First, get the full Page object from your service
    Page<NewsPost> newsPage = service.listNewsPosts(query, from, to, page, pageSize);
    
    // Then, return only the content list
=======

    int zeroBasedPage = page > 0 ? page - 1 : 0;
    Page<NewsPost> newsPage = service.listNewsPosts(query, from, to, zeroBasedPage, pageSize);
>>>>>>> aaef08f (make page 1 indexed)
    return newsPage.getContent();
>>>>>>> 7e4539e (fix data format)
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
