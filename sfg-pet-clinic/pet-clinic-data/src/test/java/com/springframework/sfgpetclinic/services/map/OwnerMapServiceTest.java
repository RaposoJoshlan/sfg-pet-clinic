package com.springframework.sfgpetclinic.services.map;

import com.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;

    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner saveOwner = ownerMapService.save(owner2);

        assertEquals(id, saveOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner saveOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(saveOwner);
        assertNotNull(saveOwner.getId());

    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(lastName);

        assertNotNull(owner);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerMapService.findByLastName("Foo");

        assertNull(owner);
    }
}