package course.spring.restMvclab8.web;

import course.spring.restMvclab8.domain.ArticlesService;
import course.spring.restMvclab8.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {

   @Autowired
   private ArticlesService articlesService;

   @GetMapping()
   public List<Article> getAllArticles() {
      return articlesService.findAll();
   }

   @PostMapping
   public ResponseEntity addArticle(@RequestBody Article article,
                                    UriComponentsBuilder uriBuilder) {
      Article created = articlesService.add(article);
      return ResponseEntity.created(uriBuilder.pathSegment("{id}").build(created.getId()))
            .body(created);
   }



}
