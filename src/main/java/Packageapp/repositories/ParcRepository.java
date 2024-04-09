package Packageapp.repositories;

import Packageapp.models.Parc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcRepository extends JpaRepository<Parc, Long> {
}
