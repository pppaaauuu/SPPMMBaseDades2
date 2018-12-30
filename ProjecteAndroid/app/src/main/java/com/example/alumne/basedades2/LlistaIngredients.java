package com.example.alumne.basedades2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LlistaIngredients extends AppCompatActivity {

    private ArrayList<Ingredient> llistaingredients = new ArrayList<Ingredient>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_ingredients);
        ListView lv = (ListView) findViewById(R.id.listView);
//Aquí s'ha de cridar la BD per carregar l'arraylist.
        ArrayAdapter<Ingredient> adap = new IngredientArrayAdapter(this, R.layout.ingredient_a_llista, llistaingredients);

        lv.setAdapter(adap);

    }
}
