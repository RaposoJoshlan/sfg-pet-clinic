package com.springframework.sfgpetclinic.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vets")
public class Vet extends Person{

    @Builder
    public Vet(ObjectId id, String firstName, String lastName, Set<Speciality> specialities) {
        super(id, firstName, lastName);
        this.specialities = specialities;
    }

    private Set<Speciality> specialities = new HashSet<>();

}
