package course.spring.restMvclab8.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post {

   @Id
   private String id;

   private LocalDateTime published;

   private String title;

   private String author;

   private String text;

   private List<String> tags;

   private URL pictureUrl;

   private Status status;

}

enum Status {
   ACTIVE,
   INACTIVE
}
