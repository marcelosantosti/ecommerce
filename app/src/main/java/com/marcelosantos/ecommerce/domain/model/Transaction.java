package com.marcelosantos.ecommerce.domain.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

/**
 * Created by Marcelo on 13/02/2016.
 */
@DatabaseTable
public class Transaction {

    public static final String ID_CREDIT_CARD_FIELD_NAME = "idCreditCard";

    @DatabaseField(id = true)
    private UUID id;
    @DatabaseField
    private double value;

    @DatabaseField(canBeNull = true, foreign = true, columnName = Transaction.ID_CREDIT_CARD_FIELD_NAME, foreignAutoRefresh = true)
    private CreditCard creditCard;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
