package com.example.alumne.basedades2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientArrayAdapter extends ArrayAdapter<Ingredient>{
    private Context context;
    private List<Ingredient> ingredients;
    Ingredient ing;
    TextView nom;
    ImageView bas;
    ImageView queda;
    ImageView compra;
    View view;
    //constructor
    public IngredientArrayAdapter(Context context, int resource, ArrayList<Ingredient> objects) {
        super(context, resource, objects);
        this.context = context;
        this.ingredients = objects;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {

        ing = ingredients.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.ingredient_a_llista, null);
        nom = (TextView) view.findViewById(R.id.textView);
        bas = (ImageView) view.findViewById(R.id.imageView);
        queda = (ImageView) view.findViewById(R.id.imageView2);
        compra = (ImageView) view.findViewById(R.id.imageView3);
        nom.setText(ing.getNom());
        if(ing.isBasic()){
            bas.setImageResource(android.R.drawable.presence_online);
        }else{
            bas.setImageResource(android.R.drawable.presence_busy);
        }
        if(ing.isQueda()){
            queda.setImageResource(android.R.drawable.presence_online);
        }else{
            queda.setImageResource(android.R.drawable.presence_busy);
        }
        if(ing.isCompra()){
            compra.setImageResource(android.R.drawable.presence_online);
        }else{
            compra.setImageResource(android.R.drawable.presence_busy);
        }
        bas.setOnClickListener(new android.widget.TextView.OnClickListener() {
            @Override
            public void onClick (View v) {
                basicClick(position);
            }});
        queda.setOnClickListener(new android.widget.TextView.OnClickListener() {
          @Override
          public void onClick (View v) {
              quedaClick(position);
            }});
        compra.setOnClickListener(new android.widget.TextView.OnClickListener() {
            @Override
            public void onClick (View v) {
                compraClick(position);
            }});
        return view;
    }


    public void basicClick(int pos){
        ing = ingredients.get(pos);
        if (ing.isBasic()) {
            ing.setBasic(false);
        } else {
            ing.setBasic(true);
        }
        DataSourceRebost dsr = new DataSourceRebost(context);
        boolean upd = false;
        try {
            dsr.open();
            upd = dsr.updateIng(ing);
            dsr.close();
        } catch (SQLException e) {}
        if (!upd) {
            Toast.makeText(context, "No s'ha actualitzat ingredient", Toast.LENGTH_LONG).show();
        }
        this.notifyDataSetChanged();
    }

    public void quedaClick(int pos){
        ing = ingredients.get(pos);
        if (ing.isQueda()) {
            ing.setQueda(false);
        } else {
            ing.setQueda(true);
        }
        DataSourceRebost dsr = new DataSourceRebost(context);
        boolean upd = false;
        try {
            dsr.open();
            upd = dsr.updateIng(ing);
            dsr.close();
        } catch (SQLException e) {}
        if (!upd) {
            Toast.makeText(context, "No s'ha actualitzat ingredient", Toast.LENGTH_LONG).show();
        }
        this.notifyDataSetChanged();
    }

    public void compraClick(int pos){
        ing = ingredients.get(pos);
        if (ing.isCompra()) {
            ing.setCompra(false);
        } else {
            ing.setQueda(true);
        }
        DataSourceRebost dsr = new DataSourceRebost(context);
        boolean upd = false;
        try {
            dsr.open();
            upd = dsr.updateIng(ing);
            dsr.close();
        } catch (SQLException e) {}
        if (!upd) {
            Toast.makeText(context, "No s'ha actualitzat ingredient", Toast.LENGTH_LONG).show();
        }
        this.notifyDataSetChanged();
    }


}
