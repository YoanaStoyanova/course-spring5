package course.spring.commentapi.web;

import course.spring.commentapi.domain.CommentService;
import course.spring.commentapi.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/comments")
public class CommentsController {

   @Autowired
   private CommentService commentService;

   @GetMapping
   public List<Comment> getAllComments() {
      return commentService.findAll();
   }

   @PostMapping
   public ResponseEntity addComment(@RequestBody Comment comment, UriComponentsBuilder uriBuilder) {
      Comment created = commentService.addComment(comment);
      return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").build(created.getId()))
            .body(created);

   }

   @GetMapping("{id}")
   public Comment getCommentById(@PathVariable("id") String id) {
      Comment comment = commentService.findById(id);
      if (comment == null) {
         throw new IllegalArgumentException("Failed to get comment by id=" + id + " Invalid id.");
      }
      return comment;
   }

   @PutMapping("{id}")
   public Comment update(@PathVariable("id") String id, @RequestBody Comment comment) {
      if (!id.equals(comment.getId()) || commentService.findById(id) == null) {
         throw new IllegalArgumentException("Invalid or non matching ids urlPathId" + id + " requestBodyId: " + comment.getId());
      }
      return commentService.update(comment);
   }

   @DeleteMapping("{id}")
   public Comment deleteComment(@PathVariable("id") String id) {
      return commentService.delete(id);
   }
}
