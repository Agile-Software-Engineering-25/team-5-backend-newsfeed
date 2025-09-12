package com.ase.userservice.services;

import com.ase.userservice.components.NewsPost;
import com.ase.userservice.components.NewsPostHistoryItemDto;
import com.ase.userservice.components.NewsPostReadDto;
import com.ase.userservice.components.UserInfo;
import com.ase.userservice.repositories.NewsPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsPostService {

  private final NewsPostRepository repository;

  private final PermissionService permissionService;

  public List<NewsPost> getAllNewsPosts(String filter) {
    return repository.findAll();
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

  /*
   * Ruft alle Beiträge ab und filtert sie basierend auf den Leseberechtigungen des Benutzers.
   *
   * @param currentUser Der aktuell angemeldete Benutzer.
   * @return Eine Liste von DTOs, die nur die für den Benutzer sichtbaren Beiträge enthält.
   */
  public List<NewsPostReadDto> findAllVisibleForUser(UserInfo currentUser) {
    // Ruft die bestehende Methode auf, um alle Posts zu bekommen

    //TODO: SQL Filter irgendwann implementieren
    List<NewsPost> allPosts = this.getAllNewsPosts(null); // oder repository.findAll();

    // Führt die Filterung basierend auf der Benutzerberechtigung durch
    return allPosts.stream()
        .filter(post -> permissionService.hasReadPermission(post, currentUser))
        .map(NewsPostReadDto::fromEntity)
        .collect(Collectors.toList());
  }

}
