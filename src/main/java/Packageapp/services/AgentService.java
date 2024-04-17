package Packageapp.services;

import Packageapp.exceptions.NotFoundException;
import Packageapp.models.Geoloc;
import Packageapp.models.Parc;
import Packageapp.repositories.AgentRepository;
import Packageapp.exceptions.DBException;
import Packageapp.models.Agent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentService {

    private final AgentRepository agentRepository;

    public List<Agent> getAllAgents() { return  this.agentRepository.findAll(); }

    public Agent updateAgent(Agent agent) throws  DBException, NotFoundException {

        Agent existing;

        if (agent.getID() != null){
            existing = this.agentRepository.findById(agent.getID()).orElse(null);
            //check si objet en base
            if (existing == null) throw new NotFoundException("Could not find agent with id : " + agent.getID());


        }else {
            existing = new Agent();
        }

        existing.setPrenom(agent.getPrenom());
        existing.setNom(agent.getNom());
        existing.setIsBusy(agent.getIsBusy());

        try{
            //sauvegarde en bdd
            return this.agentRepository.save(existing);
        }catch (Exception e){
            throw new DBException("Could not Create Agent");
        }

    }

    public Agent getAgentById(Long id) throws NotFoundException {
        return agentRepository.findById(id).orElseThrow(() -> new NotFoundException("Agent not found"));
    }

    public void deleteAgent(Long id) throws NotFoundException, DBException {
        Agent existing = this.agentRepository.findById(id).orElse(null);
        if (existing == null) throw new NotFoundException("Can't find agent with id :" + id);
        try {
            this.agentRepository.delete(existing);
        } catch (Exception e) {
            throw new DBException("Error with DB");
        }
    }

    public String getPrenomById(Long id) throws NotFoundException {
        Agent agent = agentRepository.findById(id).orElseThrow(() -> new NotFoundException("Geoloc not found"));
        return agent.getPrenom();
    }
}
