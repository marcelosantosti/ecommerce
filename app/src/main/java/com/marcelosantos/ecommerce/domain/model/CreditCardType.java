package com.marcelosantos.ecommerce.domain.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

/**
 * Created by Marcelo on 13/02/2016.
 */
@DatabaseTable
public class CreditCardType {

    @DatabaseField(id = true)
    private UUID id;
    @DatabaseField
    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {

        return this.description;
    }
}
