package course.spring.hw1.web;

import course.spring.hw1.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@ControllerAdvice("course.spring.hw1.web")
public class ControllerExceptionHandler {

   @ExceptionHandler
   public ResponseEntity<ErrorResponse> handleExceptions(IllegalArgumentException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));

   }

   @ExceptionHandler
   public ResponseEntity<ErrorResponse> handleException(NoSuchElementException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
   }

   @ExceptionHandler
   public ResponseEntity<?> handleException(AccessDeniedException ex, HttpServletRequest request, HttpServletResponse response) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
         new PortResolverImpl().getServerPort(request);
         request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST", new DefaultSavedRequest(request, new PortResolverImpl()));
         HttpHeaders headers = new HttpHeaders();
         headers.add("Location", "/login");
         return new ResponseEntity<String>(headers, HttpStatus.PERMANENT_REDIRECT);
      }
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            new ErrorResponse(HttpStatus.FORBIDDEN, "Url is forbidden"));
   }

}
