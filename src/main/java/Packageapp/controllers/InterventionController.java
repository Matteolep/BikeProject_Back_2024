package Packageapp.controllers;

import Packageapp.exceptions.DBException;
import Packageapp.exceptions.NotFoundException;
import Packageapp.models.Intervention;
import Packageapp.services.InterventionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Interventions")
@RequiredArgsConstructor
@Slf4j
public class InterventionController {

    private final InterventionService interventionService;
    @GetMapping()
    public ResponseEntity<List<Intervention>> getInterventions() {
        return new ResponseEntity<>(this.interventionService.getAllInterventions(), HttpStatus.OK);

    }

    // Update OU Création d'une intervention
    @PostMapping
    public ResponseEntity<Intervention> postIntervention(@RequestBody Intervention interventionSent) {
        try {
            log.info("Creating intervention ...");
            // La condition ternaire permet de changer le code de retour en fonction du "mode" voulu
            return interventionSent.getID() == null ?
                    new ResponseEntity<>(this.interventionService.updateIntervention(interventionSent), HttpStatus.CREATED) :
                    new ResponseEntity<>(this.interventionService.updateIntervention(interventionSent), HttpStatus.ACCEPTED);
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

    @DeleteMapping("{ID}")
    public ResponseEntity<Void> deleteIntervention(@PathVariable Long ID) {
        // Check if id is null
        if (ID == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            this.interventionService.deleteIntervention(ID);
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
