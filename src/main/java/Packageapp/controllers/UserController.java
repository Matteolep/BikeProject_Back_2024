package Packageapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Users")
public class UserController {

    @GetMapping()
    public ResponseEntity<String> helloWorld() {
        return new ResponseEntity<>("Hello-World !", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/2")
    public String helloWorld2() {

        return null;
    }
}
