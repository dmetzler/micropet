package org.dmetzler.micropet.pet.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static javax.ws.rs.client.Entity.entity;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.dmetzler.micropet.pet.api.Pet;
import org.dmetzler.micropet.pet.db.PetDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import io.dropwizard.testing.junit.ResourceTestRule;

public class PetResourceTest {
    private static final PetDAO dao = mock(PetDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new PetResource(dao))
            .build();

    private static final Pet pet1 = Pet.builder().id(1L).name("titi").description("a tiny Canary").build();

    private static final Pet pet2 = Pet.builder().id(2L).name("rominet").description("a bad cat").build();

    @Before
    public void doBefore() {
        when(dao.findById(eq(1L))).thenReturn(pet1);
        when(dao.findById(eq(2L))).thenReturn(pet2);
        when(dao.findAll()).thenReturn(new ArrayList<Pet>(){{ add(pet1);add(pet2);}});
    }

    @After
    public void tearDown() {
        // we have to reset the mock after each test because of the
        // @ClassRule, or use a @Rule as mentioned below.
        reset(dao);
    }

    @Test
    public void it_can_retrieve_a_pet() {
        assertThat(resources.client().target("/api/pet/v1/1").request().get(Pet.class)).isEqualTo(pet1);
        verify(dao).findById(1L);
    }

    @Test
    public void it_can_list_pets() throws Exception {
        List<Pet> pets = resources.client().target("/api/pet/v1").request().get(List.class);
        assertThat(pets).hasSize(2);
        verify(dao).findAll();
    }

    @Test
    public void it_can_create_a_pet() throws Exception {
        resources.client().target("/api/pet/v1").request().post(entity(pet1, MediaType.APPLICATION_JSON));
        verify(dao).create(eq(pet1));
    }

}
