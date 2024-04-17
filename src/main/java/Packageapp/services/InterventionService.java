package Packageapp.services;

import Packageapp.exceptions.NotFoundException;
import Packageapp.models.Agent;
import Packageapp.models.Parc;
import Packageapp.repositories.AgentRepository;
import Packageapp.repositories.InterventionRepository;
import Packageapp.exceptions.DBException;
import Packageapp.models.Intervention;
import Packageapp.repositories.ParcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class InterventionService {

    private final InterventionRepository interventionRepository;

    private final AgentRepository agentRepository;
    private final ParcRepository parcRepository;


    public List<Intervention> getAllInterventions() {
        return this.interventionRepository.findAll();
    }

    public Intervention updateIntervention(Intervention intervention) throws DBException, NotFoundException {

        if (intervention.getID() != null) {
            throw new NotFoundException("L'intervention existe déjà : " + intervention.getID());
        } else {
            // On veut créer une intervention
            Intervention newIntervention = new Intervention();
            newIntervention.setAgent_ID(intervention.getAgent_ID());
            newIntervention.setParc_ID(intervention.getParc_ID());

            // On récupère les objets Agent et Parc correspondants aux ID fournis
            Agent agent = agentRepository.findById(intervention.getAgent_ID()).orElse(null);
            Parc parc = parcRepository.findById(intervention.getParc_ID()).orElse(null);

            // On vérifie que les objets Agent et Parc existent bien
            if (agent == null || parc == null) {
                throw new NotFoundException("L'agent ou le parc n'existe pas");
            }
            agent.setIsBusy(true);
            parc.setStatut(false);
            parc.setNbdispo(0);


            // On affecte les objets Agent et Parc à l'intervention
            newIntervention.setAgent_ID(agent.getID());
            newIntervention.setParc_ID(parc.getID());

            try {
                // On sauvegarde l'intervention en BDD

                return this.interventionRepository.save(newIntervention);

            } catch (Exception e) {
                throw new DBException("Could not create intervention");
            }
        }
    }



    public void deleteIntervention(Long id) throws NotFoundException, DBException {
        Intervention existing = this.interventionRepository.findById(id).orElse(null);
        if (existing == null) throw new NotFoundException("Can't find intervention with id :" + id);

        // Récupérer l'agent lié à l'intervention
        Agent agent = this.agentRepository.findById(existing.getAgent_ID()).orElse(null);
        if (agent == null) throw new NotFoundException("Can't find agent with id :" + existing.getAgent_ID());

        // Récupérer le parc lié à l'intervention
        Parc parc = this.parcRepository.findById(existing.getParc_ID()).orElse(null);
        if (parc == null) throw new NotFoundException("Can't find parc with id :" + existing.getParc_ID());

        // Mettre à jour le champ is_busy de l'agent
        agent.setIsBusy(false);
        this.agentRepository.save(agent);

        // Mettre à jour le champ statut du parc
        parc.setStatut(true);
        parc.setNbdispo(parc.getNbtot());
        this.parcRepository.save(parc);

        try {
            // Supprimer l'intervention de la base de données
            this.interventionRepository.delete(existing);
        } catch (Exception e) {
            throw new DBException("Error with DB");
        }
    }


}
