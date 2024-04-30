package beaverbackend.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import beaverbackend.jpa.model.LabStaff;

@Repository
public interface LabStaffRepository extends JpaRepository<LabStaff, Long> {

}
