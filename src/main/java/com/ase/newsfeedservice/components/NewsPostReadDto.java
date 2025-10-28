package com.ase.newsfeedservice.components;

import lombok.Data;

import com.ase.newsfeedservice.components.Embedded.*;

@Data
public class NewsPostReadDto {
  private String id;
  private String title;
  private Content content;
  private Author author;
  private String creation_date;
  

  public static NewsPostReadDto fromEntity(NewsPost entity) {
    NewsPostReadDto dto = new NewsPostReadDto();
    dto.setId(entity.getId());
    dto.setTitle(entity.getTitle());
    dto.setContent(entity.getContent());
    dto.setAuthor(entity.getAuthor());
    dto.setCreation_date(entity.getCreation_date().toString());

    return dto;
  }
}
