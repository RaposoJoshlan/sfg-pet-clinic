package com.springframework.sfgpetclinic.converters;

import com.springframework.sfgpetclinic.model.Visit;
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
public class VisitCmdToVisit implements Converter<VisitCmd, Visit> {

    private final PetCmdToPet petConverter;

    public VisitCmdToVisit(PetCmdToPet petConverter) {
        this.petConverter = petConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Visit convert(VisitCmd source) {
        if (source == null) {
            return null;
        }

        final Visit visit = new Visit();
        visit.setId(new ObjectId(source.getId()));
        visit.setDate(source.getDate());
        visit.setDescription(source.getDescription());
        visit.setPet(petConverter.convert(source.getPet()));

        return visit;
    }
}
