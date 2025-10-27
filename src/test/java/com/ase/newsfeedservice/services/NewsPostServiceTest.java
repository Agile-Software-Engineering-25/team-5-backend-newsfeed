package com.ase.newsfeedservice.services;

import com.ase.newsfeedservice.components.NewsPost;
import com.ase.newsfeedservice.exceptions.NewsPostNotFoundException;
import com.ase.newsfeedservice.repositories.NewsPostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class NewsPostServiceTest {

  @Mock
  NewsPostRepository repository;

  @InjectMocks
  NewsPostService service;

  @Test
  void saveNewsPostShouldSaveAndReturnNewsPost() {
    NewsPost post = new NewsPost();
    post.setId("123");
    when(repository.save(post)).thenReturn(post);

    NewsPost result = service.saveNewsPost(post);

    assertEquals("123", result.getId());
    verify(repository).save(post);
  }

  @Test
  void updateNewsPostShouldUpdateExistingPost() {
    NewsPost post = new NewsPost();
    post.setId("123");
    post.setTitle("Updated");

    NewsPost existing = new NewsPost();
    existing.setId("123");
    existing.setTitle("Old");

    when(repository.findById("123")).thenReturn(Optional.of(existing));
    when(repository.save(any())).thenReturn(existing);

    NewsPost result = service.updateNewsPost(post);

    assertEquals("123", result.getId());
    assertEquals("Updated", result.getTitle());
    verify(repository).save(existing);
  }

  @Test
  void updateNewsPostShouldThrowIfNotFound() {
    NewsPost post = new NewsPost();
    post.setId("notfound");

    when(repository.findById("notfound")).thenReturn(Optional.empty());

    assertThrows(NewsPostNotFoundException.class, () -> service.updateNewsPost(post));
  }

  private static final int PAGE_SIZE = 10;
  private List<String> groups = List.of("GROUP_admin");

  @Test
  void listNewsPostsShouldReturnPage() {
    Page<NewsPost> page = new PageImpl<>(List.of(new NewsPost()));
    when(repository.listNewsPosts(any(), any(), any(), any(), groups)).thenReturn(page);

    Page<NewsPost> result = service.listNewsPosts(null, null, null, 0, PAGE_SIZE, groups);

    assertEquals(1, result.getContent().size());
    verify(repository).listNewsPosts(any(), any(), any(), any(), groups);
  }
}
