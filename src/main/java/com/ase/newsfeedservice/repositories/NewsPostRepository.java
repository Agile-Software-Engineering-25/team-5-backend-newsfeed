package com.ase.newsfeedservice.repositories;

import com.ase.newsfeedservice.components.NewsPost;

import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsPostRepository extends JpaRepository<NewsPost, String> {
}
