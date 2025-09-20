package com.ase.newsfeedservice.components;

import jakarta.persistence.*;
import jakarta.persistence.Embedded;
import lombok.Data;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import static com.ase.newsfeedservice.components.Embedded.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "news_posts")
@Audited
@AuditTable("news_post_aud")
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

  private OffsetDateTime creationDate;
  private OffsetDateTime publishDate;
  private OffsetDateTime lastModified;

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
      this.creationDate = OffsetDateTime.now();
    }
    if (this.lastModified == null) {
      this.lastModified = OffsetDateTime.now();
    }
  }

  @PreUpdate
  public void updateModifiedDate() {
    this.lastModified = OffsetDateTime.now();
  }

  public enum NewsPostStatus {
    draft, published, archived, deleted
  }
}
