package com.springframework.sfgpetclinic.services;

import com.springframework.sfgpetclinic.model.Vet;
import com.springframework.sfgpetclinic.model_commands.SpecialityCmd;
import com.springframework.sfgpetclinic.model_commands.VetCmd;

public interface VetService extends CrudService<Vet, String>{

    Vet saveVet(VetCmd vetCmd);

    VetCmd findCommandById(String id);

}
