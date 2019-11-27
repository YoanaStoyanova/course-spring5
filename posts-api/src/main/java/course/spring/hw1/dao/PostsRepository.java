package course.spring.hw1.dao;

import course.spring.hw1.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostsRepository extends MongoRepository<Post, String> {
}
