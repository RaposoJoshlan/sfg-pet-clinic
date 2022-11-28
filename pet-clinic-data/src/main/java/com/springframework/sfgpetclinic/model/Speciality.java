package com.springframework.sfgpetclinic.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "specialities")
public class Speciality extends BaseEntity{

    @Builder
    public Speciality(ObjectId id, String description) {
        super(id);
        this.description = description;
    }

    private String description;
}
