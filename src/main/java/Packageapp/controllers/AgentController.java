package Packageapp.controllers;

import Packageapp.exceptions.DBException;
import Packageapp.exceptions.NotFoundException;
import Packageapp.models.Agent;
import Packageapp.services.AgentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("Agents")
public class AgentController {

    @GetMapping()
    public ResponseEntity<String> helloWorld() {
        return new ResponseEntity<>("Hello-World !", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/2")
    public String helloWorld2() {

        return null;
    }


}
