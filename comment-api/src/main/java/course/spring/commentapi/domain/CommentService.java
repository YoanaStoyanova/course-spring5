package course.spring.commentapi.domain;

import course.spring.commentapi.model.Comment;

import java.util.List;

public interface CommentService {

   List<Comment> findAll();

   Comment addComment(Comment comment);

   Comment findById(String id);

   Comment update(Comment comment);

   Comment delete(String id);

}
