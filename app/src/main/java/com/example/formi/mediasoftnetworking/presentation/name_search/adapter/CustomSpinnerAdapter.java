package com.example.formi.mediasoftnetworking.presentation.name_search.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.formi.mediasoftnetworking.R;

public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter{

    private Context ctx;
    private String[] items;

    public CustomSpinnerAdapter(Context ctx, String[] items){
        this.ctx = ctx;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txt = new TextView(ctx);
        txt.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        txt.setPadding(16, 16, 16, 16);
        txt.setTextSize(16);
        txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_dropdown, 0);
        txt.setText(items[position]);
        txt.setTextColor(Color.parseColor("#000000"));
        return  txt;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView txt = new TextView(ctx);
        txt.setPadding(24, 16, 24, 16);
        txt.setTextSize(18);
        txt.setGravity(Gravity.CENTER_VERTICAL);
        txt.setText(items[position]);
        txt.setTextColor(Color.parseColor("#000000"));
        return txt;
    }
}
