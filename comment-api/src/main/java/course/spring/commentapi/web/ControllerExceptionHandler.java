package course.spring.commentapi.web;

import course.spring.commentapi.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice("course.spring.commentapi.web")
public class ControllerExceptionHandler {

   @ExceptionHandler
   public ResponseEntity<ErrorResponse> handleExceptions(IllegalArgumentException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));

   }

   @ExceptionHandler
   public ResponseEntity<ErrorResponse> handleException(NoSuchElementException ex){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
   }

}
