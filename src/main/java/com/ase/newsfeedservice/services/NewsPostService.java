package com.ase.newsfeedservice.services;

import com.ase.newsfeedservice.components.NewsPost;
import com.ase.newsfeedservice.components.NewsPostRevisionDto;
import com.ase.newsfeedservice.repositories.NewsPostRepository;

import ch.qos.logback.core.joran.action.NewRuleAction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsPostService {

  @Autowired
  private EntityManager entityManager;
  private final NewsPostRepository NewsPostRepo;

  public Page<NewsPost> listNewsPosts(String query, OffsetDateTime from, OffsetDateTime to, int page, int pageSize) {
    Pageable pageable = PageRequest.of(page, pageSize);
    return NewsPostRepo.listNewsPosts(query, from, to, pageable);
  }

  public NewsPost saveNewsPost(NewsPost newsPost) {
    return NewsPostRepo.save(newsPost);
  }

  public NewsPost updateNewsPost(NewsPost newsPost) {
    NewsPost existingPost = NewsPostRepo.findById(newsPost.getId())
        .orElseThrow(() -> new RuntimeException("NewsPost not found"));
    BeanUtils.copyProperties(newsPost, existingPost, "id", "version");
    return NewsPostRepo.save(existingPost);
  }

  public void deleteNewsPost(String id) {
    NewsPostRepo.deleteById(id);
  }

  // public List<NewsPostRevisionDto> getNewsPostHistory(String id) {
  // return List.of();
  // }

  @Transactional
  public List<NewsPostRevisionDto> getNewsPostHistory(String id) {
    // 1. Get the Envers AuditReader
    AuditReader auditReader = AuditReaderFactory.get(entityManager);

    // 2. Create an audit query to find all revisions for the Article entity,
    // ordered by revision number.
    AuditQuery auditQuery = auditReader.createQuery()
        .forRevisionsOfEntity(NewsPost.class, false, true)
        .add(AuditEntity.id().eq(id))
        .addOrder(AuditEntity.revisionNumber().asc());

    // 3. Execute the query
    List<Object[]> queryResult = auditQuery.getResultList();

    // Check if history exists to provide a clear error message
    if (queryResult.isEmpty()) {
      throw new NoResultException("No audit history found for Article with ID: " +
          id);
    }

    // 4. Map the raw query results to our clean DTO
    return queryResult.stream()
        .map(this::mapToRevisionDTO)
        .collect(Collectors.toList());
  }

  /**
   * Helper method to map the raw Envers result object array to our DTO.
   * 
   * @param revision A raw revision object array from the Envers query.
   *                 The array contains: [entity, revisionEntity, revisionType]
   * @return A populated ArticleRevisionDTO.
   */
  private NewsPostRevisionDto mapToRevisionDTO(Object[] revision) {
    NewsPost articleAtRevision = (NewsPost) revision[0];
    DefaultRevisionEntity revisionInfo = (DefaultRevisionEntity) revision[1];
    RevisionType revisionType = (RevisionType) revision[2];

    return new NewsPostRevisionDto(
        revisionInfo.getId(),
        revisionInfo.getRevisionDate(),
        revisionType,
        articleAtRevision);
  }

}
