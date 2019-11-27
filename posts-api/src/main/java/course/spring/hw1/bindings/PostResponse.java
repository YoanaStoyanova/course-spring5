package course.spring.hw1.bindings;

import course.spring.hw1.model.Post;
import course.spring.hw1.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostResponse {

   public PostResponse(Post post) {
      this.title = post.getTitle();
      this.content = post.getContent();
      this.author = post.getAuthor();
      this.published = post.getPublished();
      this.tags = post.getTags();
      this.pictureUrl = post.getPictureUrl();
      this.status = post.getStatus();

   }

   public static void main(String[] args) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      System.out.println(encoder.encode("password").toString());
   }

   private String title;

   private String content;

   private String author;

   private LocalDateTime published;

   private List<String> tags;

   String pictureUrl;

   Status status;

}
