package com.ase.userservice;
import com.ase.userservice.components.NewsPost;
import com.ase.userservice.components.NewsPostReadDto;
import com.ase.userservice.components.UserInfo;
import com.ase.userservice.repositories.NewsPostRepository;
import com.ase.userservice.services.NewsPostService;
import com.ase.userservice.services.PermissionService; // Korrigierter Import

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NewsPostServiceTest {

    @Mock
    private NewsPostRepository newsPostRepository;

    @Mock
    private PermissionService permissionService;

    @InjectMocks
    private NewsPostService newsPostService;

    @Test
    void findAllVisibleForUser_shouldReturnOnlyPermittedPosts() {
        // --- 1. Given (Vorbereitung) ---
        UserInfo testUser = new UserInfo();
        testUser.setUserId("user-123");

        NewsPost publicPost = new NewsPost();
        publicPost.setId("post-1");
        publicPost.setTitle("Öffentlicher Beitrag");
        publicPost.setCreationDate(LocalDateTime.now());
        // WICHTIG: Setzen Sie hier den Status, damit er nicht null ist
        publicPost.setStatus(NewsPost.NewsPostStatus.published); // Beispiel: Setzen Sie einen gültigen Enum-Wert

        NewsPost privatePost = new NewsPost();
        privatePost.setId("post-2");
        privatePost.setTitle("Privater Beitrag");
        privatePost.setCreationDate(LocalDateTime.now());
        // WICHTIG: Auch hier den Status setzen
        privatePost.setStatus(NewsPost.NewsPostStatus.draft); // Beispiel: Setzen Sie einen anderen gültigen Wert

        List<NewsPost> allPosts = List.of(publicPost, privatePost);

        when(newsPostRepository.findAll()).thenReturn(allPosts);
        when(permissionService.hasReadPermission(publicPost, testUser)).thenReturn(true);
        when(permissionService.hasReadPermission(privatePost, testUser)).thenReturn(false);

        // --- 2. When (Aktion) ---
        List<NewsPostReadDto> visiblePosts = newsPostService.findAllVisibleForUser(testUser);

        // --- 3. Then (Überprüfung) ---
        assertThat(visiblePosts).isNotNull();
        assertThat(visiblePosts).hasSize(1);
        assertThat(visiblePosts.get(0).getTitle()).isEqualTo("Öffentlicher Beitrag");
    }
}