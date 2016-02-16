package com.marcelosantos.ecommerce.domain.interfaces.client;

import com.marcelosantos.ecommerce.domain.model.CreditCardType;

import java.util.List;

/**
 * Created by Marcelo on 14/02/2016.
 */
public interface ICreditCardTypeClient {

    List<CreditCardType> getAll();
}
