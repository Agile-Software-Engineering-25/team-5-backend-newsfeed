package com.ase.userservice.components;

import jakarta.persistence.*;
import jakarta.persistence.Embedded;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.ase.userservice.components.Embedded.*;

@Entity
@Data
@Table(name = "news_posts")
public class NewsPost {
  @Id
  @Column(nullable = false, updatable = false)
  private String id;

  private String title;
  private String summary;

  @Enumerated(EnumType.STRING)
  private NewsPostStatus status;

  @Embedded
  private Content content;

  @Embedded
  private FeaturedImage featuredImage;

  @Embedded
  private Author author;

  private LocalDateTime creationDate;
  private LocalDateTime publishDate;
  private LocalDateTime lastModified;

  @Version
  private Integer version;

  @Embedded
  private Expiration expiration;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "read", column = @Column(name = "permission_read")),
      @AttributeOverride(name = "write", column = @Column(name = "permission_write")),
      @AttributeOverride(name = "delete", column = @Column(name = "permission_delete"))
  })
  private Permissions permissions;

  @Embedded
  private Settings settings;

  @PrePersist
  public void ensureId() {
    if (this.id == null) {
      this.id = UUID.randomUUID().toString();
    }
    if (this.creationDate == null) {
      this.creationDate = LocalDateTime.now();
    }
    if (this.lastModified == null) {
      this.lastModified = LocalDateTime.now();
    }
  }

  @PreUpdate
  public void updateModifiedDate() {
    this.lastModified = LocalDateTime.now();
  }

  public enum NewsPostStatus {
    draft, published, archived, deleted
  }
}
