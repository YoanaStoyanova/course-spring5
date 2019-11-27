package course.spring.hw1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

   @Override
   protected MethodSecurityExpressionHandler createExpressionHandler(){
      DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
      expressionHandler.setPermissionEvaluator(postsPermissionEvaluator());
      return expressionHandler;
   }

   @Bean
   public PostsPermissionEvaluator postsPermissionEvaluator(){
      return new PostsPermissionEvaluator();
   }


   @Bean
   public PasswordEncoder bCryptPasswordEncoder(){
      return new BCryptPasswordEncoder();
   }
}
