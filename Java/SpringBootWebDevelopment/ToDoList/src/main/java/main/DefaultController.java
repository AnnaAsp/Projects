package main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/")
    public double index() {
        double randomNumber = Math.random();
        return randomNumber;
    }
}
