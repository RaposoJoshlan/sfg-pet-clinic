package com.springframework.sfgpetclinic.model_commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntityCmd implements Serializable {

    @Id
    private String id;

    public boolean isNew() {
        return this.id == null;
    }
}
