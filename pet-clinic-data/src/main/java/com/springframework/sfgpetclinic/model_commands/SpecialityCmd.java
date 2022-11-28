package com.springframework.sfgpetclinic.model_commands;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpecialityCmd extends BaseEntityCmd {

    @Builder
    public SpecialityCmd(String id, String description) {
        super(id);
        this.description = description;
    }

    private String description;
}
