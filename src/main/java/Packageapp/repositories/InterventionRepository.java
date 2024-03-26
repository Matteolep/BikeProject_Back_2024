package Packageapp.repositories;

import Packageapp.models.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface InterventionRepository extends JpaRepository<Intervention, Long>{
}
