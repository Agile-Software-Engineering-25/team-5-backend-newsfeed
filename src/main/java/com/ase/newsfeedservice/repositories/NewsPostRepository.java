package com.ase.newsfeedservice.repositories;
import com.ase.newsfeedservice.components.NewsPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NewsPostRepository extends JpaRepository<NewsPost, String>, JpaSpecificationExecutor<NewsPost> {
}
