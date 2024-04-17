package Packageapp.services;

import Packageapp.exceptions.NotFoundException;
import Packageapp.models.Geoloc;
import Packageapp.repositories.GeolocRepository;
import Packageapp.repositories.ParcRepository;
import Packageapp.exceptions.DBException;
import Packageapp.models.Parc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
@RequiredArgsConstructor
public class ParcService {

    private final ParcRepository parcRepository;
    private final GeolocRepository geolocRepository;

    public List<Parc> getAllParcs() {
        return this.parcRepository.findAll();
    }

    public Parc updateParc(Parc parc) throws DBException,NotFoundException {
        Parc existing;

        if (parc.getID() != null){
            existing = this.parcRepository.findById(parc.getID()).orElse(null);
            if (existing == null) throw new NotFoundException("Could not find Parc with id : " + parc.getID());
        } else {
            if (parc.getNbtot() < parc.getNbdispo()) throw new DBException("Nombre de places dispos superieur au nombre total");

            existing = new Parc();
        }

        existing.setNbtot(parc.getNbtot());
        existing.setNbdispo(parc.getNbdispo());
        existing.setGeoloc_ID(parc.getGeoloc_ID());
        existing.setStatut(parc.getStatut());


        try {
            return this.parcRepository.save(existing);
        }catch (Exception e){
            throw new DBException("Could not Create parc");
        }
    }

    public Parc getParcById(Long id) throws NotFoundException {
        return parcRepository.findById(id).orElseThrow(() -> new NotFoundException("Parc not found"));
    }

    public void deleteParc(Long id) throws NotFoundException, DBException {
        Parc existing = this.parcRepository.findById(id).orElse(null);
        if (existing == null) throw new NotFoundException("Can't find parc with id :" + id);

        //recup la geoloc liée à la localisation
        Geoloc geoloc = this.geolocRepository.findById(existing.getGeoloc_ID()).orElse(null);
        if (geoloc == null) throw new NotFoundException("Can't find geoloc with id :" + existing.getGeoloc_ID());

        try{
            this.geolocRepository.delete(geoloc);
            try {
                this.parcRepository.delete(existing);
            } catch (Exception e) {
                throw new DBException("Error with DB");
            }
        }catch (Exception e) {
            throw new DBException("Error with DB");
        }


    }
}
