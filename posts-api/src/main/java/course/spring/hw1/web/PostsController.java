package course.spring.hw1.web;

import course.spring.hw1.bindings.PostRequest;
import course.spring.hw1.bindings.PostResponse;
import course.spring.hw1.model.Post;
import course.spring.hw1.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostsController {

   @Autowired
   private PostsService postsService;

   @GetMapping
   public List<PostResponse> findAll(){
      return postsService.findAll();
   }


   @PostMapping
   public ResponseEntity addPost(@RequestBody PostRequest postRequest, UriComponentsBuilder uriBuilder) {
      Post newPost = new Post(postRequest);
      newPost = postsService.insert(newPost);
      PostResponse postResponse = new PostResponse(newPost);
      return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").build(newPost.getId()))
            .body(postResponse);
   }

   @GetMapping("{id}")
   public PostResponse findPostById(@PathVariable("id") String id) {
      return new PostResponse(postsService.findPostById(id));
   }

   @PutMapping("{id}")
   @PreAuthorize("hasRole('ADMIN') or hasPermission(#id, 'isAuthor')")
   public PostResponse updatePost(@PathVariable("id") String id, @RequestBody PostRequest postRequest) {
      Post toUpdate = new Post(postRequest);
      postsService.findPostById(id);
      toUpdate.setId(id);
      return new PostResponse(postsService.update(toUpdate));
   }

   @DeleteMapping("{id}")
   @PreAuthorize("hasRole('ADMIN') or hasPermission(#id, 'isAuthor')")
   public PostResponse deletePost(@PathVariable String id) {
      return new PostResponse(postsService.delete(id));
   }
}
