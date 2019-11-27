package course.spring.hw1.dao;

import course.spring.hw1.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, String> {

   public User findByEmail(String email);
}
