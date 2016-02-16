package com.marcelosantos.ecommerce.userinterface.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.widget.Button;

import com.github.pinball83.maskededittext.MaskedEditText;
import com.google.inject.Inject;
import com.marcelosantos.ecommerce.R;
import com.marcelosantos.ecommerce.domain.interfaces.repository.ICreditCardTypeRepository;
import com.marcelosantos.ecommerce.domain.interfaces.service.ITransactionService;
import com.marcelosantos.ecommerce.domain.model.CreditCard;
import com.marcelosantos.ecommerce.domain.model.CreditCardType;
import com.marcelosantos.ecommerce.domain.model.Person;
import com.marcelosantos.ecommerce.domain.model.Transaction;
import com.marcelosantos.ecommerce.infrastructure.common.StringUtil;
import com.marcelosantos.ecommerce.userinterface.adapter.SpinnerAdapter;
import com.marcelosantos.ecommerce.userinterface.model.Month;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.RoboGuice;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.codehaus.jackson.map.ObjectMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@EActivity(R.layout.activity_simulate_transaction)
@RoboGuice
@OptionsMenu(R.menu.menu_add_transaction)
public class SimulateTransactionActivity extends BaseActivity {

    @ViewById
    public AppCompatEditText editTextName;
    @ViewById
    public AppCompatSpinner spinnerCreditCardType;
    @ViewById
    public MaskedEditText editTextNumber;
    @ViewById
    public AppCompatSpinner spinnerExpirationMonth;
    @ViewById
    public AppCompatSpinner spinnerExpirationYear;
    @ViewById
    public AppCompatEditText editTextVerificationDigit;
    @ViewById
    public AppCompatEditText editTextValue;
    @ViewById
    public Button buttonSave;

    @Inject
    public ICreditCardTypeRepository creditCardTypeRepository;
    @Inject
    public ITransactionService transactionService;

    private Transaction transaction;

    @AfterViews
    public void init() {

        try {

            super.configureActionBar();
            super.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            super.setTitle(R.string.simulate_transaction);

            this.loadData();
        }
        catch (Exception e) {

            super.catchException(e);
        }
    }

    @Click(R.id.buttonSave)
    public void onButtonSaveClicked() {

        try {

            this.save();
        }
        catch (Exception e) {

            super.catchException(e);
        }
    }

    @OptionsItem(R.id.menuSave)
    public void onMenuSaveClicked() {

        try {

            this.save();
        }
        catch (Exception e) {

            super.catchException(e);
        }
    }

    @OptionsItem(android.R.id.home)
    public void onBackClicked() {

        super.onBackPressed();
    }

    private void save() {

        super.showProgress();

        this.saveBackground();
    }

    @Background
    public void saveBackground() {

        try {

            this.loadObject();
            this.transactionService.save(this.transaction);

            this.saveTransactionResult();
        }
        catch (Exception e) {

            this.onError(e);
        }
    }

    @UiThread
    public void onError(Exception e) {

        super.catchException(e);
    }

    @UiThread
    public void saveTransactionResult() {

        try {

            super.removeProgress();

            Intent returnIntent = new Intent();
            returnIntent.putExtra(super.getString(R.string.key_transaction), new ObjectMapper().writeValueAsString(this.transaction));
            super.setResult(super.RESULT_OK, returnIntent);
            super.finish();
        }
        catch (Exception e) {

            super.catchException(e);
        }
    }

    private void loadData() throws SQLException {

        this.loadCardcreditType();
        this.loadExpirationDate();
    }

    private void loadCardcreditType() throws SQLException {

        List<CreditCardType> listCreditCardType = this.creditCardTypeRepository.queryForAll();

        CreditCardType defaultCreditCardType = new CreditCardType();
        defaultCreditCardType.setDescription(super.getString(R.string.select));

        listCreditCardType.add(0, defaultCreditCardType);
        this.spinnerCreditCardType.setAdapter(new SpinnerAdapter(this, listCreditCardType));
    }

    private void loadExpirationDate() {

        this.loadExpirationMonth();
        this.loadExpirationYear();
    }

    private void loadExpirationMonth() {

        List<Month> listMonth = new ArrayList<>();

        for (int i = 1; i <= 12; i++)
            listMonth.add(new Month(i));

        //// TODO: 16/02/2016 think about adding default month

        this.spinnerExpirationMonth.setAdapter(new SpinnerAdapter(this, listMonth));
    }

    private void loadExpirationYear() {

        List<Integer> listYear = new ArrayList<>();

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        for (int i = 0; i <= 10; i++)
            listYear.add(currentYear + i);

        //// TODO: 16/02/2016 think about adding default year

        this.spinnerExpirationYear.setAdapter(new SpinnerAdapter(this, listYear));
    }

    private void loadObject() {

        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID());

        CreditCard creditCard = new CreditCard();
        creditCard.setId(UUID.randomUUID());

        CreditCardType selectedCreditCardType = (CreditCardType) this.spinnerCreditCardType.getSelectedItem();

        if (selectedCreditCardType.getId() != null)
            creditCard.setCreditCardType(selectedCreditCardType);

        Month selectedMonth = (Month)this.spinnerExpirationMonth.getSelectedItem();
        creditCard.setExpirationMonth(selectedMonth.getNumber());
        creditCard.setExpirationYear((int) this.spinnerExpirationYear.getSelectedItem());

        creditCard.setNumber(this.editTextNumber.getUnmaskedText().toString());

        int verificationDigit = 0;

        String verificationDigitString = this.editTextVerificationDigit.getText().toString();

        if (!StringUtil.isNullOrEmpty(verificationDigitString) && TextUtils.isDigitsOnly(verificationDigitString))
            verificationDigit = Integer.valueOf(verificationDigitString);

        creditCard.setVerificationDigit(verificationDigit);

        Person person = new Person();
        person.setId(UUID.randomUUID());
        person.setName(this.editTextName.getText().toString());
        creditCard.setPerson(person);

        double value = 0;

        String valueString = this.editTextValue.getText().toString();

        //// TODO: 16/02/2016 verify if it´s a valid number
        if (!StringUtil.isNullOrEmpty(valueString))
            value = Double.valueOf(valueString);

        transaction.setCreditCard(creditCard);
        transaction.setValue(value);

        this.transaction = transaction;
    }
}
