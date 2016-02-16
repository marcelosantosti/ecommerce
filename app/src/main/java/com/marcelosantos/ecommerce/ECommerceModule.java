package com.marcelosantos.ecommerce;

import com.google.inject.AbstractModule;
import com.marcelosantos.ecommerce.domain.client.CreditCardTypeClient;
import com.marcelosantos.ecommerce.domain.client.TransactionClient;
import com.marcelosantos.ecommerce.domain.interfaces.client.ICreditCardTypeClient;
import com.marcelosantos.ecommerce.domain.interfaces.client.ITransactionClient;
import com.marcelosantos.ecommerce.domain.interfaces.repository.ICreditCardRepository;
import com.marcelosantos.ecommerce.domain.interfaces.repository.ICreditCardTypeRepository;
import com.marcelosantos.ecommerce.domain.interfaces.repository.IPersonRepository;
import com.marcelosantos.ecommerce.domain.interfaces.repository.ITransactionRepository;
import com.marcelosantos.ecommerce.domain.interfaces.service.ICreditCardTypeService;
import com.marcelosantos.ecommerce.domain.interfaces.service.ITransactionService;
import com.marcelosantos.ecommerce.domain.service.CreditCardTypeService;
import com.marcelosantos.ecommerce.domain.service.TransactionService;
import com.marcelosantos.ecommerce.infrastructure.data.CreditCardRepository;
import com.marcelosantos.ecommerce.infrastructure.data.CreditCardTypeRepository;
import com.marcelosantos.ecommerce.infrastructure.data.PersonRepository;
import com.marcelosantos.ecommerce.infrastructure.data.TransactionRepository;

/**
 * Created by Marcelo on 13/02/2016.
 */
public class ECommerceModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(ICreditCardRepository.class).to(CreditCardRepository.class);
        bind(ICreditCardTypeRepository.class).to(CreditCardTypeRepository.class);
        bind(IPersonRepository.class).to(PersonRepository.class);
        bind(ITransactionRepository.class).to(TransactionRepository.class);

        bind(ICreditCardTypeClient.class).to(CreditCardTypeClient.class);
        bind(ITransactionClient.class).to(TransactionClient.class);

        bind(ICreditCardTypeService.class).to(CreditCardTypeService.class);
        bind(ITransactionService.class).to(TransactionService.class);
    }
}
