package course.spring.hw1.service;

import course.spring.hw1.bindings.PostResponse;
import course.spring.hw1.model.Post;

import java.util.List;

public interface PostsService {

   List<PostResponse> findAll();

   Post insert(Post post);

   Post update(Post post);

   Post findPostById(String id);

   Post delete(String id);

}
