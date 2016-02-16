package com.marcelosantos.ecommerce.userinterface.itemview;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marcelosantos.ecommerce.R;
import com.marcelosantos.ecommerce.domain.model.Transaction;
import com.marcelosantos.ecommerce.userinterface.model.Month;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Marcelo on 14/02/2016.
 */
@EViewGroup(R.layout.item_layout_transaction)
public class TransactionItemView extends LinearLayout {

    @ViewById
    public TextView textViewPersonName;
    @ViewById
    public TextView textViewCreditCardType;
    @ViewById
    public TextView textViewCreditCardNumber;
    @ViewById
    public TextView textViewCreditCardExpirationDate;
    @ViewById
    public TextView textViewValue;

    public TransactionItemView(Context context) {

        super(context);
    }

    public void bind(Transaction transaction) {

        this.textViewPersonName.setText(transaction.getCreditCard().getPerson().getName());
        this.textViewCreditCardType.setText(String.format("%s: %s", super.getContext().getString(R.string.credit_card_type), transaction.getCreditCard().getCreditCardType().getDescription()));
        this.textViewCreditCardNumber.setText(String.format("%s: %s", super.getContext().getString(R.string.credit_card_number), String.valueOf(transaction.getCreditCard().formatNumber())));

        String creditCardExpirationDate = String.format("%s/%s", new Month(transaction.getCreditCard().getExpirationMonth()).toString(), String.valueOf(transaction.getCreditCard().getExpirationYear()));

        this.textViewCreditCardExpirationDate.setText(String.format("%s: %s", super.getContext().getString(R.string.expiration_date), creditCardExpirationDate));

        this.textViewValue.setText(String.format("%s: %s", super.getContext().getString(R.string.value),  String.valueOf(transaction.getValue())));
    }
}
