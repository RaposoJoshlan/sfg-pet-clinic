package com.springframework.sfgpetclinic.services.springDataJPA;

import com.springframework.sfgpetclinic.converters.VisitCmdToVisit;
import com.springframework.sfgpetclinic.converters.VisitToVisitCmd;
import com.springframework.sfgpetclinic.model.Visit;
import com.springframework.sfgpetclinic.model_commands.VisitCmd;
import com.springframework.sfgpetclinic.repositories.VisitRepository;
import com.springframework.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdata")
public class VisitSDService implements VisitService {

    private final VisitRepository visitRepository;
    private final VisitToVisitCmd visitToVisitCmd;
    private final VisitCmdToVisit visitCmdToVisit;

    public VisitSDService(VisitRepository visitRepository, VisitToVisitCmd visitToVisitCmd, VisitCmdToVisit visitCmdToVisit) {
        this.visitRepository = visitRepository;
        this.visitToVisitCmd = visitToVisitCmd;
        this.visitCmdToVisit = visitCmdToVisit;
    }

    @Override
    public Set<Visit> findAll() {

        Set<Visit> visits = new HashSet<>();

        visitRepository.findAll().forEach(visits::add);

        return visits;
    }

    @Override
    public Visit findById(String id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(String id) {
        visitRepository.deleteById(id);
    }

    @Override
    public Visit saveVisit(VisitCmd visitCmd) {
        return save(visitCmdToVisit.convert(visitCmd));
    }

    @Override
    public VisitCmd findCommandById(String id) {
        return visitToVisitCmd.convert(findById(id));
    }
}
