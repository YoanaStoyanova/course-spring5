package course.spring.hw1.web;

import course.spring.hw1.bindings.UserRequest;
import course.spring.hw1.bindings.UserResponse;
import course.spring.hw1.model.User;
import course.spring.hw1.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
   @PreAuthorize("hasRole('ADMIN')")
   public List<UserResponse> findAll() {
      return usersService.findAll();
   }

   @PostMapping
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity addUser(@RequestBody UserRequest userRequest, UriComponentsBuilder uriBuilder) {
      User newUser = new User(userRequest);
      newUser = usersService.insert(newUser);
      UserResponse userResponse = new UserResponse(newUser);
      return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").build(newUser.getId()))
            .body(userResponse);
   }

   @GetMapping("{id}")
   @PreAuthorize("hasRole('ADMIN') or ( hasRole('BLOGGER') and authentication.principal.id == #id)")
   public UserResponse findUserById(@PathVariable("id") String id) {
      return new UserResponse(usersService.findUserById(id));
   }

   @PutMapping("{id}")
   @PreAuthorize("hasRole('ADMIN') or ( hasRole('BLOGGER') and authentication.principal.id == #id)")
   public UserResponse updateUser(@PathVariable("id") String id, @RequestBody UserRequest userRequest) {
      User toUpdate = new User(userRequest);
      usersService.findUserById(id);
      toUpdate.setId(id);
      return new UserResponse(usersService.update(toUpdate));
   }

   @DeleteMapping("{id}")
   @PreAuthorize("hasRole('ADMIN') or ( hasRole('BLOGGER') and authentication.principal.id == #id)")
   public UserResponse deleteUser(@PathVariable String id) {
      return new UserResponse(usersService.delete(id));
   }


}
