package com.marcelosantos.ecommerce.userinterface.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.marcelosantos.ecommerce.domain.model.Transaction;
import com.marcelosantos.ecommerce.userinterface.adapter.holder.ViewWrapper;
import com.marcelosantos.ecommerce.userinterface.itemview.TransactionItemView;
import com.marcelosantos.ecommerce.userinterface.itemview.TransactionItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by Marcelo on 14/02/2016.
 */
@EBean
public class TransactionAdapter extends BaseRecyclerViewAdapter<Transaction, TransactionItemView>  {

    @RootContext
    public Context context;

    @Override
    protected TransactionItemView onCreateItemView(ViewGroup parent, int viewType) {

        return TransactionItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<TransactionItemView> viewHolder, int position) {

        TransactionItemView view = viewHolder.getView();
        Transaction transaction = getItems().get(position);

        view.bind(transaction);
    }

    public void add(Transaction transaction, int position) {

        super.getItems().add(position, transaction);
        super.notifyItemInserted(position);
    }
}
