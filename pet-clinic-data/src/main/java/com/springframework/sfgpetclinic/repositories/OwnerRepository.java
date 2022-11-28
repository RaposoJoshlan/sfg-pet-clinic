package com.springframework.sfgpetclinic.repositories;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model_commands.OwnerCmd;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, String> {
    OwnerCmd findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

}
