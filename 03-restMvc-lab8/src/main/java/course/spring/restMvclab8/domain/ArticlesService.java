package course.spring.restMvclab8.domain;

import course.spring.restMvclab8.model.Article;

import java.util.List;

public interface ArticlesService {
   List<Article> findAll();
   Article findById(String id);
   Article add(Article article);
   Article update(Article article);
   Article remove(Article article);
}
