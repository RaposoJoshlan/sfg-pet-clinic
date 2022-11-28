package com.springframework.sfgpetclinic.services;

import com.springframework.sfgpetclinic.model.Speciality;
import com.springframework.sfgpetclinic.model_commands.OwnerCmd;
import com.springframework.sfgpetclinic.model_commands.SpecialityCmd;

public interface SpecialityService extends CrudService<Speciality, String>  {
    Speciality saveSpeciality(SpecialityCmd ownerCmd);

    SpecialityCmd findCommandById(String id);
}
