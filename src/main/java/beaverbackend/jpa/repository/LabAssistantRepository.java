package beaverbackend.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import beaverbackend.jpa.model.LabAssistant;

@Repository
public interface LabAssistantRepository extends JpaRepository<LabAssistant, Long> {

}
