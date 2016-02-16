package com.marcelosantos.ecommerce.domain.client;

import android.util.Log;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Marcelo on 13/02/2016.
 */
public class BaseClient {

    public static final String ROOT_URL = "http://private-6ab87-ecommerce10.apiary-mock.com";

    protected RestTemplate restTemplate;

    public BaseClient() {

        this.restTemplate = new RestTemplate();
        this.restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
    }

    public <T> T get(String url, HashMap<String, Object> urlVariables, Class<T> returnType) {

        try {

            if (urlVariables == null)
                urlVariables = new HashMap<>();

            T object = this.restTemplate.exchange(url, HttpMethod.GET, null, returnType, urlVariables).getBody();

            return object;
        } catch (Exception e) {
            Log.e("Erro", e.getMessage());
        }

        return null;
    }

    private <T> T sendRequest(String url, HashMap<String, Object> urlVariables, Class<T> returnType, Object objectBody, HttpMethod httpMethod) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();

        String jsonObject = objectMapper.writeValueAsString(objectBody);

        HttpEntity<String> httpEntity = new HttpEntity<String>(jsonObject);

        if (urlVariables == null)
            urlVariables = new HashMap<>();

        T object = this.restTemplate.exchange(url, httpMethod, httpEntity, returnType, urlVariables).getBody();

        return object;

    }

    public <T> T post(String url, HashMap<String, Object> urlVariables, Class<T> returnType, Object objectBody) throws IOException {

        return this.sendRequest(url, urlVariables, returnType, objectBody, HttpMethod.POST);
    }
}
