package com.marcelosantos.ecommerce.domain.client;

import android.util.Log;

import com.marcelosantos.ecommerce.domain.interfaces.client.ICreditCardTypeClient;
import com.marcelosantos.ecommerce.domain.model.CreditCardType;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Marcelo on 14/02/2016.
 */
public class CreditCardTypeClient extends BaseClient implements ICreditCardTypeClient {

    private String url = BaseClient.ROOT_URL + "/creditcardtypes";

    @Override
    public List<CreditCardType> getAll() {

        try {

            CreditCardType[] listCreditCardType = super.get(this.url, null, CreditCardType[].class);
            return Arrays.asList(listCreditCardType);
        }
        catch (Exception e) {

            e.getStackTrace();
            Log.e("script", e.getMessage());

        }

        return null;
    }
}
