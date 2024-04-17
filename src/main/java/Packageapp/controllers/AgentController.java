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
@RequiredArgsConstructor
@Slf4j
public class AgentController {

    private final AgentService agentService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity<List<Agent>> getAgents() {
        return new ResponseEntity<>(this.agentService.getAllAgents(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Agent> postAgent(@RequestBody Agent agentSent){
        try {
            log.info("Creating Agent ... ");
            return  agentSent.getID() == null ?
                    new ResponseEntity<>(this.agentService.updateAgent(agentSent), HttpStatus.CREATED) :
                    new ResponseEntity<>(this.agentService.updateAgent(agentSent), HttpStatus.ACCEPTED);

        }   catch (DBException e){
            //erreur 500
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NotFoundException e) {
            //erreur 404 quand id existe pas
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{ID}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long ID) {
        // Check if id is null
        if (ID == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            this.agentService.deleteAgent(ID);
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


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}/Prenom")
    public String getPrenomAgentById(@PathVariable Long id) {
        try {
            return agentService.getPrenomById(id);
        } catch (NotFoundException e) {
            throw new RuntimeException("Agent introuvable avec l'ID : " + id);
        }
    }


}
