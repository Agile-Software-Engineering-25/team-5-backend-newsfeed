public class GenerateMockData {

    public static void main(String[] args) {
        // Generate one post
        NewsPost post = NewsPostMockFactory.generateMockPost();

        // Print SQL for main table
        System.out.println(NewsPostSqlGenerator.generateInsert(post));

        // Print SQL for permissions table
        System.out.println(NewsPostSqlGenerator.generatePermissionsInsert(post));
    }
}