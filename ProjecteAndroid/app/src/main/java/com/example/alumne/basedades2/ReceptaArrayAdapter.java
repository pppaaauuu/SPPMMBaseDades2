package com.example.alumne.basedades2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class ReceptaArrayAdapter  extends ArrayAdapter<Recepta> {
    private Context context;
    private List<Recepta> receptes;
    Recepta rec;
    TextView nom, falten;
    ImageView totok;

    public ReceptaArrayAdapter(Context context, int resource, ArrayList<Recepta> objects) {
        super(context, resource, objects);
        this.context = context;
        this.receptes = objects;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {

        rec = receptes.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view =  inflater.inflate(R.layout.recepta_a_llista, null);
        nom = (TextView) view.findViewById(R.id.textView6);
        falten = (TextView) view.findViewById(R.id.textView7);
        totok = (ImageView) view.findViewById(R.id.imageView4);
        nom.setText(rec.getNom());
        String faltaing = "";
        ArrayList<Ingredient> ings = rec.getIngredients();
        for(int i = 0; i < ings.size(); i++){
            if(!ings.get(i).isQueda()) faltaing +=  ings.get(i).getNom() + ", ";
        }
        falten.setText(faltaing);
        falten.setTextColor(Color.RED);
        if(faltaing.length() >0){
            totok.setVisibility(View.INVISIBLE);
        }else{
            totok.setVisibility(View.VISIBLE);
        }view.setOnClickListener(
                new android.widget.TextView.OnClickListener() {
                    @Override
                    public void onClick (View v) {
                        recClick(position);
                    }}
        );
        return view;
    }

    public void recClick(int pos){
        Intent inten = new Intent(context, AfegirRecepta.class);
        long id = receptes.get(pos).getId();
        inten.putExtra("recepta", id);
        context.startActivity(inten);
    }
}
