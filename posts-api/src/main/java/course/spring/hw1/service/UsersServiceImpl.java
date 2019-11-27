package course.spring.hw1.service;

import course.spring.hw1.bindings.UserResponse;
import course.spring.hw1.dao.UsersRepository;
import course.spring.hw1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

   @Autowired
   private UsersRepository repo;

   @Autowired
   private PasswordEncoder encoder;

   @Override
   public List<UserResponse> findAll() {
      List<UserResponse> userDetails = new ArrayList<>();
      List<User> users = repo.findAll();
      if (users != null) {
         userDetails = users.stream().map(UserResponse::new).collect(Collectors.toList());
      }
      return userDetails;
   }

   @Override
   public User insert(User user) {
      String encodedPassword = encoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
      repo.insert(user);
      return user;
   }

   @Override
   public User update(User user) {
      String encodedPassword = encoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
      return repo.save(user);
   }

   @Override
   public User findUserById(String id) {
      Optional<User> user = repo.findById(id);
      if (!user.isPresent()) {
         throw new NoSuchElementException("Failed to find user. Invalid id " + id);
      }
      repo.save(user.get());
      return user.get();
   }

   @Override
   public User findUserByEmail(String email) {
      return repo.findByEmail(email);
   }

   @Override
   public User delete(String id) {
      Optional<User> user = repo.findById(id);
      if (!user.isPresent()) {
         throw new NoSuchElementException("Failed to delete user. Invalid id " + id);
      }
      repo.delete(user.get());
      return user.get();
   }
}
