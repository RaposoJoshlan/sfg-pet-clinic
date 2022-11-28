package com.springframework.sfgpetclinic.services;

import com.springframework.sfgpetclinic.model.Visit;
import com.springframework.sfgpetclinic.model_commands.VetCmd;
import com.springframework.sfgpetclinic.model_commands.VisitCmd;

public interface VisitService extends CrudService<Visit, String> {

    Visit saveVisit(VisitCmd visitCmd);

    VisitCmd findCommandById(String id);
}
