package Packageapp.services;

import Packageapp.exceptions.NotFoundException;
import Packageapp.repositories.GeolocRepository;
import Packageapp.exceptions.DBException;
import Packageapp.models.Geoloc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeolocService {

    private final GeolocRepository geolocRepository;

    public List<Geoloc> getAllGeolocs() {
        return this.geolocRepository.findAll();
    }


    public Geoloc updategeoloc(Geoloc geoloc) throws DBException,NotFoundException {
        Geoloc existing;

        if (geoloc.getID() != null){
            existing = this.geolocRepository.findById(geoloc.getID()).orElse(null);
            if (existing == null) throw new NotFoundException("Could not find Geoloc with id : " + geoloc.getID());
        } else {
            existing = new Geoloc();
        }

        existing.setLongitude(geoloc.getLongitude());
        existing.setLatitude(geoloc.getLatitude());
        existing.setVille(geoloc.getVille());

        try {
            return this.geolocRepository.save(existing);
        }catch (Exception e){
            throw new DBException("Could not Create geoloc");
        }
    }

    public void deleteGeoloc(Long id) throws NotFoundException, DBException {
        Geoloc existing = this.geolocRepository.findById(id).orElse(null);
        if (existing == null) throw new NotFoundException("Can't find geoloc with id :" + id);
        try {
            this.geolocRepository.delete(existing);
        } catch (Exception e) {
            throw new DBException("Error with DB");
        }
    }



}
