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
  private String creation_date;
  
  private List<String> permissions;
  public NewsPost toEntity() {
    NewsPost entity = new NewsPost();
    entity.setId(id);
    entity.setTitle(title);
    entity.setContent(content);
    entity.setAuthor(author);
    
    
    if (this.creation_date != null && !this.creation_date.isEmpty()) {
      try {
        entity.setCreation_date(OffsetDateTime.parse(this.creation_date));
      }
      catch (java.time.format.DateTimeParseException e) {
      }
    }
    entity.setPermissions(this.permissions);
    
    return entity;
  }
}