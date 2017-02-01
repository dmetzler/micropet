package org.dmetzler.micropet.pet.api;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@NamedQueries({ @NamedQuery(name = Pet.FIND_ALL, query = "SELECT p FROM Pet p") })
public class Pet implements Serializable {

    public static final String FIND_ALL = "org.dmetzler.micropet.pet.api.findAll";
    /**
     *
     */
    private static final long serialVersionUID = -5728687331801782728L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;
    private final String name;
    private final String description;

    private final BigDecimal price;

    public Pet() {
        id = null;
        name = "";
        description = "";
        price = new BigDecimal(0);
    }

    @JsonCreator
    public Pet(@JsonProperty("id") Long id, @JsonProperty("name") String name,
            @JsonProperty("description") String description, @JsonProperty("price") BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;

    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public BigDecimal getPrice() {
        return price;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pet other = (Pet) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        return true;
    }

    public static class Builder {
        private String name;
        private String description = "";
        private BigDecimal price = new BigDecimal(0);
        private Long id;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Pet build() {
            return new Pet(id, name, description, price);
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

    }

    @JsonProperty
    public Long getId() {
        return id;
    }

}
