package com.ase.newsfeedservice.components;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.util.List;

import com.ase.newsfeedservice.components.Embedded.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class NewsPostCreateDto {
  private String id;
  private String title;
  private Content content;
  private Author author;
  private String creationDate;
  
  private List<String> permissions;
  public NewsPost toEntity() {
    NewsPost entity = new NewsPost();
    entity.setId(id);
    entity.setTitle(title);
    entity.setContent(content);
    entity.setAuthor(author);
    
    
    if (this.creationDate != null && !this.creationDate.isEmpty()) {
      try {
        entity.setCreationDate(OffsetDateTime.parse(this.creationDate));
      }
      catch (java.time.format.DateTimeParseException e) {
      }
    }
    entity.setPermissions(this.permissions);
    
    return entity;
  }
}