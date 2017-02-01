package org.dmetzler.micropet.pet.api;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Category implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4979833939553244242L;

    private final String name;

    private final String description;

    @JsonCreator
    public Category(@JsonProperty("name") String name, @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    public static class Builder {

        private String name;
        private String description = "";

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Category build() {
            if (StringUtils.isNoneBlank(name)) {
                return new Category(name, description);
            } else {
                throw new IllegalArgumentException("You must give a name to a category");
            }
        }
    }

}
