package com.ase.userservice.repositories;

import com.ase.userservice.components.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, String> {
}
