package com.ase.newsfeedservice.components;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.ase.newsfeedservice.components.Embedded.Author;
import com.ase.newsfeedservice.components.Embedded.Content;
import com.ase.newsfeedservice.components.Embedded.Expiration;
import com.ase.newsfeedservice.components.Embedded.FeaturedImage;
import com.ase.newsfeedservice.components.Embedded.Settings;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
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

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "newspost_permissions", joinColumns = @JoinColumn(name = "id"))
  @Column(name = "permission")
  private List<String> permissions;

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
