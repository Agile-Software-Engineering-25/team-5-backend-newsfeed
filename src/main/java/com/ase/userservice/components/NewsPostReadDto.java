package com.ase.userservice.components;

import lombok.Data;

import com.ase.userservice.components.Embedded.*;

@Data
public class NewsPostReadDto {
  private String id;
  private Integer version;
  private String title;
  private String summary;
  private String status;
  private Content content;
  private FeaturedImage featuredImage;
  private Author author;
  private String creationDate;
  private String publishDate;
  private String lastModified;
  private PermissionFlags permissions;
  private Settings settings;

  @Data
  public static class PermissionFlags {
    private Boolean update;
    private Boolean delete;
  }

  public static NewsPostReadDto fromEntity(NewsPost entity) {
    NewsPostReadDto dto = new NewsPostReadDto();
    dto.setId(entity.getId());
    dto.setVersion(entity.getVersion());
    dto.setTitle(entity.getTitle());
    dto.setSummary(entity.getSummary());
    dto.setStatus(entity.getStatus().name());
    dto.setContent(entity.getContent());
    dto.setFeaturedImage(entity.getFeaturedImage());
    dto.setAuthor(entity.getAuthor());
    dto.setCreationDate(entity.getCreationDate().toString());
    dto.setPublishDate(entity.getPublishDate() != null ? entity.getPublishDate().toString() : null);
    dto.setLastModified(entity.getLastModified() != null ? entity.getLastModified().toString() : null);

    // Set permission flags based on entity permissions
    PermissionFlags flags = new PermissionFlags();
    // Logic to determine update/delete permissions
    dto.setPermissions(flags);

    dto.setSettings(entity.getSettings());
    return dto;
  }
}
