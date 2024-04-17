package Packageapp.controllers;

import Packageapp.exceptions.DBException;
import Packageapp.exceptions.NotFoundException;
import Packageapp.models.Agent;
import Packageapp.models.Intervention;
import Packageapp.models.Parc;
import Packageapp.services.AgentService;
import Packageapp.services.InterventionService;
import Packageapp.services.ParcService;
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
    private final ParcService parcService;
    private final AgentService agentService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity<List<Intervention>> getInterventions() {
        return new ResponseEntity<>(this.interventionService.getAllInterventions(), HttpStatus.OK);

    }

    // Update OU Création d'une intervention
    @PostMapping
    public ResponseEntity<Intervention> postIntervention(@RequestParam Long agentId, @RequestParam Long parcId) {
        try {
            log.info("Creating intervention ...");
            Parc parc = parcService.getParcById(parcId);
            Agent agent = agentService.getAgentById(agentId);
            Intervention intervention = new Intervention();
            intervention.setAgent_ID(agent.getID());
            intervention.setParc_ID(parc.getID());
            if (parc.getStatut() && !agent.getIsBusy()){
                intervention.setParc_ID(parc.getID());
                intervention.setAgent_ID(agent.getID());
                interventionService.updateIntervention(intervention);
                return new ResponseEntity<>(intervention, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }



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
