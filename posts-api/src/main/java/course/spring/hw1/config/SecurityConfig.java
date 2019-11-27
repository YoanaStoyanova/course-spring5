package course.spring.hw1.config;

import course.spring.hw1.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   private UsersService usersService;

   @Autowired
   private PasswordEncoder encoder;

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService(usersService)).passwordEncoder(encoder);
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/logout").authenticated()
            .antMatchers("/api/users").permitAll()
            .antMatchers("/api/**").hasAnyRole("ADMIN", "BLOGGER")
            .and()
            .logout().logoutSuccessUrl("/login").deleteCookies()
            .and()
            .csrf().disable()
            .formLogin()
            .defaultSuccessUrl("/api/posts");
   }

   @Bean
   UserDetailsService userDetailsService(UsersService usersService) {
      return usersService::findUserByEmail;
   }

}
