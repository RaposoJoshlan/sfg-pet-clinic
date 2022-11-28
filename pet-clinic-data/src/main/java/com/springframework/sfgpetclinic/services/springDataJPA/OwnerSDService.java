package com.springframework.sfgpetclinic.services.springDataJPA;

import com.springframework.sfgpetclinic.converters.OwnerCmdToOwner;
import com.springframework.sfgpetclinic.converters.OwnerToOwnerCmd;
import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model_commands.OwnerCmd;
import com.springframework.sfgpetclinic.repositories.OwnerRepository;
import com.springframework.sfgpetclinic.repositories.PetRepository;
import com.springframework.sfgpetclinic.repositories.PetTypeRepository;
import com.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("springdata")
public class OwnerSDService implements OwnerService {

    private final OwnerRepository ownerRepository;

    private final OwnerCmdToOwner ownerCmdToOwner;
    private final OwnerToOwnerCmd ownerToOwnerCmd;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDService(OwnerRepository ownerRepository, OwnerCmdToOwner ownerCmdToOwner, OwnerToOwnerCmd ownerToOwnerCmd, PetRepository petRepository, PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.ownerCmdToOwner = ownerCmdToOwner;
        this.ownerToOwnerCmd = ownerToOwnerCmd;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<Owner> findAll() {

        Set<Owner> owners = new HashSet<>();

        ownerRepository.findAll().iterator().forEachRemaining(owners::add);

        return owners;
    }

    @Override
    public Owner findById(String id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public Owner saveOwner(OwnerCmd ownerCmd) {
        return save(ownerCmdToOwner.convert(ownerCmd));
    }

    @Override
    public OwnerCmd findCommandById(String id) {
        return ownerToOwnerCmd.convert(findById(id));
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(String aLong) {
        ownerRepository.deleteById(aLong);
    }

    @Override
    public OwnerCmd findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }
}
