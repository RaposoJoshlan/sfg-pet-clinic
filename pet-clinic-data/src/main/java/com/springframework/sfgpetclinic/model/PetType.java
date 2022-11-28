package com.springframework.sfgpetclinic.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pet_types")
public class PetType extends BaseEntity{

    @Builder
    public PetType(ObjectId id, String name) {
        super(id);
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
