package com.springframework.sfgpetclinic.converters;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model_commands.OwnerCmd;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created on 27/11/2022
 */

@Component
public class OwnerToOwnerCmd implements Converter<Owner, OwnerCmd> {

    private final PetToPetCmd petConverter;

    public OwnerToOwnerCmd(PetToPetCmd petConverter) {
        this.petConverter = petConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public OwnerCmd convert(Owner source) {
        if (source == null) {
            return null;
        }

        final OwnerCmd ownerCmd = new OwnerCmd();
        ownerCmd.setId(source.getId().toHexString());
        ownerCmd.setAddress(source.getAddress());
        ownerCmd.setCity(source.getCity());
        ownerCmd.setTelephone(source.getTelephone());

        if(source.getPets() != null && source.getPets().size() > 0) {
            source.getPets().forEach(pet -> ownerCmd.getPets()
                    .add(petConverter.convert(pet)));
        }



        return ownerCmd;
    }
}
