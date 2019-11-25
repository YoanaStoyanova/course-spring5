package course.spring.restMvclab8.dao;

import course.spring.restMvclab8.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostsRepository extends MongoRepository<Post, String> {
}
