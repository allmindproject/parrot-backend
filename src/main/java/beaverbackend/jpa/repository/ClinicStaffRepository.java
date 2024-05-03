package beaverbackend.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import beaverbackend.jpa.model.ClinicStaff;

@Repository
public interface ClinicStaffRepository extends JpaRepository<ClinicStaff, Long> {

}
