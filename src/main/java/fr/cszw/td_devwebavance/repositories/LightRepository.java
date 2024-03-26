package fr.cszw.td_devwebavance.repositories;

import fr.cszw.td_devwebavance.models.Light;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightRepository extends JpaRepository<Light, Long> {
}
