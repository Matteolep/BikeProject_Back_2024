package fr.cszw.td_devwebavance.services;

import fr.cszw.td_devwebavance.exceptions.DBException;
import fr.cszw.td_devwebavance.exceptions.NotFoundException;
import fr.cszw.td_devwebavance.models.Light;
import fr.cszw.td_devwebavance.repositories.LightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LightService {

    private final LightRepository lightRepository;

    public List<Light> getAllLights() {
        return this.lightRepository.findAll();
    }

    public Light updateLight(Light light) throws DBException, NotFoundException {
        Light existing;

        if (light.getId() != null) {
            // On veut modifier une lampe
            // findById renvoie un Optionnal, ce qui signifie qu'il faut rajouter une méthode afin de palier à tous
            //      les comportements possibles
            existing = this.lightRepository.findById(light.getId()).orElse(null);
            // On check si l'object existe en base
            if (existing == null) throw new NotFoundException("Could not find light with id : " + light.getId());
        } else {
            // On veut créer une lampe
            existing = new Light();
        }

        // On modifie toutes les propriétés que l'on veut modifier de la lampe
        existing.setToggled(light.getToggled());
        existing.setColor(light.getColor());
        existing.setTitle(light.getTitle());

        try {
            // On sauvegarde la lampe en BDD
            return this.lightRepository.save(existing);
        } catch (Exception e) {
            throw new DBException("Could not create light");
        }
    }

    public void deleteLight(Long id) throws NotFoundException, DBException {
        Light existing = this.lightRepository.findById(id).orElse(null);
        if (existing == null) throw new NotFoundException("Can't find light with id :" + id);
        try {
            this.lightRepository.delete(existing);
        } catch (Exception e) {
            throw new DBException("Error with DB");
        }
    }
}
