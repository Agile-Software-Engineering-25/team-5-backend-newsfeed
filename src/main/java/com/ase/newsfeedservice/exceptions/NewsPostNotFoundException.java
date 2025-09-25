package com.ase.newsfeedservice.exceptions;

public class NewsPostNotFoundException extends RuntimeException {
  public NewsPostNotFoundException(String id) {
    super("NewsPost not found: " + id);
  }
}
