package com.springframework.sfgpetclinic.model_commands;

import com.springframework.sfgpetclinic.model.Visit;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetCmd extends BaseEntityCmd {

     @Builder
     public PetCmd(String id, String name, PetTypeCmd petType, OwnerCmd owner, LocalDate birthDate) {
          super(id);
          this.name = name;
          this.petType = petType;
          this.owner = owner;
          this.birthDate = birthDate;
     }

     private String name;

     private PetTypeCmd petType;

     private OwnerCmd owner;

     private LocalDate birthDate;

}
