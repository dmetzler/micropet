package org.dmetzler.micropet.pet.api;
import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

public class CategoryTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void it_can_build_a_category() throws Exception {

        Category cat = new Category.Builder().name("dog").description("dog description").build();
        assertThat(cat.getName()).isEqualTo("dog");
        assertThat(cat.getDescription()).isEqualTo("dog description");

    }

    @Test
    public void it_can_build_incomplete_category() throws Exception {
        Category cat = new Category.Builder().name("dog").build();
        assertThat(cat.getName()).isEqualTo("dog");
        assertThat(cat.getDescription()).isEmpty();

    }

    @Test(expected=IllegalArgumentException.class)
    public void it_cant_build_a_category_without_name() throws Exception {
        new Category.Builder().description("description").build();
    }

    @Test
    public void category_can_be_serialized() throws Exception {
        Category cat = new Category.Builder().name("dog").description("a category for dogs").build();

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/category.json"), Category.class));
        assertThat(MAPPER.writeValueAsString(cat)).isEqualTo(expected);
    }
}
