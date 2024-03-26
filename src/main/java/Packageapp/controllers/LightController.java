package Packageapp.controllers;

import Packageapp.exceptions.DBException;
import Packageapp.exceptions.NotFoundException;
import Packageapp.models.Light;
import Packageapp.services.LightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lights")
@RequiredArgsConstructor
@Slf4j
public class LightController {

    private final LightService lightService;

    @GetMapping
    public ResponseEntity<List<Light>> getLights() {
        return new ResponseEntity<>(this.lightService.getAllLights(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Light> postLight(@RequestBody Light lightSent) {
        try {
            log.info("Creating light ...");
            // La condition ternaire permet de changer le code de retour en fonction du "mode" voulu
            return lightSent.getId() == null ?
                    new ResponseEntity<>(this.lightService.updateLight(lightSent), HttpStatus.CREATED) :
                    new ResponseEntity<>(this.lightService.updateLight(lightSent), HttpStatus.ACCEPTED);
        } catch (DBException e) {
            // Erreur 500
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            // Erreur 404 lorsque l'id de l'objet qu'on veut modifier n'existe pas en base
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLight(@PathVariable Long id) {
        // Check if id is null
        if (id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            this.lightService.deleteLight(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DBException e) {
            // Erreur 500
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            // Erreur 404 lorsque l'id de l'objet qu'on veut modifier n'existe pas en base
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
