package com.marcelosantos.ecommerce.infrastructure.data;

import android.content.Context;

import com.google.inject.Inject;
import com.marcelosantos.ecommerce.domain.interfaces.repository.ITransactionRepository;
import com.marcelosantos.ecommerce.domain.model.Transaction;

import java.util.UUID;

/**
 * Created by Marcelo on 13/02/2016.
 */
public class TransactionRepository extends Repository<Transaction, UUID> implements ITransactionRepository {

    @Inject
    public TransactionRepository(Context context) {

        super();
        super.setOrmLiteSqliteOpenHelperClass(DatabaseHelper.class);
        super.setEntityClass(Transaction.class);
        super.setContext(context);
    }
}
