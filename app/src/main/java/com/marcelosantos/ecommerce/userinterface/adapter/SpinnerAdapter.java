package com.marcelosantos.ecommerce.userinterface.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marcelosantos.ecommerce.R;
import com.marcelosantos.ecommerce.infrastructure.common.LogUtil;

import java.util.List;

/**
 * Created by Marcelo on 14/02/2016.
 */
public class SpinnerAdapter <T> extends BaseArrayAdapter<T> {

    private TextView textView;
    private List<T> listObject;
    private Context context;
    private int layoutResourceId;

    public SpinnerAdapter(Context context, List<T> listObject) {

        super(context, 0, 0, listObject);
        this.context = context;
        this.listObject = listObject;
        this.layoutResourceId = R.layout.item_spinner;

    }

    @Override
    public int getCount() {
        return this.listObject.size();
    }


    @Override
    public T getItem(int position) {
        return this.listObject.get(position);
    }

    public Context getContext() {
        return context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = new View(this.getContext());

        try {

            LayoutInflater inflater = LayoutInflater.from(this.context);
            view = inflater.inflate(this.layoutResourceId, null);
            this.textView = (TextView) view.findViewById(R.id.textView);
            this.textView.setText(this.listObject.get(position).toString());

            return view;
        } catch (Exception ex) {

            LogUtil.catchException(this.context, ex);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        try {

            return getView(position, convertView, parent);
        } catch (Exception e) {
            LogUtil.catchException(this.context, e);
            return convertView;
        }
    }

    private View initView(int position, View convertView) {
        try {

            convertView = View.inflate(getContext(),  R.layout.item_spinner_dropdown, null);

            TextView textView = (TextView) convertView.findViewById(R.id.textView);
            textView.setText(this.listObject.get(position).toString());
        } catch (Exception ex) {

            LogUtil.catchException(this.context, ex);
        }

        return convertView;
    }
}
