package com.springframework.sfgpetclinic.services.springDataJPA;

import com.springframework.sfgpetclinic.converters.VetCmdToVet;
import com.springframework.sfgpetclinic.converters.VetToVetCmd;
import com.springframework.sfgpetclinic.model.Vet;
import com.springframework.sfgpetclinic.model_commands.VetCmd;
import com.springframework.sfgpetclinic.repositories.VetRepository;
import com.springframework.sfgpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdata")
public class VetSDService implements VetService {

    private final VetRepository vetRepository;
    private final VetToVetCmd vetToVetCmd;
    private final VetCmdToVet vetCmdToVet;

    public VetSDService(VetRepository vetRepository, VetToVetCmd vetToVetCmd, VetCmdToVet vetCmdToVet) {
        this.vetRepository = vetRepository;
        this.vetToVetCmd = vetToVetCmd;
        this.vetCmdToVet = vetCmdToVet;
    }

    @Override
    public Set<Vet> findAll() {

        Set<Vet> vets = new HashSet<>();

        vetRepository.findAll().forEach(vets::add);

        return vets;
    }

    @Override
    public Vet findById(String s) {
        return vetRepository.findById(s).orElse(null);
    }

    @Override
    public Vet save(Vet object) {
        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(String s) {
        vetRepository.deleteById(s);
    }

    @Override
    public Vet saveVet(VetCmd vetCmd) {
        return save(vetCmdToVet.convert(vetCmd));
    }

    @Override
    public VetCmd findCommandById(String id) {
        return vetToVetCmd.convert(findById(id));
    }
}
