package com.springframework.sfgpetclinic.controllers;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model_commands.OwnerCmd;
import com.springframework.sfgpetclinic.services.OwnerService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(new ObjectId("5db6ce13f74c7f9f982f2598")).build());
        owners.add(Owner.builder().id(new ObjectId("5db6ce13f74c7f9f982f2599")).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

    }

    @Test
    void findOwner() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void displayOwners() throws Exception {
        when(ownerService.findCommandById(anyString())).thenReturn(OwnerCmd.builder().id("5db6ce13f74c7f9f982f2598").build());

        mockMvc.perform(get("/owners/5db6ce13f74c7f9f982f2598"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("ownerCmd", hasProperty("id",
                        is("5db6ce13f74c7f9f982f2598"))));
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(new ObjectId("5db6ce13f74c7f9f982f2598")).build()
                , Owner.builder().id(new ObjectId("5db6ce13f74c7f9f982f2598")).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(new ObjectId("5db6ce13f74c7f9f982f2598")).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/5db6ce13f74c7f9f982f2598"));
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyNoMoreInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.saveOwner(any())).thenReturn(Owner.builder().id(new ObjectId("5db6ce13f74c7f9f982f2598")).build());

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/5db6ce13f74c7f9f982f2598"))
                .andExpect(model().attributeExists("ownerCmd"));

        verify(ownerService).saveOwner(any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        when(ownerService.findCommandById(anyString())).thenReturn(OwnerCmd.builder().id("5db6ce13f74c7f9f982f2598").build());

        mockMvc.perform(get("/owners/5db6ce13f74c7f9f982f2598/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("ownerCmd"));

        verifyNoMoreInteractions(ownerService);
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        when(ownerService.saveOwner(any())).thenReturn(Owner.builder().id(new ObjectId("5db6ce13f74c7f9f982f2598")).build());

        mockMvc.perform(post("/owners/5db6ce13f74c7f9f982f2598/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/5db6ce13f74c7f9f982f2598"))
                .andExpect(model().attributeExists("ownerCmd"));

        verify(ownerService).saveOwner(any());
    }

}