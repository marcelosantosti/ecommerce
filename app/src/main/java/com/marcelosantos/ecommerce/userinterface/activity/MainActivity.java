package com.marcelosantos.ecommerce.userinterface.activity;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.inject.Inject;
import com.marcelosantos.ecommerce.R;
import com.marcelosantos.ecommerce.domain.interfaces.repository.ITransactionRepository;
import com.marcelosantos.ecommerce.domain.interfaces.service.ICreditCardTypeService;
import com.marcelosantos.ecommerce.domain.model.Transaction;
import com.marcelosantos.ecommerce.infrastructure.common.SharedPreferencesUtil;
import com.marcelosantos.ecommerce.userinterface.adapter.TransactionAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.RoboGuice;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.codehaus.jackson.map.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;

@EActivity(R.layout.activity_main)
@RoboGuice
public class MainActivity extends BaseActivity {

    @ViewById
    public CardView cardViewNoTransaction;
    @ViewById
    public RecyclerView recyclerView;

    @Bean
    public TransactionAdapter transactionAdapter;

    @Inject
    public ITransactionRepository transactionRepository;
    @Inject
    public ICreditCardTypeService creditCardTypeService;

    private List<Transaction> listTransaction;
    private SharedPreferencesUtil sharedPreferencesUtil;
    private String keyLoadDefaultCreditCardType;

    @AfterViews
    public void init() {

        try {

            super.configureActionBar();
            super.setTitle(R.string.app_name);

            this.keyLoadDefaultCreditCardType = super.getString(R.string.key_load_default_credit_card_type);
            this.sharedPreferencesUtil = new SharedPreferencesUtil(this);

            this.getDefaultCreditCardType();
            this.loadTransactions();
        }
        catch (Exception e) {

            super.catchException(e);
        }
    }

    private void getDefaultCreditCardType() {

        boolean loadDefaultCreditCardType = this.sharedPreferencesUtil.getBoolean(this.keyLoadDefaultCreditCardType, true);

        if (loadDefaultCreditCardType) {

            super.showProgress();
            this.getDefaultCreditCardTypeApi();
        }
    }

    @Background
    public void getDefaultCreditCardTypeApi() {

        try {

            this.creditCardTypeService.getDefaultCreditCardTypeApi();
            this.getDefaultCreditCardTypeApiResult();

        } catch (SQLException e) {

            this.onError(e);
        }
    }

    @UiThread
    public void onError(Exception e) {

        super.catchException(e);
    }

    @UiThread
    public void getDefaultCreditCardTypeApiResult() {

        super.removeProgress();
        sharedPreferencesUtil.putBoolean(this.keyLoadDefaultCreditCardType, false);
    }

    private void loadTransactions() throws SQLException {

        this.listTransaction = this.transactionRepository.queryForAll();

        this.setUiVisibility();

        this.transactionAdapter.setItems(this.listTransaction);

        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setItemAnimator(new FadeInAnimator());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.transactionAdapter);
    }

    private void setUiVisibility() {

        if (this.listTransaction.size() == 0) {

            this.cardViewNoTransaction.setVisibility(View.VISIBLE);
            this.recyclerView.setVisibility(View.GONE);
        }
        else {

            this.cardViewNoTransaction.setVisibility(View.GONE);
            this.recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Click(R.id.buttonAddCreditCard)
    public void onButtonAddCreditCardClicked() {

        try {

            AddTransactionActivity_.intent(this).startForResult(0);
        }
        catch (Exception e) {

            super.catchException(e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {

            if (data != null) {

                String transactionJson = data.getStringExtra(super.getString(R.string.key_transaction));
                Transaction transaction = new ObjectMapper().readValue(transactionJson, Transaction.class);

                int position = this.transactionAdapter.getItemCount();

                if (position != 0)
                    this.transactionAdapter.add(transaction, position);
                else {

                    this.listTransaction.add(transaction);
                    this.transactionAdapter.setItems(this.listTransaction);
                }

                this.setUiVisibility();
            }
        }
        catch (Exception e) {

            super.catchException(e);
        }
    }
}
