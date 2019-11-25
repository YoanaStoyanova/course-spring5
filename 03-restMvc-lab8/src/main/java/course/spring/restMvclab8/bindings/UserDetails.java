package course.spring.restMvclab8.bindings;

import course.spring.restMvclab8.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDetails {

   public UserDetails(User user) {
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

   private URL photoUrl;
}