package com.marcelosantos.ecommerce.domain.client;

import com.marcelosantos.ecommerce.domain.client.model.ResponseApi;
import com.marcelosantos.ecommerce.domain.interfaces.client.ITransactionClient;
import com.marcelosantos.ecommerce.domain.model.Transaction;

import java.io.IOException;

/**
 * Created by Marcelo on 16/02/2016.
 */
public class TransactionClient extends BaseClient implements ITransactionClient {

    private String url = BaseClient.ROOT_URL + "/transactions";

    @Override
    public ResponseApi save(Transaction transaction) throws IOException {

        return super.post(this.url, null, ResponseApi.class, transaction);
    }
}