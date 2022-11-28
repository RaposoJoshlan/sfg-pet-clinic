package com.springframework.sfgpetclinic.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pets")
public class Pet extends BaseEntity{

     @Builder
     public Pet(ObjectId id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
          super(id);
          this.name = name;
          this.petType = petType;
          this.owner = owner;
          this.birthDate = birthDate;
          if (visits == null || visits.size() > 0) this.visits = visits;
     }

     private String name;

     private PetType petType;

     private Owner owner;

     private LocalDate birthDate;

     private Set<Visit> visits = new HashSet<>();
}
