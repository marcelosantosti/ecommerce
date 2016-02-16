package com.marcelosantos.ecommerce.infrastructure.common;

/**
 * Created by Marcelo on 16/02/2016.
 */
public class StringUtil {

    public static Boolean isNullOrEmpty(String value) {

        return value == null || value.equals("");
    }
}
