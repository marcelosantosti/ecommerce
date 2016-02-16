package com.marcelosantos.ecommerce.domain.client.model;

/**
 * Created by Marcelo on 16/02/2016.
 */
public class ResponseApi {

    private boolean sucess;
    private String errorMessage;

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
