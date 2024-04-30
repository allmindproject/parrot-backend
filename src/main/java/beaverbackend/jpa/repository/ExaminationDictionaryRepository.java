package beaverbackend.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import beaverbackend.jpa.model.ExaminationDictionary;

@Repository
public interface ExaminationDictionaryRepository extends JpaRepository<ExaminationDictionary, Long> {
}
