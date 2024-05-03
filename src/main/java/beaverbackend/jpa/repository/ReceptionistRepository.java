package beaverbackend.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import beaverbackend.jpa.model.Receptionist;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {

}
