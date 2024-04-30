package beaverbackend.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import beaverbackend.jpa.model.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

}
