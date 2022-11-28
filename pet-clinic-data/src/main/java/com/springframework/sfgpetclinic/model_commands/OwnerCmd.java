package com.springframework.sfgpetclinic.model_commands;

import com.springframework.sfgpetclinic.model.Pet;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerCmd extends PersonCmd {

    @Builder
    public OwnerCmd(String id, String firstName, String lastName, String address, String city, String telephone,
                 Set<PetCmd> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if (pets != null) this.pets = pets;
    }

    private String address;

    private String city;

    private String telephone;

    private Set<PetCmd> pets = new HashSet<>();

}
