package com.marcelosantos.ecommerce.domain.service;

import android.content.Context;

import com.google.inject.Inject;
import com.marcelosantos.ecommerce.R;
import com.marcelosantos.ecommerce.domain.client.model.ResponseApi;
import com.marcelosantos.ecommerce.domain.interfaces.client.ITransactionClient;
import com.marcelosantos.ecommerce.domain.interfaces.repository.ICreditCardRepository;
import com.marcelosantos.ecommerce.domain.interfaces.repository.IPersonRepository;
import com.marcelosantos.ecommerce.domain.interfaces.repository.ITransactionRepository;
import com.marcelosantos.ecommerce.domain.interfaces.service.ITransactionService;
import com.marcelosantos.ecommerce.domain.model.Transaction;
import com.marcelosantos.ecommerce.infrastructure.common.StringUtil;
import com.marcelosantos.ecommerce.infrastructure.common.ValidationException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Marcelo on 14/02/2016.
 */
public class TransactionService implements ITransactionService {

    private ICreditCardRepository creditCardRepository;
    private IPersonRepository personRepository;
    private ITransactionRepository transactionRepository;
    private ITransactionClient transactionClient;
    private Context context;

    @Inject
    public TransactionService(ICreditCardRepository creditCardRepository, IPersonRepository personRepository, ITransactionRepository transactionRepository, ITransactionClient transactionClient, Context context) {

        this.creditCardRepository = creditCardRepository;
        this.personRepository = personRepository;
        this.transactionRepository = transactionRepository;
        this.transactionClient = transactionClient;
        this.context = context;
    }

    @Override
    public void save(Transaction transaction) throws ValidationException, SQLException, IOException {

        this.validate(transaction);

        ResponseApi responseApi = this.transactionClient.save(transaction);

        if (responseApi.isSucess()) {

            this.personRepository.create(transaction.getCreditCard().getPerson());
            this.creditCardRepository.create(transaction.getCreditCard());
            this.transactionRepository.create(transaction);
        }
        else
            throw new ValidationException(responseApi.getErrorMessage());
    }

    private void validate(Transaction transaction) throws ValidationException {

        StringBuilder stringBuilder = new StringBuilder();

        if (StringUtil.isNullOrEmpty(transaction.getCreditCard().getPerson().getName()))
            stringBuilder.append(this.context.getString(R.string.required_name));

        if (transaction.getCreditCard().getCreditCardType() == null)
            stringBuilder.append(this.context.getString(R.string.credit_card_type));

        if (StringUtil.isNullOrEmpty(transaction.getCreditCard().getNumber()))
            stringBuilder.append(this.context.getString(R.string.required_credit_card_number));
        else {

            if (transaction.getCreditCard().getNumber().length() != 16)
                stringBuilder.append(this.context.getString(R.string.digits_credit_card_number));
        }

        if (transaction.getCreditCard().getExpirationMonth() == 0)
            stringBuilder.append(this.context.getString(R.string.required_expiration_month));

        if (transaction.getCreditCard().getExpirationYear() == 0)
            stringBuilder.append(this.context.getString(R.string.required_expiration_year));

        if (transaction.getCreditCard().getVerificationDigit() == 0)
            stringBuilder.append(this.context.getString(R.string.required_verification_digit));
        else {

            if (String.valueOf(transaction.getCreditCard().getVerificationDigit()).length() != 3)
                stringBuilder.append(this.context.getString(R.string.digits_verification_digits));
        }

        if (transaction.getValue() == 0)
            stringBuilder.append(this.context.getString(R.string.required_value));

        if (stringBuilder.length() != 0)
            throw new ValidationException(stringBuilder.toString());
    }
}
