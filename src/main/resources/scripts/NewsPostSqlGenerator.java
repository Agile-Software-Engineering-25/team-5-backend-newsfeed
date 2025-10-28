public class NewsPostSqlGenerator {

    public static String generateInsert(NewsPost post) {
        return String.format(
            "INSERT INTO news_posts " +
            "(id, title, summary, status, creation_date, publish_date, last_modified) " +
            "VALUES ('%s','%s','%s','%s','%s','%s','%s');",
            post.getId(),
            post.getTitle().replace("'", "''"),
            post.getSummary().replace("'", "''"),
            post.getStatus().name(),
            post.getCreation_date(),
            post.getPublishDate(),
            post.getLastModified()
        );
    }

    public static String generatePermissionsInsert(NewsPost post) {
        StringBuilder sb = new StringBuilder();
        for (String perm : post.getPermissions()) {
            sb.append(String.format(
                "INSERT INTO newspost_permissions (id, permission) VALUES ('%s','%s');%n",
                post.getId(), perm
            ));
        }
        return sb.toString();
    }
}
