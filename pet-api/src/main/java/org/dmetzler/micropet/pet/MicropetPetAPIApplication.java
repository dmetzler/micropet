package org.dmetzler.micropet.pet;

import org.dmetzler.micropet.pet.api.Pet;
import org.dmetzler.micropet.pet.db.PetDAO;
import org.dmetzler.micropet.pet.resources.PetResource;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MicropetPetAPIApplication extends Application<MicropetPetAPIConfiguration> {

    private final HibernateBundle<MicropetPetAPIConfiguration> hibernate = new HibernateBundle<MicropetPetAPIConfiguration>(Pet.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(MicropetPetAPIConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };



    public static void main(final String[] args) throws Exception {
        new MicropetPetAPIApplication().run(args);
    }

    @Override
    public String getName() {
        return "MicroPetAPI";
    }

    @Override
    public void initialize(final Bootstrap<MicropetPetAPIConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);

        bootstrap.addBundle(new MigrationsBundle<MicropetPetAPIConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(MicropetPetAPIConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final MicropetPetAPIConfiguration configuration, final Environment environment) {
        PetDAO petStore = new PetDAO(hibernate.getSessionFactory());
        final PetResource resource = new PetResource(petStore);
        environment.jersey().register(resource);
    }





}
