package course.spring.hw1.service;

import course.spring.hw1.bindings.UserResponse;
import course.spring.hw1.model.User;

import java.util.List;

public interface UsersService {

   List<UserResponse> findAll();

   User insert(User user);

   User update(User user);

   User findUserById(String id);

   User findUserByEmail(String email);

   User delete(String id);
}
