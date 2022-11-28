package com.springframework.sfgpetclinic.converters;

import com.springframework.sfgpetclinic.model.Pet;
import com.springframework.sfgpetclinic.model_commands.PetCmd;
import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created on 27/11/2022
 */

@Component
public class PetCmdToPet implements Converter<PetCmd, Pet> {

    private final PetTypeCmdToPetType petTypeConverter;
    private final OwnerCmdToOwner ownerConverter;

    public PetCmdToPet(PetTypeCmdToPetType petTypeConverter, OwnerCmdToOwner ownerConverter) {
        this.petTypeConverter = petTypeConverter;
        this.ownerConverter = ownerConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Pet convert(PetCmd source) {
        if (source == null) {
            return null;
        }

        final Pet pet = new Pet();
        pet.setId(new ObjectId(source.getId()));
        pet.setName(source.getName());
        pet.setPetType(petTypeConverter.convert(source.getPetType()));
        pet.setOwner(ownerConverter.convert(source.getOwner()));
        pet.setBirthDate(source.getBirthDate());

        return pet;
    }
}
