package Packageapp.repositories;

import Packageapp.models.Geoloc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeolocRepository extends JpaRepository<Geoloc, Long>{
}
