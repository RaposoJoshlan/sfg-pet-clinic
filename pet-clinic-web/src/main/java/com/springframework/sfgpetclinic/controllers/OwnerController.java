package com.springframework.sfgpetclinic.controllers;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model_commands.OwnerCmd;
import com.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    @RequestMapping({"/find"})
    public String findOwner(Model model) {

        model.addAttribute("owner", Owner.builder().build());

        return "owners/findOwners";
    }

    @GetMapping({"/{ownerId}"})
    public ModelAndView displayOwners(@PathVariable String ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");

        mav.addObject(ownerService.findCommandById(ownerId));

        return mav;
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        System.out.println(results);

        if (results.isEmpty()) {
            result.rejectValue("lastName", "not found", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId().toHexString();
        } else {
            model.addAttribute("selections", results);
            return "owners/ownerList";
        }
    }

    @GetMapping({"/new"})
    public String initCreationForm(Model model) {
        Owner owner = Owner.builder().build();
        model.addAttribute("owner", owner);
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping({"/new"})
    public String processCreationForm(@Validated OwnerCmd ownerCmd, BindingResult result) {
        if(result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        } else {
            Owner savedOwner =  ownerService.saveOwner(ownerCmd);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping({"/{ownerId}/edit"})
    public String initUpdateOwnerForm(@PathVariable String ownerId, Model model) {
        OwnerCmd owner = ownerService.findCommandById(ownerId);

        model.addAttribute(owner);

        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping({"/{ownerId}/edit"})
    public String processUpdateOwnerForm(@Validated OwnerCmd ownerCmd, BindingResult result,
                                         @PathVariable String ownerId) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        } else {
            ownerCmd.setId(ownerId);
            Owner savedOwner = ownerService.saveOwner(ownerCmd);
            savedOwner.getId();
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
