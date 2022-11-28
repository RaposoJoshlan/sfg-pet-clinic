package com.springframework.sfgpetclinic.converters;

import com.springframework.sfgpetclinic.model.Vet;
import com.springframework.sfgpetclinic.model.Visit;
import com.springframework.sfgpetclinic.model_commands.VetCmd;
import com.springframework.sfgpetclinic.model_commands.VisitCmd;
import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created on 27/11/2022
 */

@Component
public class VetCmdToVet implements Converter<VetCmd, Vet> {

    private final SpecialityCmdToSpeciality specialityConverter;

    public VetCmdToVet(SpecialityCmdToSpeciality specialityConverter) {
        this.specialityConverter = specialityConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Vet convert(VetCmd source) {
        if (source == null) {
            return null;
        }

        final Vet vet = new Vet();
        vet.setId(new ObjectId(source.getId()));
        vet.setFirstName(source.getFirstName());
        vet.setLastName(source.getLastName());

        if(source.getSpecialities() != null && source.getSpecialities().size() > 0) {
            source.getSpecialities().forEach(speciality -> vet.getSpecialities()
                    .add(specialityConverter.convert(speciality)));
        }

        return vet;
    }
}
