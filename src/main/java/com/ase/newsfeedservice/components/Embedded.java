package com.ase.newsfeedservice.components;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.envers.Audited;

public class Embedded {
  @Embeddable
  @Data
  @Audited
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
  public static class FeaturedImage {
    private String url;
    private String altText;
    private String caption;
  }

  @Embeddable
  @Data
  @Audited
  public static class Author {
    private String userId;
    private String name;
    private String avatarUrl;
  }

  @Embeddable
  @Data
  @Audited
  public static class Expiration {
    private LocalDateTime expiresAt;
    private Boolean autoArchive;
  }

  @Embeddable
  @Data
  @Audited
  public static class Settings {
    private Boolean featured;
    private Boolean sticky;
  }

}
