package com.ase.newsfeedservice.components;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

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
      html
    }
  }
  @Embeddable
  @Data
  @Audited
  @Getter
  @Setter
  public static class Author {
    private String userId;
    private String name;
  }
}