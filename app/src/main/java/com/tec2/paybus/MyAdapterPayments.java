package com.tec2.paybus;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Matteo on 24/08/2015.
 */
public class MyAdapterPayments extends FirebaseRecyclerAdapter<MyAdapterPayments.ViewHolder, Transaccion> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView indicadorColorRuta;
        private TextView textViewNombre;
        private TextView textViewDireccion;

        public ViewHolder(View view) {
            super(view);
            view.setClickable(true);
            indicadorColorRuta = view.findViewById(R.id.ivColorIndicador);
            textViewNombre = view.findViewById(R.id.textViewCalle);
            textViewDireccion = view.findViewById(R.id.textViewDireccion);
        }
    }

    public MyAdapterPayments(Query query, Class<Transaccion> itemClass, @Nullable ArrayList<Transaccion> items,
                             @Nullable ArrayList<String> keys) {
        super(query, itemClass, items, keys);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaccion item = getItem(position);
        holder.indicadorColorRuta.setImageResource(R.drawable.ic_money);
        holder.textViewNombre.setText("Pagaste " + item.getMonto() + ", ruta " + item.getRuta());
        holder.textViewDireccion.setText("autobus " + item.getNumbus() + " el " + getDate(item.getTimestamp()));
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();
        return date;
    }

    @Override
    protected void itemAdded(Transaccion item, String key, int position) {
    }

    @Override
    protected void itemChanged(Transaccion oldItem, Transaccion newItem, String key, int position) {
    }

    @Override
    protected void itemRemoved(Transaccion item, String key, int position) {
    }

    @Override
    protected void itemMoved(Transaccion item, String key, int oldPosition, int newPosition) {
    }
}