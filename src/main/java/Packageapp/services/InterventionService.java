package Packageapp.services;

import Packageapp.exceptions.NotFoundException;
import Packageapp.repositories.InterventionRepository;
import Packageapp.exceptions.DBException;
import Packageapp.models.Intervention;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class InterventionService {

    private final InterventionRepository interventionRepository;


    public List<Intervention> getAllInterventions() {
        return this.interventionRepository.findAll();
    }

    public Intervention updateIntervention(Intervention intervention) throws DBException, NotFoundException {
        Intervention existing;

        if (intervention.getID() != null) {
            // On veut modifier une intervention
            // findById renvoie un Optionnal, ce qui signifie qu'il faut rajouter une méthode afin de palier à tous
            //      les comportements possibles
            existing = this.interventionRepository.findById(intervention.getID()).orElse(null);
            // On check si l'object existe en base
            if (existing == null) throw new NotFoundException("Could not find intervention with id : " + intervention.getID());
        } else {
            // On veut créer une intervention
            existing = new Intervention();
        }

        // On modifie toutes les propriétés que l'on veut modifier de la lampe
        existing.setAgent_ID(intervention.getAgent_ID());
        existing.setParc_ID(intervention.getParc_ID());

        try {
            // On sauvegarde l'intervention en BDD
            return this.interventionRepository.save(existing);
        } catch (Exception e) {
            throw new DBException("Could not create intervention");
        }
    }

    public void deleteIntervention(Long id) throws NotFoundException, DBException {
        Intervention existing = this.interventionRepository.findById(id).orElse(null);
        if (existing == null) throw new NotFoundException("Can't find intervention with id :" + id);
        try {
            this.interventionRepository.delete(existing);
        } catch (Exception e) {
            throw new DBException("Error with DB");
        }
    }
}
