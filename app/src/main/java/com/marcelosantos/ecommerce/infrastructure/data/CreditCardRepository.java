package com.marcelosantos.ecommerce.infrastructure.data;

import android.content.Context;

import com.google.inject.Inject;
import com.marcelosantos.ecommerce.domain.interfaces.repository.ICreditCardRepository;
import com.marcelosantos.ecommerce.domain.model.CreditCard;

import java.util.UUID;

/**
 * Created by Marcelo on 13/02/2016.
 */
public class CreditCardRepository extends Repository<CreditCard, UUID> implements ICreditCardRepository {

    @Inject
    public CreditCardRepository(Context context) {

        super();
        super.setOrmLiteSqliteOpenHelperClass(DatabaseHelper.class);
        super.setEntityClass(CreditCard.class);
        super.setContext(context);
    }
}