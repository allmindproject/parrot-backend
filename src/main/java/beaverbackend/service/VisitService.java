package beaverbackend.service;

import beaverbackend.controllers.common.BadRequestException;
import beaverbackend.controllers.common.VisitSearchReq;
import beaverbackend.controllers.doctor.VisitCompleteReq;
import beaverbackend.controllers.doctor.VisitDetailsReq;
import beaverbackend.controllers.doctor.SetVisitStatusReq;
import beaverbackend.controllers.receptionist.VisitCreateReq;
import beaverbackend.jpa.model.Visit;

import java.util.List;

public interface VisitService {

    Visit createNewVisit(VisitCreateReq req) throws BadRequestException;

    List<Visit> searchVisits(VisitSearchReq req);

    Visit getVisitById(Long visitId);

    Visit completeVisit(VisitCompleteReq req);

    Visit cancelVisit(Long id) throws BadRequestException;

    Visit setVisitStatus(SetVisitStatusReq req) throws BadRequestException;

    Visit setVisitDetails(VisitDetailsReq req) throws BadRequestException;
}
