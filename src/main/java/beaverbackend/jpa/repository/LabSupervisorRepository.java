package beaverbackend.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import beaverbackend.jpa.model.LabSupervisor;

@Repository
public interface LabSupervisorRepository extends JpaRepository<LabSupervisor, Long> {

}
