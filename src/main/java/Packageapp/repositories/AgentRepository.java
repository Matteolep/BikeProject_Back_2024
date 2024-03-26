package Packageapp.repositories;

import Packageapp.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface AgentRepository extends JpaRepository<Agent, Long> {

}
