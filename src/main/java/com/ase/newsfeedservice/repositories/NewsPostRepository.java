package com.ase.newsfeedservice.repositories;
import com.ase.newsfeedservice.components.NewsPost;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsPostRepository extends JpaRepository<NewsPost, String> {
  @Query("""
        SELECT DISTINCT np FROM NewsPost np
        JOIN np.permissions perm
        WHERE
        (:query IS NULL OR LOWER(np.title) LIKE LOWER(CONCAT('%', :query, '%')))
        AND (:from IS NULL OR np.creation_date >= :from)
        AND (:to IS NULL OR np.creation_date <= :to)
        AND (perm IN :groups)
      """)
  Page<NewsPost> listNewsPosts(
      @Param("query") String query,
      @Param("from") OffsetDateTime from,
      @Param("to") OffsetDateTime to,
      @Param("groups") List<String> groups
      Pageable pageable,
    );
}
