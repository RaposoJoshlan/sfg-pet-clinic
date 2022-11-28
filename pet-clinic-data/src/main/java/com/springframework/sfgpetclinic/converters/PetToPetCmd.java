package com.springframework.sfgpetclinic.converters;

import com.springframework.sfgpetclinic.model.Pet;
import com.springframework.sfgpetclinic.model_commands.PetCmd;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created on 27/11/2022
 */

@Component
public class PetToPetCmd implements Converter<Pet, PetCmd> {

    private final OwnerToOwnerCmd ownerConverter;

    public PetToPetCmd(OwnerToOwnerCmd ownerConverter) {
        this.ownerConverter = ownerConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public PetCmd convert(Pet source) {
        if (source == null) {
            return null;
        }

        final PetCmd petCmd = new PetCmd();
        petCmd.setId(source.getId().toHexString());
        petCmd.setName(source.getName());
        petCmd.setOwner(ownerConverter.convert(source.getOwner()));
        petCmd.setBirthDate(source.getBirthDate());

        return petCmd;
    }
}
