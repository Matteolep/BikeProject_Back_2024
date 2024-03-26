package fr.cszw.td_devwebavance.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    // Récupération de toutes les lampes
    @GetMapping()
    public ResponseEntity<String> helloWorld() {
        return new ResponseEntity<>("Hello-World !", HttpStatus.NOT_FOUND);
    }


    // Update OU Création d'une lampe
    @GetMapping("/2")
    public String helloWorld2() {
        return "Hello-World !";
    }

}
