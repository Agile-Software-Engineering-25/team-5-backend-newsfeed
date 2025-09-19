-- =========================
-- News Post 1
-- =========================
INSERT INTO news_posts (
    id, title, summary, status,
    format, body,
    url, alt_text, caption,
    user_id, name, avatar_url,
    creation_date, publish_date, last_modified,
    version,
    expires_at, auto_archive,
    featured, sticky
) VALUES (
             '11111111-1111-1111-1111-111111111111',
             'Spring Boot News Release',
             'New features in Spring Boot 3.2',
             'published',
             'markdown',
             '# Spring Boot 3.2 Released!',
             'https://example.com/images/spring.png', 'Spring Boot Logo', 'Release Announcement',
             'user-1', 'Alice Johnson', 'https://example.com/avatars/alice.png',
             CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
             0,
             TIMESTAMP '2025-12-31 23:59:59', FALSE,
             TRUE, FALSE
         );

INSERT INTO news_post_read_permissions (news_post_id, id, type, name) VALUES
                                                                          ('11111111-1111-1111-1111-111111111111', 'reader-1', 'user', 'Test Reader'),
                                                                          ('11111111-1111-1111-1111-111111111111', 'group-1', 'group', 'Editors');

INSERT INTO news_post_write_permissions (news_post_id, id, type, name) VALUES
    ('11111111-1111-1111-1111-111111111111', 'writer-1', 'user', 'Bob Writer');

INSERT INTO news_post_delete_permissions (news_post_id, id, type, name) VALUES
    ('11111111-1111-1111-1111-111111111111', 'deleter-1', 'user', 'Charlie Admin');


-- =========================
-- News Post 2
-- =========================
INSERT INTO news_posts (
    id, title, summary, status,
    format, body,
    url, alt_text, caption,
    user_id, name, avatar_url,
    creation_date, publish_date, last_modified,
    version,
    expires_at, auto_archive,
    featured, sticky
) VALUES (
             '22222222-2222-2222-2222-222222222222',
             'Vaadin UI Update',
             'Vaadin 24 brings new components',
             'draft',
             'html',
             '<h1>Vaadin 24</h1><p>Check out the new grid features!</p>',
             'https://example.com/images/vaadin.png', 'Vaadin Logo', 'UI Update',
             'user-2', 'Bob Smith', 'https://example.com/avatars/bob.png',
             CURRENT_TIMESTAMP, NULL, CURRENT_TIMESTAMP,
             0,
             NULL, NULL,
             FALSE, TRUE
         );

INSERT INTO news_post_read_permissions (news_post_id, id, type, name) VALUES
    ('22222222-2222-2222-2222-222222222222', 'group-2', 'group', 'UI Testers');


-- =========================
-- News Post 3
-- =========================
INSERT INTO news_posts (
    id, title, summary, status,
    format, body,
    url, alt_text, caption,
    user_id, name, avatar_url,
    creation_date, publish_date, last_modified,
    version,
    expires_at, auto_archive,
    featured, sticky
) VALUES (
             '33333333-3333-3333-3333-333333333333',
             'Database Migration Tips',
             'PostgreSQL tricks for Java devs',
             'archived',
             'markdown',
             '## Tips\nUse pgAdmin and Flyway for smoother migrations.',
             NULL, NULL, NULL,
             'user-3', 'Charlie Doe', NULL,
             CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
             1,
             TIMESTAMP '2025-02-01 00:00:00', TRUE,
             FALSE, FALSE
         );

INSERT INTO news_post_read_permissions (news_post_id, id, type, name) VALUES
    ('33333333-3333-3333-3333-333333333333', 'reader-2', 'user', 'Database Enthusiast');

INSERT INTO news_post_delete_permissions (news_post_id, id, type, name) VALUES
    ('33333333-3333-3333-3333-333333333333', 'deleter-2', 'user', 'Moderator');


-- =========================
-- News Post 4
-- =========================
INSERT INTO news_posts (
    id, title, summary, status,
    format, body,
    url, alt_text, caption,
    user_id, name, avatar_url,
    creation_date, publish_date, last_modified,
    version,
    expires_at, auto_archive,
    featured, sticky
) VALUES (
             '44444444-4444-4444-4444-444444444444',
             'Security Advisory',
             'Patch released for recent CVE',
             'published',
             'markdown',
             'Please update to the latest version to avoid vulnerabilities.',
             'https://example.com/images/security.png', 'Security Shield', 'Important Notice',
             'user-4', 'Dana Lee', 'https://example.com/avatars/dana.png',
             CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
             2,
             TIMESTAMP '2025-06-30 00:00:00', TRUE,
             TRUE, TRUE
         );

INSERT INTO news_post_read_permissions (news_post_id, id, type, name) VALUES
    ('44444444-4444-4444-4444-444444444444', 'all-users', 'group', 'All Users');

INSERT INTO news_post_write_permissions (news_post_id, id, type, name) VALUES
    ('44444444-4444-4444-4444-444444444444', 'security-team', 'group', 'Security Team');

INSERT INTO news_post_delete_permissions (news_post_id, id, type, name) VALUES
    ('44444444-4444-4444-4444-444444444444', 'admin-1', 'user', 'System Admin');
