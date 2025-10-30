package com.ase.newsfeedservice.services;

import com.ase.newsfeedservice.components.NewsPost;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class EmailNotificationHelper {
  private final RestTemplate restTemplate = new RestTemplate();

  // URLs can be injected via application.yml if you prefer
  private static final String TOKEN_URL = "https://keycloak.sau-portal.de/realms/sau/protocol/openid-connect/token";
  private static final String API_URL = "https://sau-portal.de/notification-service/api/v1/notifications";
  private static final String clientId = System.getenv("CLIENT_ID");
  private static final String clientSecret = System.getenv("CLIENT_SECRET");

  public String emailNotify(NewsPost newsPost) {
    String jwt = getJwtToken(clientId, clientSecret);

    Map<String, Object> requestBody = Map.of(
        "title", "Neuer Beitrag im Newsfeed",
        "message", "Es wurde ein neuer Beitrag veröffentlicht: " + newsPost.getTitle(),
        "notifyType", "All",
        "notificationType", "Info",
        "groups", ""
    );

    // idk ob das permissions array nur die groups hält
    requestBody.put("groups", newsPost.getPermissions());

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(jwt);

    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        API_URL,
        HttpMethod.POST,
        entity,
        String.class
    );

    return response.getBody();
  }

  private String getJwtToken() {
    Map<String, String> requestBody = Map.of(
        "client_id", clientId,
        "client_secret", clientSecret,
        "grant_type", "client_credentials"
    );

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<TokenResponse> response = restTemplate.postForEntity(
        TOKEN_URL,
        entity,
        TokenResponse.class
    );

    return response.getBody().getAccessToken();
  }

  private static class TokenResponse {
    private String access_token;
    public String getAccessToken() { return access_token; }
    public void setAccess_token(String access_token) { this.access_token = access_token; }
  }
}
