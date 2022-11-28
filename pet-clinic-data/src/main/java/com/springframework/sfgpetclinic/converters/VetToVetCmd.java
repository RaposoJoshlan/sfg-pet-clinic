package com.springframework.sfgpetclinic.converters;

import com.springframework.sfgpetclinic.model.Vet;
import com.springframework.sfgpetclinic.model_commands.VetCmd;
import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created on 27/11/2022
 */

@Component
public class VetToVetCmd implements Converter<Vet, VetCmd> {

    private final SpecialityToSpecialityCmd specialityConverter;

    public VetToVetCmd(SpecialityToSpecialityCmd specialityConverter) {
        this.specialityConverter = specialityConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public VetCmd convert(Vet source) {
        if (source == null) {
            return null;
        }

        final VetCmd vetCmd = new VetCmd();
        vetCmd.setId(source.getId().toHexString());
        vetCmd.setFirstName(source.getFirstName());
        vetCmd.setLastName(source.getLastName());

        if(source.getSpecialities() != null && source.getSpecialities().size() > 0) {
            source.getSpecialities().forEach(speciality -> vetCmd.getSpecialities()
                    .add(specialityConverter.convert(speciality)));
        }

        return vetCmd;
    }
}
