package beaverbackend.jpa.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import beaverbackend.controllers.receptionist.SearchPatientReq;
import beaverbackend.jpa.model.Patient;
import beaverbackend.jpa.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PatientSpecification {

    public static Specification<Patient> searchSpecification(SearchPatientReq req) {
        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            Join<Patient, Person> personJoin = root.join("person", JoinType.INNER);

            if (!req.getFirstName().isEmpty())
                predicates.add(builder.and(builder.like(personJoin.get("firstName"), "%" + req.getFirstName() + "%")));
            if (!req.getLastName().isEmpty())
                predicates.add(builder.and(builder.like(personJoin.get("lastName"), "%" + req.getLastName() + "%")));
            if (!req.getNationalIdNumber().isEmpty())
                predicates.add(builder.and(builder.equal(personJoin.get("nationalIdNumber"), req.getNationalIdNumber())));
            if (!req.getInsuranceId().isEmpty())
                predicates.add(builder.and(builder.equal(root.get("insuranceId"), req.getInsuranceId())));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
