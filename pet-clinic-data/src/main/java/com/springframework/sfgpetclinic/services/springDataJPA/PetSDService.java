package com.springframework.sfgpetclinic.services.springDataJPA;

import com.springframework.sfgpetclinic.converters.PetCmdToPet;
import com.springframework.sfgpetclinic.converters.PetToPetCmd;
import com.springframework.sfgpetclinic.model.Pet;
import com.springframework.sfgpetclinic.model_commands.PetCmd;
import com.springframework.sfgpetclinic.repositories.PetRepository;
import com.springframework.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdata")
public class PetSDService implements PetService {

    private final PetRepository petRepository;

    private final PetCmdToPet petCmdToPet;
    private final PetToPetCmd petToPetCmd;

    public PetSDService(PetRepository petRepository, PetCmdToPet petCmdToPet, PetToPetCmd petToPetCmd) {
        this.petRepository = petRepository;
        this.petCmdToPet = petCmdToPet;
        this.petToPetCmd = petToPetCmd;
    }

    @Override
    public Set<Pet> findAll() {

        Set<Pet> pets = new HashSet<>();

        petRepository.findAll().forEach(pets::add);

        return pets;
    }

    @Override
    public Pet findById(String id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }


    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(String id) {
        petRepository.deleteById(id);
    }

    @Override
    public Pet savePet(PetCmd petCmd) {
        return save(petCmdToPet.convert(petCmd));
    }

    @Override
    public PetCmd findCommandById(String id) {
        return petToPetCmd.convert(findById(id));
    }
}
