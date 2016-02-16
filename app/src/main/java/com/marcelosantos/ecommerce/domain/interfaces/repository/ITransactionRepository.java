package com.marcelosantos.ecommerce.domain.interfaces.repository;

import com.marcelosantos.ecommerce.domain.model.Transaction;

import java.util.UUID;

/**
 * Created by Marcelo on 13/02/2016.
 */
public interface ITransactionRepository extends IRepository<Transaction, UUID> {
}
