package com.marcelosantos.ecommerce.domain.interfaces.client;

import com.marcelosantos.ecommerce.domain.client.model.ResponseApi;
import com.marcelosantos.ecommerce.domain.model.Transaction;

import java.io.IOException;

/**
 * Created by Marcelo on 16/02/2016.
 */
public interface ITransactionClient {

    ResponseApi save(Transaction transaction) throws IOException;
}
