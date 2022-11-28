package com.springframework.sfgpetclinic.services;

import com.springframework.sfgpetclinic.model.PetType;
import com.springframework.sfgpetclinic.model_commands.PetTypeCmd;
import com.springframework.sfgpetclinic.model_commands.SpecialityCmd;

public interface PetTypeService extends  CrudService<PetType, String>{

    PetType savePetType(PetTypeCmd petTypeCmd);

    PetTypeCmd findCommandById(String id);

}
