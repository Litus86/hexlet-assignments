package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
      ///  System.setProperty("spring.devtools.restart.enabled", "true");
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/")
    String home() {
        return "Hello World!";
    }
    @GetMapping("/about")
    String about() {
        return "Welcome to Hexlet!";
    }
}
// END
