package com.ase.newsfeedservice.controllers;

import com.ase.newsfeedservice.components.NewsPost;
import com.ase.newsfeedservice.components.NewsPostRevisionDto;
import com.ase.newsfeedservice.services.NewsPostService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.beans.factory.annotation.Value;
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

  @PostMapping("/newsfeed")
  public ResponseEntity<NewsPost> create(@RequestBody NewsPost newsPost) {
    NewsPost saved = service.saveNewsPost(newsPost);
    return ResponseEntity.ok(saved);
  }

  @Value("${spring.profiles.active:}")
  private String activeProfile;

  @GetMapping("/newsfeed")
  public List<NewsPost> get(
      @RequestParam(required = false) String query,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String from,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String to,
      @RequestParam int page,
      @RequestParam int pageSize,
      Authentication authentication) {

    List<String> groups = List.of();

    if ("dev".equalsIgnoreCase(activeProfile)) {
      groups = List.of("sau-admin", "university-administrative-staff");
    } else {
      Jwt jwt = (Jwt) authentication.getPrincipal();
      groups = jwt.getClaimAsStringList("groups");
    }

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
        pageSize, groups);
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
  public ResponseEntity<List<NewsPostRevisionDto>> history(@PathVariable String id) {
    return ResponseEntity.ok(service.getNewsPostHistory(id));
  }
}
