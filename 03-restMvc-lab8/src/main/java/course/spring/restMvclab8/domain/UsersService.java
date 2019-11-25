package course.spring.restMvclab8.domain;

import course.spring.restMvclab8.bindings.UserDetails;
import course.spring.restMvclab8.model.User;

import java.util.List;

public interface UsersService {

   List<UserDetails> findAll();

   User insert(User user);

   User update(User user);

   User findUserById(String id);

   User findUserByEmail(String email);
}
