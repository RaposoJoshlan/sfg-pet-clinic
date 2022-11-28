package com.springframework.sfgpetclinic.services.springDataJPA;

import com.springframework.sfgpetclinic.converters.PetTypeCmdToPetType;
import com.springframework.sfgpetclinic.converters.PetTypeToPetTypeCmd;
import com.springframework.sfgpetclinic.model.PetType;
import com.springframework.sfgpetclinic.model_commands.PetTypeCmd;
import com.springframework.sfgpetclinic.repositories.PetTypeRepository;
import com.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdata")
public class PetTypeSDService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;
    private final PetTypeCmdToPetType petTypeCmdToPetType;
    private final PetTypeToPetTypeCmd petTypeToPetTypeCmd;

    public PetTypeSDService(PetTypeRepository petTypeRepository, PetTypeCmdToPetType petTypeCmdToPetType, PetTypeToPetTypeCmd petTypeToPetTypeCmd) {
        this.petTypeRepository = petTypeRepository;
        this.petTypeCmdToPetType = petTypeCmdToPetType;
        this.petTypeToPetTypeCmd = petTypeToPetTypeCmd;
    }

    @Override
    public Set<PetType> findAll() {

        Set<PetType> petTypes = new HashSet<>();

        petTypeRepository.findAll().forEach(petTypes::add);

        return petTypes;
    }

    @Override
    public PetType findById(String s) {
        return petTypeRepository.findById(s).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(String s) {
        petTypeRepository.deleteById(s);
    }

    @Override
    public PetType savePetType(PetTypeCmd petTypeCmd) {
        return save(petTypeCmdToPetType.convert(petTypeCmd));
    }

    @Override
    public PetTypeCmd findCommandById(String id) {
        return petTypeToPetTypeCmd.convert(findById(id));
    }
}
