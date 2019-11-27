package course.spring.hw1.bindings;

import course.spring.hw1.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponse {

   public UserResponse(User user) {
      this.id = user.getId();
      this.name = user.getName();
      this.lastName = user.getLastName();
      this.email = user.getEmail();
      this.photoUrl = user.getPhotoUrl();
   }

   private String id;

   private String name;

   private String lastName;

   private String email;

   private String photoUrl;

}