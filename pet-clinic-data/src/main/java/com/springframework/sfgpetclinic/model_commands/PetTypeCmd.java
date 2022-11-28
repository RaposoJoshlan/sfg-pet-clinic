package com.springframework.sfgpetclinic.model_commands;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetTypeCmd extends BaseEntityCmd {

    @Builder
    public PetTypeCmd(String id, String name) {
        super(id);
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
