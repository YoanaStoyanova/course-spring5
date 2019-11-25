package course.spring.restMvclab8.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User implements UserDetails {

   public User(String name, String password){
      this.name = name;
      this.password = password;
   }

   private String id;

   private String name;

   private String lastName;

   private String email;

   @JsonIgnore
   private String password;

   @JsonIgnore
   private Role role;

   private URL photoUrl;

   @JsonIgnore
   private boolean active = true;

   @Override
   @JsonIgnore
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

enum Role {
   ROLE_BLOGGER,
   ROLE_ADMIN
}
