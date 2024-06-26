package beaverbackend.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import beaverbackend.jpa.model.LabExamination;

@Repository
public interface LabExaminationRepository extends JpaRepository<LabExamination, Long>, JpaSpecificationExecutor<LabExamination> {

}
