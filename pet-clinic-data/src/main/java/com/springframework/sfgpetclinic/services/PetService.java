package com.springframework.sfgpetclinic.services;

import com.springframework.sfgpetclinic.model.Pet;
import com.springframework.sfgpetclinic.model_commands.OwnerCmd;
import com.springframework.sfgpetclinic.model_commands.PetCmd;

public interface PetService extends CrudService<Pet, String>{

    Pet savePet(PetCmd petCmd);

    PetCmd findCommandById(String id);
}
