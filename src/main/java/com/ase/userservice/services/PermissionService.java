package com.ase.userservice.services;

import com.ase.userservice.components.NewsPost;
import com.ase.userservice.components.UserInfo;
import org.springframework.stereotype.Service;

/**
 * Dieser Service prüft die Berechtigungen von Benutzern für Aktionen auf NewsPosts.
 * Aktuell wird die Berechtigungsprüfung simuliert und immer true zurückgegeben.
 */
@Service
public class PermissionService {

    /**
     * Prüft, ob ein Benutzer die Leseberechtigung für einen bestimmten NewsPost hat.
     *
     * @param newsPost Der zu prüfende News-Beitrag.
     * @param user     Die Informationen des angemeldeten Benutzers.
     * @return         Gibt vorerst immer {@code true} zurück (Platzhalter-Implementierung).
     */
    public boolean hasReadPermission(NewsPost newsPost, UserInfo user) {
        // TODO: Hier wird später die echte Logik der Auth-Bibliothek integriert.
        System.out.println("Prüfe Leseberechtigung für User '" + user.getUsername() + "' auf Beitrag '" + newsPost.getTitle() + "' -> Ergebnis: true (simuliert)");
        return true;
    }
}