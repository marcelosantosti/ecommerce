package com.marcelosantos.ecommerce.domain.interfaces.service;

import com.marcelosantos.ecommerce.domain.model.Transaction;
import com.marcelosantos.ecommerce.infrastructure.common.ValidationException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Marcelo on 14/02/2016.
 */
public interface ITransactionService {

    void save(Transaction transaction) throws ValidationException, SQLException, IOException;
}
