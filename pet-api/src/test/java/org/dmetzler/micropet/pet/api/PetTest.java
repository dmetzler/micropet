package org.dmetzler.micropet.pet.api;


import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

public class PetTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void it_can_create_a_pet() throws Exception {
        Pet pet = new Pet.Builder().name("titi").description("a tiny bird").price(new BigDecimal(4)).build();

        assertThat(pet.getName()).isEqualTo("titi");
        assertThat(pet.getDescription()).isEqualTo("a tiny bird");
        assertThat(pet.getPrice()).isEqualByComparingTo("4");
    }

    @Test
    public void pet_has_default_values() throws Exception {
        Pet pet = new Pet.Builder().name("titi").build();
        assertThat(pet.getDescription()).isEmpty();
        assertThat(pet.getPrice()).isEqualByComparingTo("0");
    }

    @Test
    public void pet_can_be_serialized() throws Exception {

        Pet pet = new Pet.Builder().name("titi").description("a tiny bird").price(new BigDecimal(4)).build();

            final String expected = MAPPER.writeValueAsString(
                    MAPPER.readValue(fixture("fixtures/pet.json"), Pet.class));
            assertThat(MAPPER.writeValueAsString(pet)).isEqualTo(expected);
    }
}
