documentation for any relevant json templates

## Blogpost:
```json
{
  "id": "string", // Unique identifier for the post - essential for database operations and referencing
  "title": "string", // Post headline - required for display and SEO, appears in browser tabs and search results
  "slug": "string", // URL-friendly version of title - enables clean URLs like /blog/my-post-title
  "summary": "string", // Brief description/excerpt - used for previews, meta descriptions, and social media cards
  "status": "draft|published|archived|deleted", // Content workflow state - controls visibility and editorial process
  
  "content": {
    "format": "markdown|html", // Content markup type - allows flexibility between markdown and HTML editing
    "body": "string", // Main post content - the primary text of the blog post
    "media_attachments": [ // Embedded media files - separates content from media for better management
      {
        "id": "string", // Unique media identifier - enables media reuse and reference tracking
        "type": "image|video|audio|document", // Media classification - helps with rendering and processing
        "url": "string", // Storage location/CDN path - actual file location for serving
        "alt_text": "string", // Accessibility description - required for screen readers and SEO
        "caption": "string", // Display caption - shown below media for context
        "file_size": "number", // File size in bytes - useful for bandwidth management and UI
        "mime_type": "string" // File format specification - enables proper handling and validation
      }
    ]
  },
  
  "featured_image": { // Main post image - used in previews, social shares, and post headers
    "url": "string", // Image location - primary display image
    "alt_text": "string", // Accessibility text - SEO and screen reader support
    "caption": "string" // Image description - displayed context for readers
  },
  
  "author": { // Post creator information - attribution and user management
    "user_id": "string", // Internal user reference - links to user management system
    "name": "string", // Display name - shown publicly on posts
    "avatar_url": "string" // Author image - personalizes posts and improves engagement
  },
  
  "creation_date": "ISO_8601_datetime", // When post was first created - important for sorting and history
  "publish_date": "ISO_8601_datetime", // When post went live - can differ from creation, affects SEO and feeds
  "last_modified": "ISO_8601_datetime", // Most recent edit timestamp - shows freshness and update frequency
  
  //in seperate table
  "revision_history": [ // Version control system - enables rollbacks and change tracking
    {
      "version": "number", // Sequential version number - tracks progression of changes
      "timestamp": "ISO_8601_datetime", // When this version was created - audit trail
      "user_id": "string", // Who made the changes - accountability and attribution
      "changes_summary": "string", // Description of modifications - helps with reviewing changes
      "previous_version_id": "string" // Link to prior version - enables rollback functionality
    }
  ],
  
  "expiration": { // Automatic content lifecycle management
    "expires_at": "ISO_8601_datetime", // When content should expire - useful for time-sensitive posts
    "auto_archive": "boolean" // Whether to auto-archive on expiry - prevents manual cleanup overhead
  },
  
  "permissions": { // Comprehensive access control and security
    "read": { // Who can view this post
      "type": "public|role_based|user_based|password|private", // Access control method
      "roles": ["string"], // Role names that can read (e.g., "subscriber", "premium", "admin")
      "users": ["string"] // Specific user IDs with read access
    },
    "write": { // Who can edit this post
      "type": "author_only|role_based|user_based", // Edit permission model
      "roles": ["string"], // Roles that can edit (e.g., "editor", "admin")
      "users": ["string"] // Specific users who can edit beyond the author
    },
    "delete": { // Who can delete this post
      "type": "author_only|role_based|user_based", // Deletion permission model
      "roles": ["string"], // Roles that can delete (typically "admin", "moderator")
      "users": ["string"] // Specific users who can delete
    },
    "moderate": { // Who can manage comments, reports, etc.
      "roles": ["string"], // Roles with moderation powers on this post
      "users": ["string"] // Specific moderators for this post
    },
    "inherit_from_category": "boolean" // Whether to use category-level permissions as base
  },
  
  "taxonomy": { // Content organization and discoverability
    "categories": ["string"], // Broad topic classification - hierarchical content grouping
    "tags": ["string"] // Specific keywords - granular content labeling and search optimization
  },
  
  //not really relevant for our usecase
  "seo": { // Search engine optimization - critical for discoverability
    "meta_title": "string", // Custom title tag - optimized for search results (different from display title)
    "meta_description": "string", // Search snippet text - influences click-through rates
    "keywords": ["string"] // Target search terms - helps with content strategy
  },
  
  //move to extra table
  "analytics": { // Engagement metrics - measures post performance and user interaction
    "view_count": "number", // Page views - basic traffic metric
    "like_count": "number",
    "dislike_count": "number", // User appreciation - engagement indicator
    "comment_count": "number", // Discussion level - community engagement measure
  },
  
  "settings": { // Post behavior configuration - controls interactive features
    "allow_comments": "boolean", // Enable discussion - community engagement control
    "featured": "boolean", // Highlight important posts - editorial curation flag
    "sticky": "boolean" // Pin to top of listings - ensures important content visibility
  }
}
```
