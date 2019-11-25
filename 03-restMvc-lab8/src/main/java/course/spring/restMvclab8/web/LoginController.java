package course.spring.restMvclab8.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import course.spring.restMvclab8.model.UserCredentials;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

//@RestController
//@RequestMapping("/api/login")
public class LoginController extends UsernamePasswordAuthenticationFilter {

   private final ObjectMapper objectMapper = new ObjectMapper();

   @GetMapping
   public String getLogin(){
      return "This is login page";
   }


   @Override
   public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
      String requestBody;
      try {
         requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
         UserCredentials authRequest = objectMapper.readValue(requestBody, UserCredentials.class);

         UsernamePasswordAuthenticationToken token
               = new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());

         // Allow subclasses to set the "details" property
         setDetails(request, token);

         return this.getAuthenticationManager().authenticate(token);
      } catch(IOException e) {

         throw new InternalAuthenticationServiceException("weww", e);
      }
   }

}
