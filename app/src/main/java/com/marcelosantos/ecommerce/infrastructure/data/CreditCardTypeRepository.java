package com.marcelosantos.ecommerce.infrastructure.data;

import android.content.Context;

import com.google.inject.Inject;
import com.marcelosantos.ecommerce.domain.interfaces.repository.ICreditCardTypeRepository;
import com.marcelosantos.ecommerce.domain.model.CreditCardType;

import java.util.UUID;

/**
 * Created by Marcelo on 13/02/2016.
 */
public class CreditCardTypeRepository extends Repository<CreditCardType, UUID> implements ICreditCardTypeRepository {

    @Inject
    public CreditCardTypeRepository(Context context) {

        super();
        super.setOrmLiteSqliteOpenHelperClass(DatabaseHelper.class);
        super.setEntityClass(CreditCardType.class);
        super.setContext(context);
    }
}