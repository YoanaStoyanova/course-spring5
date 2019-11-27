package course.spring.hw1.model;


import course.spring.hw1.bindings.UserRequest;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Document(collection = "users")
@Data
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

   @Id
   private String id;

   private String name;

   private String lastName;

   private String email;

   private String password;

   private Role role;

   private String photoUrl;

   private boolean active = true;

   public User(UserRequest userRequest) {
      this.name = userRequest.getName();
      this.lastName = userRequest.getLastName();
      this.email = userRequest.getEmail();
      this.password = userRequest.getPassword();
      this.role = userRequest.getRole();
      this.photoUrl = userRequest.getPhotoUrl();
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return Collections.singleton(new SimpleGrantedAuthority(role.name()));
   }

   @Override
   public String getUsername() {
      return name;
   }

   @Override
   public boolean isAccountNonExpired() {
      return active;
   }

   @Override
   public boolean isAccountNonLocked() {
      return active;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return active;
   }

   @Override
   public boolean isEnabled() {
      return active;
   }

}
