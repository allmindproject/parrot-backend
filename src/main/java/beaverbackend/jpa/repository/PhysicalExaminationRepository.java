package beaverbackend.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import beaverbackend.jpa.model.PhysicalExamination;

import java.util.List;

@Repository
public interface PhysicalExaminationRepository extends JpaRepository<PhysicalExamination, Long> {

}
