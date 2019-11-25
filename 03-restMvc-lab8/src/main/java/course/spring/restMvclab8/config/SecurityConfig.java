package course.spring.restMvclab8.config;

import course.spring.restMvclab8.domain.UsersService;
import course.spring.restMvclab8.security.RestAuthenticationEntryPoint;
import course.spring.restMvclab8.security.RestSavedRequestAwareAuthenticationSuccessHandler;
import course.spring.restMvclab8.web.LoginController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   private UsersService usersService;

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService(usersService)).passwordEncoder(NoOpPasswordEncoder.getInstance());
   }

   //
////   @Override
////   public void configure(HttpSecurity http) throws Exception {
////      http.csrf().disable()
////            .authorizeRequests()
////            .antMatchers(HttpMethod.POST, "/**").hasRole("USER")
////            .antMatchers(HttpMethod.PUT, "/**").hasRole("USER")
////            .antMatchers(HttpMethod.DELETE, "*/**").hasRole("USER")
////            .antMatchers("/**").permitAll()
////            .and()
////            .formLogin()
////            .and()
////            .httpBasic();
////   }
//
//   @Autowired
//   JdbcTemplate jdbcTemplate;
//
   @Autowired
   private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

   @Autowired
   private RestSavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

   @Bean
   public LoginController authenticationFilter() throws Exception {
      LoginController authenticationFilter
            = new LoginController();
      authenticationFilter.setAuthenticationSuccessHandler(mySuccessHandler());
      authenticationFilter.setAuthenticationFailureHandler(myFailureHandler());
      authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
      authenticationFilter.setAuthenticationManager(authenticationManagerBean());
      TokenBasedRememberMeServices rememberMeService = new TokenBasedRememberMeServices("key", userDetailsService(usersService));
      rememberMeService.setAlwaysRemember(true);
      authenticationFilter.setRememberMeServices(rememberMeService);
      return authenticationFilter;
   }

   //
//
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
            .antMatchers("/api/login").permitAll()
            .antMatchers("/api/logout").authenticated()
            .antMatchers("/api/users").hasRole("ADMIN")
          //  .antMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
            .antMatchers("/api/**").hasAnyRole("ADMIN", "USER", "BLOGGER")
            .and()
            .csrf().disable()
            .logout().logoutSuccessUrl("/api/login").logoutUrl("/api/logout").deleteCookies()
            .and()
            //.formLogin()
            .addFilterBefore(
                  authenticationFilter(),
                  UsernamePasswordAuthenticationFilter.class)
      ;
      //.successHandler(authenticationSuccessHandler)
      //.usernameParameter("email")
      //.passwordParameter("password");

//      http
//            .csrf().disable()
//            .exceptionHandling()
//            .authenticationEntryPoint(restAuthenticationEntryPoint)
//            .and()
//            .authorizeRequests()
//            .antMatchers("/actuator/info").permitAll()
//            .antMatchers("/actuator/health").permitAll()
//            .antMatchers("/v2/api-docs").permitAll()
//            .antMatchers("/swagger*/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/api/**").authenticated()
//            .antMatchers(HttpMethod.POST, "/**").hasAnyRole("USER", "ADMIN")
//            .antMatchers(HttpMethod.PUT).hasAnyRole("USER", "ADMIN")
//            .antMatchers(HttpMethod.DELETE).hasAnyRole("USER", "ADMIN")
//            .and()
//            .formLogin()
//            .permitAll()
//            .successHandler(authenticationSuccessHandler)
//            .failureHandler(new SimpleUrlAuthenticationFailureHandler())
//            .and()
//            .logout();
//                .and()
//                    .rememberMe();
   }

   //
//
   @Bean
   public RestSavedRequestAwareAuthenticationSuccessHandler mySuccessHandler() {
      return new RestSavedRequestAwareAuthenticationSuccessHandler();
   }

   @Bean
   public SimpleUrlAuthenticationFailureHandler myFailureHandler() {
      return new SimpleUrlAuthenticationFailureHandler();
   }
//
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        manager.createUser(User.withDefaultPasswordEncoder()
////                .username("user").password("user").roles("USER").build());
////        return manager;
//
////   @Bean
////   public UserDetailsService userDetailsService() {
////      JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
////      jdbcDao.setJdbcTemplate(jdbcTemplate);
////      jdbcDao.setAuthoritiesByUsernameQuery("select username, password, true as enabled from users where username=?;");
////      jdbcDao.setAuthoritiesByUsernameQuery("select username, role from users where username=?");
////      InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
////      userDetailsManager.createUser(User.withDefaultPasswordEncoder()
////            .username("user").password("user")
////            .roles("USER").build());
//    //  return jdbcDao;
//   //}

   @Bean
   UserDetailsService userDetailsService(UsersService usersService) {
      return usersService::findUserByEmail;
   }
}
