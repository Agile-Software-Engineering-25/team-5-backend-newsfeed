package com.ase.newsfeedservice.services;
<<<<<<< HEAD

import com.ase.newsfeedservice.components.NewsPost;
import com.ase.newsfeedservice.components.NewsPostHistoryItemDto;
import com.ase.newsfeedservice.repositories.NewsPostRepository;
=======

import com.ase.newsfeedservice.components.NewsPost;
import com.ase.newsfeedservice.components.NewsPostHistoryItemDto;
import com.ase.newsfeedservice.repositories.NewsPostRepository;

<<<<<<< HEAD
>>>>>>> 11758a8 (Namechange for deployment)
=======
<<<<<<< HEAD:src/main/java/com/ase/newsfeedservice/services/NewsPostService.java
=======
import com.ase.userservice.components.NewsPost;
import com.ase.userservice.components.NewsPostHistoryItemDto;
import com.ase.userservice.repositories.NewsPostRepository;
>>>>>>> 1d4818d (cleaned up merge conflict):src/main/java/com/ase/userservice/services/NewsPostService.java
>>>>>>> ff505d1 (cleaned up merge conflict)
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsPostService {

  private final NewsPostRepository repository;

  public Page<NewsPost> listNewsPosts(String query, OffsetDateTime from, OffsetDateTime to, int page, int pageSize) {
    Pageable pageable = PageRequest.of(page, pageSize);
<<<<<<< HEAD
<<<<<<< HEAD
    return repository.listNewsPosts(query, from, to, pageable);
=======
    return newsPostRepository.findWithFilters(query, from, to, pageable);
>>>>>>> 4761264 (added queries)
=======
    return repository.listNewsPosts(query, from, to, pageable);
>>>>>>> d1a2d60 (fixed errors)
  }

  public NewsPost saveNewsPost(NewsPost newsPost) {
    return repository.save(newsPost);
  }

  public NewsPost updateNewsPost(NewsPost newsPost) {
    NewsPost existingPost = repository.findById(newsPost.getId())
        .orElseThrow(() -> new RuntimeException("NewsPost not found"));
    BeanUtils.copyProperties(newsPost, existingPost, "id", "version");
    return repository.save(existingPost);
  }

  public void deleteNewsPost(String id) {
    repository.deleteById(id);
  }

  public List<NewsPostHistoryItemDto> getNewsPostHistory(String id) {
    return List.of();
  }
}
