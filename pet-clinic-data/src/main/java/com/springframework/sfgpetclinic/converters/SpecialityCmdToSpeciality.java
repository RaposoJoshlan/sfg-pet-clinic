package com.springframework.sfgpetclinic.converters;

import com.springframework.sfgpetclinic.model.Speciality;
import com.springframework.sfgpetclinic.model.Visit;
import com.springframework.sfgpetclinic.model_commands.SpecialityCmd;
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
public class SpecialityCmdToSpeciality implements Converter<SpecialityCmd, Speciality> {

    @Synchronized
    @Nullable
    @Override
    public Speciality convert(SpecialityCmd source) {
        if (source == null) {
            return null;
        }

        final Speciality speciality = new Speciality();
        speciality.setId(new ObjectId(source.getId()));
        speciality.setDescription(source.getDescription());


        return speciality;
    }
}
