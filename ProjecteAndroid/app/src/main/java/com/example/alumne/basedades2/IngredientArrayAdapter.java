package com.example.alumne.basedades2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class IngredientArrayAdapter extends ArrayAdapter<Ingredient> {
    private Context context;
    private List<Ingredient> ingredients;
    Ingredient ing;
    TextView nom;
    ImageView bas;
    ImageView queda;
    ImageView compra;
    //constructor
    public IngredientArrayAdapter(Context context, int resource, ArrayList<Ingredient> objects) {
        super(context, resource, objects);
        this.context = context;
        this.ingredients = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ing = ingredients.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ingredient_a_llista, null);
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
                if(ing.isBasic()){
                    ing.setBasic(false);
                    bas.setImageResource(android.R.drawable.presence_busy);
                }else{
                    ing.setBasic(true);
                    bas.setImageResource(android.R.drawable.presence_online);
                }
            }
        });
        queda.setOnClickListener(new android.widget.TextView.OnClickListener() {
          @Override
          public void onClick (View v) {
              if(ing.isQueda()){
                  ing.setQueda(false);
                  queda.setImageResource(android.R.drawable.presence_busy);
              }else{
                  ing.setQueda(true);
                  queda.setImageResource(android.R.drawable.presence_online);
              }
          }
        });
        compra.setOnClickListener(new android.widget.TextView.OnClickListener() {
            @Override
            public void onClick (View v) {
                if(ing.isCompra()){
                    ing.setCompra(false);
                    compra.setImageResource(android.R.drawable.presence_busy);
                }else{
                    ing.setCompra(true);
                    compra.setImageResource(android.R.drawable.presence_online);
                }
            }
        });
        return view;
    }
}
