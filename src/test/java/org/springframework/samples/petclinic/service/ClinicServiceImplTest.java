package org.springframework.samples.petclinic.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ClinicServiceImplTest {

    @InjectMocks
    private ClinicServiceImpl clinicService;

    @Mock
    private PetRepository petRepository;

    @Test
    void findPetTypes() {
        //given
        List<PetType> petTypes = new ArrayList<>();
        petTypes.add(new PetType());

        given(petRepository.findPetTypes()).willReturn(petTypes);

        //when
        Collection<PetType> petTypesResult = clinicService.findPetTypes();

        //then
        then(petRepository).should().findPetTypes();
        assertNotNull(petTypesResult);
        assertEquals(1, petTypesResult.size());
    }
}