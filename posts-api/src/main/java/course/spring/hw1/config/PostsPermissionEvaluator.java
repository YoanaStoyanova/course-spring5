package course.spring.hw1.config;

import course.spring.hw1.model.Post;
import course.spring.hw1.model.User;
import course.spring.hw1.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

public class PostsPermissionEvaluator implements PermissionEvaluator {

   @Autowired
   private PostsService postsService;

   @Override
   public boolean hasPermission(Authentication authentication, Object targetPostId, Object permission) {
      Object principal = authentication.getPrincipal();
      if (principal instanceof User) {
         User currentUser = (User) principal;
         Post post = postsService.findPostById((String) targetPostId);
         return post != null && post.getUserId().equals(currentUser.getId());
      }
      return false;
   }

   @Override
   public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
      return false;
   }
}
