package com.ase.newsfeedservice.components;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NewsPostUpdateDto extends NewsPostCreateDto {
  // Inherits all fields from NewsPostCreateDto
}
