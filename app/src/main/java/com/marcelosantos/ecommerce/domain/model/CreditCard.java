package com.marcelosantos.ecommerce.domain.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.marcelosantos.ecommerce.infrastructure.common.StringUtil;

import java.util.UUID;

/**
 * Created by Marcelo on 13/02/2016.
 */
@DatabaseTable
public class CreditCard {

    public static final String ID_CREDIT_CARD_TYPE_FIELD_NAME = "idCreditCardType";
    public static final String ID_PERSON_TYPE_FIELD_NAME = "idPerson";

    @DatabaseField(id = true)
    private UUID id;
    @DatabaseField
    private String number;
    @DatabaseField
    private int expirationMonth;
    @DatabaseField
    private int expirationYear;
    @DatabaseField
    private int verificationDigit;

    @DatabaseField(canBeNull = true, foreign = true, columnName = CreditCard.ID_CREDIT_CARD_TYPE_FIELD_NAME, foreignAutoRefresh = true)
    private CreditCardType creditCardType;

    @DatabaseField(canBeNull = true, foreign = true, columnName = CreditCard.ID_PERSON_TYPE_FIELD_NAME, foreignAutoRefresh = true)
    private Person person;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public int getVerificationDigit() {
        return verificationDigit;
    }

    public void setVerificationDigit(int verificationDigit) {
        this.verificationDigit = verificationDigit;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String formatNumber() {

        String formattedNumber = "";

        try {

            if (!StringUtil.isNullOrEmpty(this.number) && this.number.length() == 16)
                formattedNumber = String.format("%s %s %s %s", this.number.substring(0, 4), this.number.substring(4, 8), this.number.substring(8, 12), this.number.substring(12, 16));
        }
        catch (Exception e) {

            formattedNumber = this.number;
        }

        return formattedNumber;
    }
}
