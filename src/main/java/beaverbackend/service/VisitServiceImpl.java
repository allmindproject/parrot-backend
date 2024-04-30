package beaverbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import beaverbackend.jpa.model.Visit;
import beaverbackend.jpa.repository.VisitRepository;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    @Override
    public Visit saveVisit(Visit visit) {
        return visitRepository.save(visit);
    }
}
