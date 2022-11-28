package com.springframework.sfgpetclinic.services.springDataJPA;

import com.springframework.sfgpetclinic.converters.OwnerCmdToOwner;
import com.springframework.sfgpetclinic.converters.OwnerToOwnerCmd;
import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model_commands.OwnerCmd;
import com.springframework.sfgpetclinic.repositories.OwnerRepository;
import com.springframework.sfgpetclinic.repositories.PetRepository;
import com.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.bson.types.ObjectId;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDjpaServiceTest {

    public static final String LAST_NAME = "Smith";
    public static final String id = "5db6ce13f74c7f9f982f2598";
    public static final String id2 = "5db6ce13f74c7f9f982f2599";
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @Mock
    OwnerToOwnerCmd ownerToOwnerCmd;

    @Mock
    OwnerCmdToOwner ownerCmdToOwner;

    @InjectMocks
    OwnerSDService service;

    private Owner returnOwner;
    private OwnerCmd returnOwnerCmd;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(new ObjectId(id)).lastName(LAST_NAME).build();
        returnOwnerCmd = OwnerCmd.builder().id(id).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnerSet = new HashSet<>();
        returnOwnerSet.add(Owner.builder().id(new ObjectId(id)).build());
        returnOwnerSet.add(Owner.builder().id(new ObjectId(id2)).build());

        when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyString())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(id);

        assertNotNull(owner);
        assertEquals(new ObjectId(id), owner.getId());

        verify(ownerRepository).findById(anyString());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyString())).thenReturn(Optional.empty());

        Owner owner = service.findById(id);

        assertNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = service.saveOwner(returnOwnerCmd);

        assertNotNull(savedOwner);

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        //Default times is once, I added for demonstration only
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(returnOwner.getId().toHexString());

        verify(ownerRepository).deleteById(any());
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(any())).thenReturn(returnOwnerCmd);

        OwnerCmd smith = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }
}