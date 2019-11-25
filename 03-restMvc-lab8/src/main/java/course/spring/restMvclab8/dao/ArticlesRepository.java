package course.spring.restMvclab8.dao;

import course.spring.restMvclab8.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticlesRepository extends MongoRepository<Article, String> {
}
