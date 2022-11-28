package com.springframework.sfgpetclinic.controllers;

import com.springframework.sfgpetclinic.model.Vet;
import com.springframework.sfgpetclinic.services.VetService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    VetService vetService;

    @InjectMocks
    VetController controller;

    Set<Vet> vets;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        vets = new HashSet<>();
        vets.add(Vet.builder().id(new ObjectId("5db6ce13f74c7f9f982f2596")).build());
        vets.add(Vet.builder().id(new ObjectId("5db6ce13f74c7f9f982f2597")).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void listVets() throws Exception {
        when(vetService.findAll()).thenReturn(vets);

        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attribute("vets", hasSize(2)));
    }

    void listVetsByIndex() throws Exception {
        when(vetService.findAll()).thenReturn(vets);

        mockMvc.perform(get("/vets/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attribute("vets", hasSize(2)));
    }
}