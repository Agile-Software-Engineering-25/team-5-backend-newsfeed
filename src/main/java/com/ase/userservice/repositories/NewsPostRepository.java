package com.ase.userservice.repositories;

import com.ase.userservice.components.NewsPost;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsPostRepository extends JpaRepository<NewsPost, String> {
    @Query("SELECT np FROM NewsPost np WHERE " +
           "(:query IS NULL OR LOWER(np.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(np.summary) LIKE LOWER(CONCAT('%', :query, '%'))) AND " +
           "(:from IS NULL OR np.publish_date >= :from) AND " +
           "(:to IS NULL OR np.publish_date <= :to)")
    Page<NewsPost> findWithFilters(
            @Param("query") String query,
            @Param("from") OffsetDateTime from,
            @Param("to") OffsetDateTime to,
            Pageable pageable
    );
}
