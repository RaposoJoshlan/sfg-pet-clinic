package com.springframework.sfgpetclinic.model_commands;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VetCmd extends PersonCmd {

    private Set<SpecialityCmd> specialities = new HashSet<>();

}
