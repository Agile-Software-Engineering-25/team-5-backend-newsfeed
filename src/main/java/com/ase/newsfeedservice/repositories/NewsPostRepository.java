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

=======
>>>>>>> 4238373 (fixed errors):src/main/java/com/ase/userservice/repositories/NewsPostRepository.java
>>>>>>> d1a2d60 (fixed errors)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface NewsPostRepository extends JpaRepository<NewsPost, String> {
    @Query("SELECT np FROM NewsPost np WHERE " +
           "(:query IS NULL OR LOWER(np.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(np.summary) LIKE LOWER(CONCAT('%', :query, '%'))) AND " +
           "(:from IS NULL OR np.publish_date >= :from) AND " +
           "(:to IS NULL OR np.publish_date <= :to)")
    Page<NewsPost> listNewsPosts(
            @Param("query") String query,
            @Param("from") OffsetDateTime from,
            @Param("to") OffsetDateTime to,
            Pageable pageable
    );
}
