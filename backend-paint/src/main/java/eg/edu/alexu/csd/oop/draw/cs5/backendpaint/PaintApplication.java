package eg.edu.alexu.csd.oop.draw.cs5.backendpaint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin
public class PaintApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaintApplication.class, args);
	}

}
