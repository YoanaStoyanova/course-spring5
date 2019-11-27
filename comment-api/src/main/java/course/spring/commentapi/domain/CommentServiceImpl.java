package course.spring.commentapi.domain;

import course.spring.commentapi.dao.CommentRepository;
import course.spring.commentapi.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

   @Autowired
   CommentRepository repo;

   @Override
   public List<Comment> findAll() {
      return repo.findAll();
   }

   @Override
   public Comment addComment(Comment comment) {
      comment.setId(null);
      return repo.insert(comment);
   }

   @Override
   public Comment findById(String id) {
      return repo.findById(id).get();
   }

   @Override
   public Comment update(Comment comment) {
      return repo.save(comment);
   }

   @Override
   public Comment delete(String commentId) {
      Optional<Comment> old = repo.findById(commentId);
      if (!old.isPresent()) {
         throw new IllegalArgumentException("Failed to delete comment. Invalid id " + commentId);
      }
      repo.deleteById(commentId);
      return old.get();
   }
}
