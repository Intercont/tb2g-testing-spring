package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @InjectMocks
    private VetController vetController;

    @Mock
    private ClinicService clinicService;

    @Mock
    private Map<String, Object> model;

    private List<Vet> vets;

    @BeforeEach
    void setUp() {
        Vet vet = new Vet();
        vet.setId(1);
        vet.setFirstName("Lucky");
        vet.setLastName("Fraga");

        vets = new ArrayList<>();
        vets.add(vet);

        given(clinicService.findVets()).willReturn(vets);
    }

    @Test
    void showVetList() {
        //when
        String result = vetController.showVetList(model);

        //then
        then(clinicService).should().findVets();
        then(model).should().put(anyString(), any());

        assertNotNull(result);
        assertEquals("vets/vetList", result);
    }

    @Test
    void showResourcesVetList() {
        //when
        Vets vetsResult = vetController.showResourcesVetList();

        //then
        then(clinicService).should().findVets();

        assertNotNull(vetsResult);
        assertEquals(1, vets.size());
        assertEquals(vets.stream().findAny().get().getId(), vetsResult.getVetList().stream().findAny().get().getId());
        assertEquals(vets.stream().findAny().get().getFirstName(), vetsResult.getVetList().stream().findAny().get().getFirstName());
        assertEquals(vets.stream().findAny().get().getLastName(), vetsResult.getVetList().stream().findAny().get().getLastName());
    }
}