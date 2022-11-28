package com.springframework.sfgpetclinic.services.springDataJPA;

import com.springframework.sfgpetclinic.converters.PetCmdToPet;
import com.springframework.sfgpetclinic.converters.PetToPetCmd;
import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model.Pet;
import com.springframework.sfgpetclinic.model.PetType;
import com.springframework.sfgpetclinic.model_commands.OwnerCmd;
import com.springframework.sfgpetclinic.model_commands.PetCmd;
import com.springframework.sfgpetclinic.model_commands.PetTypeCmd;
import com.springframework.sfgpetclinic.repositories.PetRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetSDjpaServiceTest {

    public static final String ID = "5db6ce13f74c7f9f982f2598";
    public static final String IDI = "5db6ce13f74c7f9f982f2598";

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDService service;

    private Pet returnPet;
    private PetCmd returnPetCmd;
    private Owner returnOwner;
    private OwnerCmd returnOwnerCmd;
    public static final String LAST_NAME = "Smith";
    private PetType dog;
    private PetTypeCmd dogg;
    private PetType cat;

    @BeforeEach
    void setUp() {
        dog = PetType.builder().id(new ObjectId(ID)).name("Dog").build();
        dogg = PetTypeCmd.builder().id(ID).name("Dog").build();
        cat = new PetType();
        returnOwner = Owner.builder().id(new ObjectId(ID)).lastName(LAST_NAME).build();
        returnOwnerCmd = OwnerCmd.builder().id(ID).lastName(LAST_NAME).build();
        returnPet =
                Pet.builder().id(new ObjectId(ID)).petType(dog).name("Dog").owner(returnOwner).birthDate(LocalDate.now()).build();
        returnPetCmd =
                PetCmd.builder().id(ID).petType(dogg).name("Dog").owner(returnOwnerCmd).birthDate(LocalDate.now()).build();
    }

    @Test
    void findAll() {
        Set<Pet> returnPetSet = new HashSet<>();
        returnPetSet.add(Pet.builder().id(new ObjectId(ID)).petType(dog).name("Dog").build());
        returnPetSet.add(Pet.builder().id(new ObjectId("5db6ce13f74c7f9f982f2598")).petType(cat).name("Cat").build());

        when(petRepository.findAll()).thenReturn(returnPetSet);

        Set<Pet> petSet = service.findAll();

        assertNotNull(petSet);
        assertEquals(2, petSet.size());
    }

    @Test
    void findById() {
        when(petRepository.findById(anyString())).thenReturn(Optional.of(returnPet));

        Pet petSet = service.findById(ID);

        assertNotNull(petSet);
        assertEquals(new ObjectId(ID), petSet.getId());

        verify(petRepository).findById(anyString());
    }

    @Test
    void findByIdNotFound() {
        when(petRepository.findById(anyString())).thenReturn(Optional.empty());

        Pet pet = service.findById(ID);

        assertNull(pet);
    }

//    @Test
//    void save() {
//        when(petRepository.save(any())).thenReturn(returnPet);
//
//        Pet savedPet = service.savePet(returnPetCmd);
//
//        assertNotNull(savedPet);
//
//        verify(petRepository).save(any());
//    }

    @Test
    void delete() {
        service.delete(returnPet);

        verify(petRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(returnPet.getId().toHexString());

        verify(petRepository).deleteById(anyString());
    }
}