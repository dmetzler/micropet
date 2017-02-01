package org.dmetzler.micropet.pet.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.dmetzler.micropet.pet.api.Pet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.dropwizard.testing.junit.DAOTestRule;

public class PetDaoTest {

    @Rule
    public DAOTestRule database  = DAOTestRule.newBuilder().addEntityClass(Pet.class).build();

    private PetDAO petDAO;

    @Before
    public void setUp() {
        petDAO = new PetDAO(database.getSessionFactory());
    }

    @Test
    public void can_save_a_pet() throws Exception {
        Pet pet = new Pet.Builder().name("titi").build();
        long id = database.inTransaction(() -> {
                return petDAO.create(pet);
        });

        Pet retrievedPet = petDAO.findById(id);
        assertThat(retrievedPet).isNotNull();
        assertThat(retrievedPet.getName()).isEqualTo(pet.getName());

    }

}
