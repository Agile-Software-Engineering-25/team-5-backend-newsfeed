package com.ase.newsfeedservice.repositories;

<<<<<<< HEAD:src/main/java/com/ase/newsfeedservice/repositories/NewsPostRepository.java
import com.ase.newsfeedservice.components.NewsPost;
<<<<<<< HEAD
=======
=======
import com.ase.userservice.components.NewsPost;
>>>>>>> d1a2d60 (fixed errors)

import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> 4238373 (fixed errors):src/main/java/com/ase/userservice/repositories/NewsPostRepository.java
>>>>>>> d1a2d60 (fixed errors)
=======
<<<<<<< HEAD:src/main/java/com/ase/newsfeedservice/repositories/NewsPostRepository.java
>>>>>>> 4238373 (fixed errors):src/main/java/com/ase/userservice/repositories/NewsPostRepository.java
=======

<<<<<<< HEAD:src/main/java/com/ase/newsfeedservice/repositories/NewsPostRepository.java
import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> f85c7a6 (fixed errors):src/main/java/com/ase/userservice/repositories/NewsPostRepository.java
<<<<<<< HEAD
>>>>>>> f1cd5ae (fixed errors)
=======
=======
>>>>>>> fa7e44b (merge clean up):src/main/java/com/ase/userservice/repositories/NewsPostRepository.java
>>>>>>> 20ed3a2 (merge clean up)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsPostRepository extends JpaRepository<NewsPost, String> {
    @Query("SELECT np FROM NewsPost np WHERE " +
            "(:query IS NULL OR LOWER(np.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(np.summary) LIKE LOWER(CONCAT('%', :query, '%'))) AND "
            +
            "(:from IS NULL OR np.publishDate >= :from) AND " +
            "(:to IS NULL OR np.publishDate <= :to)")
    Page<NewsPost> listNewsPosts(
            @Param("query") String query,
            @Param("from") OffsetDateTime from,
            @Param("to") OffsetDateTime to,
            Pageable pageable);
}
