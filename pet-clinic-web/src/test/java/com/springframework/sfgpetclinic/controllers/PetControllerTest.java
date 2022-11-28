package com.springframework.sfgpetclinic.controllers;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model.Pet;
import com.springframework.sfgpetclinic.model.PetType;
import com.springframework.sfgpetclinic.model_commands.OwnerCmd;
import com.springframework.sfgpetclinic.model_commands.PetCmd;
import com.springframework.sfgpetclinic.services.OwnerService;
import com.springframework.sfgpetclinic.services.PetService;
import com.springframework.sfgpetclinic.services.PetTypeService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    PetService petService;

    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;

    Owner owner;
    OwnerCmd ownerCmd;

    Set<PetType> petTypes;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(new ObjectId("5db6ce13f74c7f9f982f2596")).build();
        ownerCmd = OwnerCmd.builder().id("5db6ce13f74c7f9f982f2596").build();
        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(new ObjectId("5db6ce13f74c7f9f982f2596")).name("Dog").build());
        petTypes.add(PetType.builder().id(new ObjectId("5db6ce13f74c7f9f982f2596")).name("Cat").build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(petController)
                .build();
    }

    @Test
    void initCreationForm() throws Exception {
        when(ownerService.findCommandById(anyString())).thenReturn(ownerCmd);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owners/5db6ce13f74c7f9f982f2596/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"));

        verifyNoMoreInteractions(petService);
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.findCommandById(anyString())).thenReturn(ownerCmd);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/5db6ce13f74c7f9f982f2596/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/5db6ce13f74c7f9f982f2596"));

        verify(petService).savePet(any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        when(ownerService.findCommandById(anyString())).thenReturn(ownerCmd);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.findCommandById(anyString())).thenReturn(PetCmd.builder().id("5db6ce13f74c7f9f982f2598").build());

        mockMvc.perform(get("/owners/5db6ce13f74c7f9f982f2596/pets/5db6ce13f74c7f9f982f2598/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"));

        verifyNoMoreInteractions(petService);
    }

    @Test
    void processUpdateForm() throws Exception {
        when(ownerService.findCommandById(anyString())).thenReturn(ownerCmd);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/5db6ce13f74c7f9f982f2596/pets/5db6ce13f74c7f9f982f2598/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/5db6ce13f74c7f9f982f2596"));

        verify(petService).savePet(any());
    }

    @Test
    void populatePetType() {
    }

    @Test
    void findOwner() {
    }

    @Test
    void initOwnerBinder() {
    }
}