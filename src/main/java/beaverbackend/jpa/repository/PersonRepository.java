package beaverbackend.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import beaverbackend.jpa.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

}
