package com.springframework.sfgpetclinic.services.springDataJPA;

import com.springframework.sfgpetclinic.converters.SpecialityCmdToSpeciality;
import com.springframework.sfgpetclinic.converters.SpecialityToSpecialityCmd;
import com.springframework.sfgpetclinic.model.Pet;
import com.springframework.sfgpetclinic.model.Speciality;
import com.springframework.sfgpetclinic.model_commands.SpecialityCmd;
import com.springframework.sfgpetclinic.repositories.SpecialityRepository;
import com.springframework.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdata")
public class SpecialitySDService implements SpecialityService {

    private final SpecialityRepository specialityRepository;
    private final SpecialityCmdToSpeciality specialityCmdToSpeciality;
    private final SpecialityToSpecialityCmd specialityToSpecialityCmd;

    public SpecialitySDService(SpecialityRepository specialityRepository, SpecialityCmdToSpeciality specialityCmdToSpeciality, SpecialityToSpecialityCmd specialityToSpecialityCmd) {
        this.specialityRepository = specialityRepository;
        this.specialityCmdToSpeciality = specialityCmdToSpeciality;
        this.specialityToSpecialityCmd = specialityToSpecialityCmd;
    }

    @Override
    public Set<Speciality> findAll() {

        Set<Speciality> specialities = new HashSet<>();

        specialityRepository.findAll().forEach(specialities::add);

        return specialities;
    }

    @Override
    public Speciality findById(String s) {
        return specialityRepository.findById(s).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(String s) {
        specialityRepository.deleteById(s);
    }

    @Override
    public Speciality saveSpeciality(SpecialityCmd specialityCmd) {
        return save(specialityCmdToSpeciality.convert(specialityCmd));
    }

    @Override
    public SpecialityCmd findCommandById(String id) {
        return specialityToSpecialityCmd.convert(findById(id));
    }
}
