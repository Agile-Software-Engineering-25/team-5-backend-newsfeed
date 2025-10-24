package com.ase.newsfeedservice.components;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

import java.time.LocalDateTime;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonProperty;

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
     html
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
    // HINZUGEFÜGT: Diese Annotation mappt das Java-Feld 'userId'
    // auf das JSON-Feld 'user_id' aus der YAML-Datei.
    @JsonProperty("user_id")
    private String userId;

    private String name;
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
