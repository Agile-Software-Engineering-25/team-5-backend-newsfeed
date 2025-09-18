package com.ase.newsfeedservice.components;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class NewsPost_old {
  @Id
  @Column(nullable = false, updatable = false)
  private String id;
  private String title;
  private String slug;
  private String summary;
  private String status;
  @Embedded
  private Content content;
  @Embedded
  private FeaturedImage featuredImage;
  @Embedded
  private Author author;
  private String creationDate;
  private String publishDate;
  private String lastModified;
  @ElementCollection
  List<NewsRevision> revisionHistory;
  @Embedded
  private Expiration expiration;
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "read.permissionType", column = @Column(name = "read_permission_type")),
      @AttributeOverride(name = "read.roles", column = @Column(name = "read_roles")),
      @AttributeOverride(name = "read.users", column = @Column(name = "read_users")),
      @AttributeOverride(name = "write.permissionType", column = @Column(name = "write_permission_type")),
      @AttributeOverride(name = "write.roles", column = @Column(name = "write_roles")),
      @AttributeOverride(name = "write.users", column = @Column(name = "write_users")),
      @AttributeOverride(name = "delete.permissionType", column = @Column(name = "delete_permission_type")),
      @AttributeOverride(name = "delete.roles", column = @Column(name = "delete_roles")),
      @AttributeOverride(name = "delete.users", column = @Column(name = "delete_users")),
      @AttributeOverride(name = "moderate.roles", column = @Column(name = "moderate_roles")),
      @AttributeOverride(name = "moderate.users", column = @Column(name = "moderate_users"))
  })
  private NewsPermissions permissions;
  @Embedded
  private NewsTaxonomy taxonomy;
  @Embedded
  private NewsSEO seo;
  @Embedded
  private NewsAnalytics analytics;
  @Embedded
  private NewsSettings settings;

  // UUID gen
  @PrePersist
  public void ensureId() {
    if (this.id == null) {
      this.id = UUID.randomUUID().toString();
    }
  }

  @Embeddable
  @Data
  public static class Content {
    private String format;
    private String body;
    @ElementCollection
    private List<NewsMediaAttachment> mediaAttachments;
  }

  @Embeddable
  @Data
  public static class NewsMediaAttachment {
    private String id;
    private String type;
    private String url;
    private String altText;
    private String caption;
    private Double fileSize;
    private String mimeType;
  }

  @Embeddable
  @Data
  public static class FeaturedImage {
    private String url;
    private String altText;
    private String caption;
  }

  @Embeddable
  @Data
  public static class Author {
    private String userId;
    private String name;
    private String avatarUrl;
  }

  @Embeddable
  @Data
  public static class NewsRevision {
    private Double version;
    private String timestamp;
    private String userId;
    private String changesSummary;
    private String previousVersionId;
  }

  @Embeddable
  @Data
  public static class Expiration {
    private String expiresAt;
    private Boolean autoArchive;
  }

  @Embeddable
  @Data
  public static class NewsPermissions {
    private NewsPermissionDetail read;
    private NewsPermissionDetail write;
    private NewsPermissionDetail delete;
    private NewsModeratePermission moderate;
    private Boolean inheritFromCategory;
  }

  @Embeddable
  @Data
  public static class NewsPermissionDetail {
    private String permissionType;
    private List<String> roles;
    private List<String> users;
  }

  @Embeddable
  @Data
  public static class NewsModeratePermission {
    private List<String> roles;
    private List<String> users;
  }

  @Embeddable
  @Data
  public static class NewsTaxonomy {
    private List<String> categories;
    private List<String> tags;
  }

  @Embeddable
  @Data
  public static class NewsSEO {
    private String metaTitle;
    private String metaDescription;
    private List<String> keywords;
  }

  @Embeddable
  @Data
  public static class NewsAnalytics {
    private Double viewCount;
    private Double likeCount;
    private Double dislikeCount;
    private Double commentCount;
  }

  @Embeddable
  @Data
  public static class NewsSettings {
    private Boolean allowComments;
    private Boolean featured;
    private Boolean sticky;
  }
}
