package Packageapp.controllers;

import Packageapp.exceptions.DBException;
import Packageapp.exceptions.NotFoundException;
import Packageapp.models.Geoloc;
import Packageapp.services.GeolocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Geolocs")
@RequiredArgsConstructor
@Slf4j
public class GeolocController {

    private final GeolocService geolocService;
    @GetMapping
    public ResponseEntity<List<Geoloc>> getGeolocs() {
        return new ResponseEntity<>(this.geolocService.getAllGeolocs(), HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<Geoloc> postGeoloc(@RequestBody Geoloc geolocSent) {
        try {
            log.info("Creating light ...");
            // La condition ternaire permet de changer le code de retour en fonction du "mode" voulu
            return geolocSent.getID() == null ?
                    new ResponseEntity<>(this.geolocService.updategeoloc(geolocSent), HttpStatus.CREATED) :
                    new ResponseEntity<>(this.geolocService.updategeoloc(geolocSent), HttpStatus.ACCEPTED);
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
    public ResponseEntity<Void> deleteLight(@PathVariable Long ID) {
        // Check if id is null
        if (ID == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            this.geolocService.deleteGeoloc(ID);
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
