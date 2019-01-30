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

public class IngsRecArrayAdapter extends ArrayAdapter<Ingredient>

    {
        private Context context;
        private List<Ingredient> ingredients;
        ArrayList<Integer> index;
        Ingredient ing;
        TextView nom;
        ImageView te;

        View view;
        //constructor
    public IngsRecArrayAdapter(Context context, int resource, ArrayList<Ingredient> objects, ArrayList<Integer> index1) {
        super(context, resource, objects);
        this.context = context;
        this.ingredients = objects;
        this.index = index1;
    }

        public View getView(final int position, View convertView, final ViewGroup parent) {

        ing = ingredients.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.ingredient_a_llista, null);
        nom = (TextView) view.findViewById(R.id.textView);
        view.findViewById(R.id.imageView).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.textView2).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.textView3).setVisibility(View.INVISIBLE);
        TextView text = (TextView)view.findViewById(R.id.textView5);
        text.setText("Inclòs?");
        te = (ImageView) view.findViewById(R.id.imageView3);
        nom.setText(ing.getNom());
        Integer intid = new Integer((int)ing.getId());
        if(index.contains(intid)){
            te.setImageResource(android.R.drawable.presence_online);
        }else{
            te.setImageResource(android.R.drawable.presence_busy);
        }

        te.setOnClickListener(new android.widget.TextView.OnClickListener() {
            @Override
            public void onClick (View v) {
                teClick(position);
            }});

        return view;
    }

        public void teClick(int pos){
            ing = ingredients.get(pos);
            Integer intid = new Integer((int)ing.getId());
            if(!index.contains(intid)){
                index.add(intid);
            }else{
                index.remove(intid);
            }

            this.notifyDataSetChanged();
        }
}
