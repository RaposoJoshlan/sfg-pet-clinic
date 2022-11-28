package com.springframework.sfgpetclinic.converters;

import com.springframework.sfgpetclinic.model.Visit;
import com.springframework.sfgpetclinic.model_commands.VisitCmd;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created on 27/11/2022
 */

@Component
public class VisitToVisitCmd implements Converter<Visit, VisitCmd> {

    private final PetToPetCmd petConverter;

    public VisitToVisitCmd(PetToPetCmd petConverter) {
        this.petConverter = petConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public VisitCmd convert(Visit source) {
        if (source == null) {
            return null;
        }

        final VisitCmd visitCmd = new VisitCmd();
        visitCmd.setId(source.getId().toHexString());
        visitCmd.setDate(source.getDate());
        visitCmd.setDescription(source.getDescription());
        visitCmd.setPet(petConverter.convert(source.getPet()));

        return visitCmd;
    }
}
