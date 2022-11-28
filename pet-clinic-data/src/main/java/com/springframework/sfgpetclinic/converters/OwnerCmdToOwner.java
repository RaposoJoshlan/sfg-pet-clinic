package com.springframework.sfgpetclinic.converters;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model_commands.OwnerCmd;
import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created on 27/11/2022
 */

@Component
public class OwnerCmdToOwner implements Converter<OwnerCmd, Owner> {

    private final PetCmdToPet petConverter;

    public OwnerCmdToOwner(PetCmdToPet petConverter) {
        this.petConverter = petConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Owner convert(OwnerCmd source) {
        if (source == null) {
            return null;
        }

        final Owner owner = new Owner();
        owner.setId(new ObjectId(source.getId()));
        owner.setFirstName(source.getFirstName());
        owner.setLastName(source.getLastName());
        owner.setAddress(source.getAddress());
        owner.setCity(source.getCity());
        owner.setTelephone(source.getTelephone());

        if(source.getPets() != null && source.getPets().size() > 0) {
            source.getPets().forEach(pet -> owner.getPets()
                    .add(petConverter.convert(pet)));
        }

        return owner;
    }
}
