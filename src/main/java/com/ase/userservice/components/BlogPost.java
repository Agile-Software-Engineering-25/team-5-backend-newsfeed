package com.ase.userservice.components;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class BlogPost {
  @Id
  private String id;
  private String title;
  private String slug;
  private String summary;
  private String status;
  @Embedded private Content content;
  @Embedded private FeaturedImage featuredImage;
  @Embedded private Author author;
  private String creationDate;
  private String publishDate;
  private String lastModified;
  @ElementCollection List<BlogRevision> revisionHistory;
  @Embedded private Expiration expiration;
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
  private BlogPermissions permissions;
  @Embedded private BlogTaxonomy taxonomy;
  @Embedded private BlogSEO seo;
  @Embedded private BlogAnalytics analytics;
  @Embedded private BlogSettings settings;

  @Embeddable
  @Data
  public static class Content {
    private String format;
    private String body;
    @ElementCollection
    private List<BlogMediaAttachment> mediaAttachments;
  }

  @Embeddable
  @Data
  public static class BlogMediaAttachment {
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
  public static class BlogRevision {
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
  public static class BlogPermissions {
    private BlogPermissionDetail read;
    private BlogPermissionDetail write;
    private BlogPermissionDetail delete;
    private BlogModeratePermission moderate;
    private Boolean inheritFromCategory;
  }

  @Embeddable
  @Data
  public static class BlogPermissionDetail {
    private String permissionType;
    private List<String> roles;
    private List<String> users;
  }

  @Embeddable
  @Data
  public static class BlogModeratePermission {
    private List<String> roles;
    private List<String> users;
  }

  @Embeddable
  @Data
  public static class BlogTaxonomy {
    private List<String> categories;
    private List<String> tags;
  }

  @Embeddable
  @Data
  public static class BlogSEO {
    private String metaTitle;
    private String metaDescription;
    private List<String> keywords;
  }

  @Embeddable
  @Data
  public static class BlogAnalytics {
    private Double viewCount;
    private Double likeCount;
    private Double dislikeCount;
    private Double commentCount;
  }

  @Embeddable
  @Data
  public static class BlogSettings {
    private Boolean allowComments;
    private Boolean featured;
    private Boolean sticky;
  }
}
