package course.spring.restMvclab8.domain;

import course.spring.restMvclab8.bindings.UserDetails;
import course.spring.restMvclab8.dao.UsersRepository;
import course.spring.restMvclab8.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

   @Autowired
   private UsersRepository repo;


   @Override
   public List<UserDetails> findAll() {
      List<UserDetails> userDetails = new ArrayList<>();
      List<User> users = repo.findAll();
      if (users != null) {
         userDetails = users.stream().map(UserDetails::new).collect(Collectors.toList());
      }
      return userDetails;
   }

   @Override
   public User insert(User user) {
      repo.insert(user);
      return user;
   }

   @Override
   public User update(User user) {
      return repo.save(user);
   }

   @Override
   public User findUserById(String id) {
      return repo.findById(id).get();
   }

   @Override
   public User findUserByEmail(String email) {
      return repo.findByEmail(email);
   }
}
