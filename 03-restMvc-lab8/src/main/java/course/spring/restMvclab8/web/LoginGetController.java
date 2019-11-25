package course.spring.restMvclab8.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginGetController {

   @GetMapping
   public String getLoginPage(){
      return "This is login page";
   }
}
