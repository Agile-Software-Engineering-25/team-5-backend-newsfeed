package com.ase.newsfeedservice.components;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.util.List;

import com.ase.newsfeedservice.components.Embedded.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class NewsPostCreateDto {

  // HINZUGEFÜGT: 'id' (optional laut YAML)
  private String id;

  private String title;
  private Content content;
  private Author author;

  // HINZUGEFÜGT: 'creationDate' (optional laut YAML)
  // Als String, um das 'format: date-time' aus der YAML zu empfangen
  private String creationDate;

  // HINZUGEFÜGT: 'permissions' (optional laut YAML)
  private List<String> permissions;


  public NewsPost toEntity() {
    NewsPost entity = new NewsPost();
    
  // HINZUGEFÜGT: Mapping für die neuen Felder
    entity.setId(this.id); // 'PrePersist' in NewsPost füllt die ID, falls sie null ist
    entity.setPermissions(this.permissions);

   // HINZUGEFÜGT: Parsen des Datums-Strings zur Entität
    if (this.creationDate != null && !this.creationDate.isEmpty()) {
      try {
        entity.setCreationDate(OffsetDateTime.parse(this.creationDate));
      }
      catch (java.time.format.DateTimeParseException e) {
      // Optional: Fehlerbehandlung, falls das Datumsformat ungültig ist
      // Vorerst wird es ignoriert, wenn es nicht geparst werden kann
      }
    }
    
    entity.setTitle(title);
    entity.setContent(content);
    entity.setAuthor(author);
    return entity;
  }
}