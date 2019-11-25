package course.spring.restMvclab8.dao;

import course.spring.restMvclab8.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, String> {

   public User findByEmail(String email);
}
