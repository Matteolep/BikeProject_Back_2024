package Packageapp.services;

import Packageapp.exceptions.NotFoundException;
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

    public List<Parc> getAllParcs() {
        return this.parcRepository.findAll();
    }

    public Parc updateParc(Parc parc) throws DBException,NotFoundException {
        Parc existing;

        if (parc.getID() != null){
            existing = this.parcRepository.findById(parc.getID()).orElse(null);
            if (existing == null) throw new NotFoundException("Could not find Geoloc with id : " + parc.getID());
        } else {
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

    public void deleteParc(Long id) throws NotFoundException, DBException {
        Parc existing = this.parcRepository.findById(id).orElse(null);
        if (existing == null) throw new NotFoundException("Can't find parc with id :" + id);
        try {
            this.parcRepository.delete(existing);
        } catch (Exception e) {
            throw new DBException("Error with DB");
        }
    }
}
