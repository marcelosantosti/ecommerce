package com.marcelosantos.ecommerce.domain.interfaces.service;

import java.sql.SQLException;

/**
 * Created by Marcelo on 13/02/2016.
 */
public interface ICreditCardTypeService {

    void addDefaultCreditCardType();
    void getDefaultCreditCardTypeApi() throws SQLException;
}
