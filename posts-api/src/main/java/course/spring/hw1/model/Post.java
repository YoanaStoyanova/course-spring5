package course.spring.hw1.model;

import course.spring.hw1.bindings.PostRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(value = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {

   @Id
   private String id;

   private LocalDateTime published = LocalDateTime.now();

   private String title;

   private String author;

   private String userId;

   private String content;

   private List<String> tags;

   private String pictureUrl;

   private Status status;

   public Post(PostRequest postRequest) {
      this.title = postRequest.getTitle();
      this.content = postRequest.getContent();
      this.tags = postRequest.getTags();
      this.pictureUrl = postRequest.getPictureUrl();
      this.status = postRequest.getStatus();
   }

}


