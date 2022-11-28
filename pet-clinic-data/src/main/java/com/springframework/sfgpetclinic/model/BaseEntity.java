package com.springframework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

    @Id
    private ObjectId id;

    public boolean isNew() {
        return this.id == null;
    }
}
