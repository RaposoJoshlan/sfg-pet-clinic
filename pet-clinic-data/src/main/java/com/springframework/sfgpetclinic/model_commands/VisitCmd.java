package com.springframework.sfgpetclinic.model_commands;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VisitCmd extends BaseEntityCmd {
    private LocalDate date;

    private String description;

    private PetCmd pet;
}
