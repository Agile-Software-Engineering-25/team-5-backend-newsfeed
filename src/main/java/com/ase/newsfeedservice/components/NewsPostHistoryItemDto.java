package com.ase.newsfeedservice.components;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NewsPostHistoryItemDto extends NewsPostReadDto {
  private String slug;
}
