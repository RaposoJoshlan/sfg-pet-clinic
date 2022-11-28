package com.springframework.sfgpetclinic.converters;

import com.springframework.sfgpetclinic.model.PetType;
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
public class PetTypeToPetTypeCmd implements Converter<PetType, PetTypeCmd> {

    @Synchronized
    @Nullable
    @Override
    public PetTypeCmd convert(PetType source) {
        if (source == null) {
            return null;
        }

        final PetTypeCmd petTypeCmd = new PetTypeCmd();
        petTypeCmd.setId(source.getId().toHexString());
        petTypeCmd.setName(source.getName());


        return petTypeCmd;
    }
}
