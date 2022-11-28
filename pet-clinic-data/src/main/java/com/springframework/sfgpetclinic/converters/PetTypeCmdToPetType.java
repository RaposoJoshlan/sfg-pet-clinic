package com.springframework.sfgpetclinic.converters;

import com.springframework.sfgpetclinic.model.Pet;
import com.springframework.sfgpetclinic.model.PetType;
import com.springframework.sfgpetclinic.model_commands.PetCmd;
import com.springframework.sfgpetclinic.model_commands.PetTypeCmd;
import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created on 27/11/2022
 */

@Component
public class PetTypeCmdToPetType implements Converter<PetTypeCmd, PetType> {

    @Synchronized
    @Nullable
    @Override
    public PetType convert(PetTypeCmd source) {
        if (source == null) {
            return null;
        }

        final PetType petType = new PetType();
        petType.setId(new ObjectId(source.getId()));
        petType.setName(source.getName());


        return petType;
    }
}
