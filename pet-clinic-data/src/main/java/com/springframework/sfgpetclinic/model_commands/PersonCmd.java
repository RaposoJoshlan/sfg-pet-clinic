package com.springframework.sfgpetclinic.model_commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonCmd extends BaseEntityCmd {

    public PersonCmd(String id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private String firstName;

    private String lastName;
}
