package com.marcelosantos.ecommerce.infrastructure.data;

import android.content.Context;

import com.google.inject.Inject;
import com.marcelosantos.ecommerce.domain.interfaces.repository.IPersonRepository;
import com.marcelosantos.ecommerce.domain.model.Person;

import java.util.UUID;

/**
 * Created by Marcelo on 13/02/2016.
 */
public class PersonRepository extends Repository<Person, UUID> implements IPersonRepository {

    @Inject
    public PersonRepository(Context context) {

        super();
        super.setOrmLiteSqliteOpenHelperClass(DatabaseHelper.class);
        super.setEntityClass(Person.class);
        super.setContext(context);
    }
}