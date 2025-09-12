package com.ase.userservice.components;

import lombok.Data;
import java.util.List;

/**
Platzhalter für die echte Implementierung
 */
@Data
public class UserInfo {
    private String userId;
    private String username;
    private List<String> groups; // Gruppen, denen der Benutzer angehört
}