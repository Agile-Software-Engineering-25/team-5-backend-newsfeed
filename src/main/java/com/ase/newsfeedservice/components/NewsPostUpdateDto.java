package com.ase.newsfeedservice.components;

import lombok.Data;
// import lombok.EqualsAndHashCode; // Nicht mehr nötig, da callSuper = true entfällt
import com.ase.newsfeedservice.components.Embedded.Content; // Importiere die eingebetteten DTOs
import com.ase.newsfeedservice.components.Embedded.Author; 
import java.util.List; // Für PermissionsList

@Data
public class NewsPostUpdateDto { 
 // ACHTUNG: Keine Vererbung von NewsPostCreateDto mehr!

 // properties:
 // title: { type: string }
 private String title;
    
 // content: { $ref: '#/components/schemas/Content' }
 private Content content;
    
 // author: { $ref: '#/components/schemas/Author' }
 private Author author;
  
 // creationDate: { type: string, format: date-time }
 private String creationDate; 
  
 // permissions: { $ref: '#/components/schemas/PermissionsList' } -> List<String>
 private List<String> permissions;

}