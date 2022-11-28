package com.springframework.sfgpetclinic.converters;

import com.springframework.sfgpetclinic.model.Speciality;
import com.springframework.sfgpetclinic.model_commands.SpecialityCmd;
import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created on 27/11/2022
 */

@Component
public class SpecialityToSpecialityCmd implements Converter<Speciality, SpecialityCmd> {

    @Synchronized
    @Nullable
    @Override
    public SpecialityCmd convert(Speciality source) {
        if (source == null) {
            return null;
        }

        final SpecialityCmd specialityCmd = new SpecialityCmd();
        specialityCmd.setId(source.getId().toHexString());
        specialityCmd.setDescription(source.getDescription());


        return specialityCmd;
    }
}
