package course.spring.restMvclab8.web;

import course.spring.restMvclab8.bindings.UserDetails;
import course.spring.restMvclab8.domain.UsersService;
import course.spring.restMvclab8.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

   @Autowired
   private UsersService usersService;

   @GetMapping
   public List<UserDetails> findAll() {
      return usersService.findAll();
   }

   @PostMapping
   public ResponseEntity addUser(@RequestBody User user, UriComponentsBuilder uriBuilder) {
      User created = usersService.insert(user);
      return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").build(created.getId()))
            .body(created);
   }

   @GetMapping("{id}")
   public User findUserById(@PathVariable("id") String id) {
      return usersService.findUserById(id);
   }

   @PutMapping("{id}")
   public User updateUser(@PathVariable("id") String id, User user) {
      if (!id.equals(user.getId()) || usersService.findUserById(id) == null) {
         throw new IllegalArgumentException("Invalid or non matching ids requestPathId: " + id + " requestBodyId: " + user.getId());
      }
      return usersService.update(user);
   }


}
