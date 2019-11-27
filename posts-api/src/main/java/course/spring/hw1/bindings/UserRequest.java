package course.spring.hw1.bindings;

import course.spring.hw1.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequest {

   private String name;

   private String lastName;

   private String email;

   private String password;

   private Role role;

   private String photoUrl;

}
