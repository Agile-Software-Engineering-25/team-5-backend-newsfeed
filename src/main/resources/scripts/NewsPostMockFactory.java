import com.github.javafaker.Faker;
import com.ase.newsfeedservice.components.NewsPost;
import com.ase.newsfeedservice.components.Embedded;
import java.time.OffsetDateTime;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class NewsPostMockFactory {

    private static final Faker faker = new Faker();

    public static NewsPost generateMockPost() {
        NewsPost post = new NewsPost();
        post.setId(UUID.randomUUID().toString());
        post.setTitle(faker.book().title());
        post.setSummary(faker.lorem().sentence());
        post.setStatus(NewsPost.NewsPostStatus.draft);

        Embedded.Content content = new Embedded.Content();
        content.setFormat(Embedded.Content.ContentFormat.html);
        content.setBody("<p>" + faker.lorem().paragraph() + "</p>");
        post.setContent(content);

        Embedded.FeaturedImage image = new Embedded.FeaturedImage();
        image.setUrl(faker.internet().url());
        image.setAltText(faker.lorem().words(3).toString());
        image.setCaption(faker.lorem().sentence());
        post.setFeaturedImage(image);

        Embedded.Author author = new Embedded.Author();
        author.setUserId(UUID.randomUUID().toString());
        author.setName(faker.name().fullName());
        author.setAvatarUrl(faker.internet().avatar());
        post.setAuthor(author);

        post.setCreation_date(OffsetDateTime.now());
        post.setPublishDate(OffsetDateTime.now().plusDays(faker.number().numberBetween(1, 30)));
        post.setLastModified(OffsetDateTime.now());

        Embedded.Expiration expiration = new Embedded.Expiration();
        expiration.setExpiresAt(LocalDateTime.now().plusDays(faker.number().numberBetween(31, 365)));
        expiration.setAutoArchive(true);
        post.setExpiration(expiration);

        post.setPermissions(Arrays.asList(
            "F2.Team-5.Read.NewsPost.Fachbereich",
            "F2.Team-5.Write.NewsPost.Admin"
        ));

        Embedded.Settings settings = new Embedded.Settings();
        settings.setFeatured(true);
        settings.setSticky(true);
        post.setSettings(settings);

        return post;
    }
}
