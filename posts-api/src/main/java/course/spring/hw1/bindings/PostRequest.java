package course.spring.hw1.bindings;

import course.spring.hw1.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostRequest {

   private String title;

   private String content;

   private List<String> tags;

   String pictureUrl;

   Status status = Status.ACTIVE;
}
