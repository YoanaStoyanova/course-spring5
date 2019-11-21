package course.spring.commentapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comment")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

   @Id
   private String id;

   private String url;

   private String authorEmail;

   private String commentText;

   public void setId(String id) {
      this.id = id;
   }

}
