package com.ase.newsfeedservice.components;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

import com.ase.newsfeedservice.components.Embedded.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class NewsPostCreateDto {
  private String id;
  private String title;
  private String summary;
  private String status;
  private Content content;
  private FeaturedImage featuredImage;
  private Author author;
  private String creationDate;
  private String publishDate;
  private String lastModified;
  private Expiration expiration;
  private Permissions permissions;
  private Settings settings;

  public NewsPost toEntity() {
    NewsPost entity = new NewsPost();
    entity.setId(id);
    entity.setTitle(title);
    entity.setSummary(summary);
    entity.setStatus(NewsPost.NewsPostStatus.valueOf(status));
    entity.setContent(content);
    entity.setFeaturedImage(featuredImage);
    entity.setAuthor(author);
    entity.setCreationDate(creationDate != null ? LocalDateTime.parse(creationDate) : null);
    entity.setPublishDate(publishDate != null ? LocalDateTime.parse(publishDate) : null);
    entity.setLastModified(lastModified != null ? LocalDateTime.parse(lastModified) : null);
    entity.setExpiration(expiration);
    entity.setPermissions(permissions);
    entity.setSettings(settings);
    return entity;
  }
}
