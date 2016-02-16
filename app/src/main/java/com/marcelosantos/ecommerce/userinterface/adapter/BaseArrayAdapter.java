package com.marcelosantos.ecommerce.userinterface.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by marcelo.santos on 14/02/2016.
 */
public class BaseArrayAdapter<T> extends ArrayAdapter<T> {

    private List<T> listObject;
    private Context context;

    public BaseArrayAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {

        super(context, 0, 0, objects);
        this.context = context;
        this.listObject = objects;
    }

    @Override
    public int getCount() {
        return this.listObject.size();
    }

    @Override
    public T getItem(int position) {
        return this.listObject.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    protected List<T> getListObject() {
        return listObject;
    }

    protected void setListObject(List<T> listObject) {
        this.listObject = listObject;
    }
}