package com.springframework.sfgpetclinic.services;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model_commands.OwnerCmd;

import java.util.List;

public interface OwnerService extends CrudService<Owner, String>{

    Owner saveOwner(OwnerCmd ownerCmd);

    OwnerCmd findCommandById(String id);

    OwnerCmd findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
