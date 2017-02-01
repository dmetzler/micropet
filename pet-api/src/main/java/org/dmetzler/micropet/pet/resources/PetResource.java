package org.dmetzler.micropet.pet.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dmetzler.micropet.pet.api.Pet;
import org.dmetzler.micropet.pet.db.PetDAO;

import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.UnitOfWork;


@Path("/api/pet/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetResource {

    private final PetDAO dao;

    public PetResource(PetDAO dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    @UnitOfWork
    public List<Pet> listAll() {
        return dao.findAll();
    }

    @POST
    @Timed
    @UnitOfWork
    public Pet createPet(Pet pet) {
        long id = dao.create(pet);
        return dao.findById(id);
    }


    @Path("{id}")
    @GET
    @Timed
    @UnitOfWork
    public Pet doGetPet(@PathParam("id") Long id) {
        return dao.findById(id);
    }

}
