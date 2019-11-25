package course.spring.restMvclab8.domain;

import course.spring.restMvclab8.dao.ArticlesRepository;
import course.spring.restMvclab8.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticlesService {

   @Autowired
   private ArticlesRepository repository;

   @Override
   public List<Article> findAll() {
      return repository.findAll();
   }

   @Override
   public Article findById(String id) {
      return repository.findById(id).get();
   }

   @Override
   public Article add(Article article) {
      return repository.insert(article);
   }

   @Override
   public Article update(Article article) {
      return repository.save(article);
   }

   @Override
   public Article remove(Article article) {
      repository.delete(article);
      return article;
   }
}
