package org.dmetzler.micropet.pet.db;

import java.util.List;

import org.dmetzler.micropet.pet.api.Pet;
import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;

public class PetDAO extends AbstractDAO<Pet> {

    public PetDAO(SessionFactory factory) {
        super(factory);
    }

    public Pet findById(Long id) {
        return get(id);
    }

    public long create(Pet pet) {
        return persist(pet).getId();
    }

    public List<Pet> findAll() {
        return list(namedQuery(Pet.FIND_ALL));
    }

}
