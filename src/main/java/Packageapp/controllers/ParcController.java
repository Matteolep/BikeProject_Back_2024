package Packageapp.controllers;

import Packageapp.exceptions.DBException;
import Packageapp.exceptions.NotFoundException;
import Packageapp.models.Geoloc;
import Packageapp.models.Parc;
import Packageapp.services.GeolocService;
import Packageapp.services.ParcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Parcs")
@RequiredArgsConstructor
@Slf4j
public class ParcController {

    private final ParcService parcService;
    private final GeolocService geolocService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity<List<Parc>> getParcs() {
        return new ResponseEntity<>(this.parcService.getAllParcs(), HttpStatus.OK);
    }
    // Update OU Création d'un parc
    @PostMapping
    public ResponseEntity<Parc> postParc(@RequestBody Parc parcSent) {
        try {
            log.info("Creating parc ...");
            return parcSent.getID() == null ?
                    new ResponseEntity<>(this.parcService.updateParc(parcSent),HttpStatus.CREATED):
                    new ResponseEntity<>(this.parcService.updateParc(parcSent),HttpStatus.ACCEPTED);



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
    public ResponseEntity<Void> deleteParc(@PathVariable Long ID) {
        // Check if id is null
        if (ID == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            this.parcService.deleteParc(ID);
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
