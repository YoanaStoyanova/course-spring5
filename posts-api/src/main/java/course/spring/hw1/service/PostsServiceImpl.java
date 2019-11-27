package course.spring.hw1.service;

import course.spring.hw1.bindings.PostResponse;
import course.spring.hw1.dao.PostsRepository;
import course.spring.hw1.model.Post;
import course.spring.hw1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostsServiceImpl implements PostsService {

   @Autowired
   private PostsRepository repo;

   @Autowired
   private UsersService usersService;

   @Override
   public List<PostResponse> findAll() {
      List<PostResponse> postsResponse = new ArrayList<>();
      List<Post> posts = repo.findAll();
      if (posts != null) {
         postsResponse = posts.stream().map(post -> {
            setPostAuthor(post);
            return new PostResponse(post);
         }).collect(Collectors.toList());
      }
      return postsResponse;
   }

   @Override
   public Post insert(Post post) {
      User currentUser = getCurrentUser();
      if (currentUser != null) {
         post.setUserId(currentUser.getId());
         post.setAuthor(currentUser.getName() + " " + currentUser.getLastName());
         return repo.insert(post);
      } else {
         throw new NoSuchElementException("Failed to add new post. Invalid user");
      }
   }

   @Override
   public Post update(Post post) {
      User currentUser = getCurrentUser();

      if (currentUser != null) {
         post.setUserId(currentUser.getId());
         post.setAuthor(currentUser.getName() + " " + currentUser.getLastName());
         return repo.save(post);
      } else {
         throw new NoSuchElementException("Failed to update post. Invalid user");
      }

   }

   @Override
   public Post findPostById(String id) {
      Post post = repo.findById(id).get();
      return setPostAuthor(post);
   }


   @Override
   public Post delete(String id) {
      Optional<Post> user = repo.findById(id);
      if (!user.isPresent()) {
         throw new NoSuchElementException("Failed to delete post. Invalid id " + id);
      }
      repo.delete(user.get());
      return user.get();
   }

   private User getCurrentUser() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication != null && authentication.getPrincipal() instanceof User) {
         return (User) authentication.getPrincipal();
      }
      return null;
   }

   private Post setPostAuthor(Post post) {
      try {
         User userById = usersService.findUserById(post.getUserId());
         post.setAuthor(userById.getName() + " " + userById.getLastName());
      }catch (Exception e){
         System.out.println("failed to update author");
      }

      return post;
   }

}
