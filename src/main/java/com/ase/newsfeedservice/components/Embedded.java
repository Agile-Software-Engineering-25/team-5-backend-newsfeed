package com.ase.newsfeedservice.components;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import org.hibernate.envers.Audited;

public class Embedded {

  @Embeddable
  @Data
  @Audited
  @Getter
  @Setter
  public static class Content {
    @Enumerated(EnumType.STRING)
    private ContentFormat format;

    @Column(columnDefinition = "TEXT")
    private String body;

    public enum ContentFormat {
      markdown, html
    }
  }

  @Embeddable
  @Data
  @Audited
  @Getter
  @Setter
  public static class FeaturedImage {
    private String url;
    private String altText;
    private String caption;
  }

  @Embeddable
  @Data
  @Audited
  @Getter
  @Setter
  public static class Author {
    private String userId;
    private String name;
    private String avatarUrl;
  }

  @Embeddable
  @Data
  @Audited
  @Getter
  @Setter
  public static class Expiration {
    private LocalDateTime expiresAt;
    private Boolean autoArchive;
  }

  @Embeddable
  @Data
  @Audited
  @Getter
  @Setter
  public static class Permissions {
    @ElementCollection
    @CollectionTable(name = "news_post_read_permissions")
    private List<PrincipalRef> read;

    @ElementCollection
    @CollectionTable(name = "news_post_write_permissions")
    private List<PrincipalRef> write;

    @ElementCollection
    @CollectionTable(name = "news_post_delete_permissions")
    private List<PrincipalRef> delete;
  }

  @Embeddable
  @Getter
  @Setter
  public static class PrincipalRef {
    private String id;

    @Enumerated(EnumType.STRING)
    private PrincipalType type;

    private String name;

    public enum PrincipalType {
      user, group
    }
  }

  @Embeddable
  @Getter
  @Setter
  public static class Settings {
    private Boolean featured;
    private Boolean sticky;
  }
}
