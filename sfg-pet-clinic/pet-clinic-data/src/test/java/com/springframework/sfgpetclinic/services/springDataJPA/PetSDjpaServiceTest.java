package com.springframework.sfgpetclinic.services.springDataJPA;

import com.springframework.sfgpetclinic.model.Pet;
import com.springframework.sfgpetclinic.model.PetType;
import com.springframework.sfgpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetSDjpaServiceTest {

    public static final long ID = 1L;
    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDjpaService service;

    Pet returnPet;

    PetType dog;
    PetType cat;

    @BeforeEach
    void setUp() {
        dog = new PetType();
        cat = new PetType();
        returnPet = Pet.builder().id(ID).petType(dog).name("Dog").build();
    }

    @Test
    void findAll() {
        Set<Pet> returnPetSet = new HashSet<>();
        returnPetSet.add(Pet.builder().id(ID).petType(dog).name("Dog").build());
        returnPetSet.add(Pet.builder().id(2L).petType(cat).name("Cat").build());

        when(petRepository.findAll()).thenReturn(returnPetSet);

        Set<Pet> petSet = service.findAll();

        assertNotNull(petSet);
        assertEquals(2, petSet.size());
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));

        Pet petSet = service.findById(ID);

        assertNotNull(petSet);
        assertEquals(ID, petSet.getId());

        verify(petRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());

        Pet pet = service.findById(ID);

        assertNull(pet);
    }

    @Test
    void save() {
        when(petRepository.save(any())).thenReturn(returnPet);

        Pet savedPet = service.save(returnPet);

        assertNotNull(savedPet);

        verify(petRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnPet);

        verify(petRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(returnPet.getId());

        verify(petRepository).deleteById(anyLong());
    }
}