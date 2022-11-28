package com.springframework.sfgpetclinic.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "visits")
public class Visit extends BaseEntity{

    @Builder
    public Visit(ObjectId id, LocalDate date, String description, Pet pet) {
        super(id);
        this.date = date;
        this.description = description;
        this.pet = pet;
    }

    private LocalDate date;

    private String description;

    private Pet pet;
}
