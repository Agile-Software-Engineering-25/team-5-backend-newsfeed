package com.ase.newsfeedservice.components;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class NewsPostRevisionDto {
    
 // 1. revisionNumber: type: integer
  private Integer revisionNumber; 

 // 2. revisionTimestamp: type: string, format: date-time
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
 private Instant revisionTimestamp; 
    
 // 3. revisionType: type: string, enum: [ADD, MOD, DEL]
  private RevisionAction revisionType; 
    
 // 4. newsPostState: $ref: NewsPostRead
  private NewsPostReadDto newsPostState; 

  public enum RevisionAction {
  ADD,
  MOD,
  DEL
  }
}
