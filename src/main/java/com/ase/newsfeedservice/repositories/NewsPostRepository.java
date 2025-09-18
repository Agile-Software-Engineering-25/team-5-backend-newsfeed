package com.ase.newsfeedservice.repositories;

<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/com/ase/newsfeedservice/repositories/NewsPostRepository.java
import com.ase.newsfeedservice.components.NewsPost;
<<<<<<< HEAD
=======
=======
import com.ase.userservice.components.NewsPost;
<<<<<<< HEAD
>>>>>>> d1a2d60 (fixed errors)
=======
=======
import com.ase.newsfeedservice.components.NewsPost;
>>>>>>> 8f80d4f (fix merge conflict issues; fix date format)
>>>>>>> 7da6cdc (fix merge conflict issues; fix date format)

import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> 4238373 (fixed errors):src/main/java/com/ase/userservice/repositories/NewsPostRepository.java
>>>>>>> d1a2d60 (fixed errors)
=======
=======
>>>>>>> 7da6cdc (fix merge conflict issues; fix date format)
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
<<<<<<< HEAD
>>>>>>> 20ed3a2 (merge clean up)
=======
=======

>>>>>>> 8f80d4f (fix merge conflict issues; fix date format)
>>>>>>> 7da6cdc (fix merge conflict issues; fix date format)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsPostRepository extends JpaRepository<NewsPost, String> {
  @Query("SELECT np FROM NewsPost np WHERE " +
      "(:query IS NULL OR LOWER(np.title) LIKE LOWER(CONCAT('%', :query, '%'))" +
      "OR LOWER(np.summary) LIKE LOWER(CONCAT('%', :query, '%'))) AND " +
      "(:from IS NULL OR np.publishDate >= :from) AND " +
      "(:to IS NULL OR np.publishDate <= :to)")
  Page<NewsPost> listNewsPosts(
      @Param("query") String query,
      @Param("from") OffsetDateTime from,
      @Param("to") OffsetDateTime to,
      Pageable pageable);
}
