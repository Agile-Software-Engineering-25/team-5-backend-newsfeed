package com.ase.userservice.repositories;

import com.ase.userservice.components.NewsPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsPostRepository extends JpaRepository<NewsPost, String> {
}
