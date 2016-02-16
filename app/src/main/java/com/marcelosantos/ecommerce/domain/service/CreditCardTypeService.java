package com.marcelosantos.ecommerce.domain.service;

import android.content.Context;

import com.google.inject.Inject;
import com.marcelosantos.ecommerce.R;
import com.marcelosantos.ecommerce.domain.interfaces.client.ICreditCardTypeClient;
import com.marcelosantos.ecommerce.domain.interfaces.repository.ICreditCardRepository;
import com.marcelosantos.ecommerce.domain.interfaces.repository.ICreditCardTypeRepository;
import com.marcelosantos.ecommerce.domain.interfaces.service.ICreditCardTypeService;
import com.marcelosantos.ecommerce.domain.model.CreditCardType;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Marcelo on 13/02/2016.
 */
public class CreditCardTypeService implements ICreditCardTypeService {

    private ICreditCardTypeRepository creditCardTypeRepository;
    private ICreditCardTypeClient creditCardTypeClient;
    private Context context;

    @Inject
    public CreditCardTypeService(ICreditCardTypeRepository creditCardTypeRepository, ICreditCardTypeClient creditCardTypeClient, Context context) {

        this.creditCardTypeRepository = creditCardTypeRepository;
        this.creditCardTypeClient = creditCardTypeClient;
    }

    @Override
    public void addDefaultCreditCardType() {


    }

    public void getDefaultCreditCardTypeApi() throws SQLException {

        List<CreditCardType> listCreditCardType = this.creditCardTypeClient.getAll();

        for (CreditCardType creditCardType : listCreditCardType)
            this.creditCardTypeRepository.createOrUpdate(creditCardType);
    }
}
