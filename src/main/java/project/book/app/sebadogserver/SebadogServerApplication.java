package project.book.app.sebadogserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SebadogServerApplication {

    @GetMapping("hello")
    public ResponseEntity hello() {
        return ResponseEntity.ok("hello world");
    }
    public static void main(String[] args) {
        SpringApplication.run(SebadogServerApplication.class, args);
    }

}
