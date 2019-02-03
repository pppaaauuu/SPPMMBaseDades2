package com.example.alumne.basedades2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

public class MenuCompra extends AppCompatActivity {
    private ListView lv;
    private ArrayList<Ingredient> llistaingredients = new ArrayList<Ingredient>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_ingredients);
        lv = (ListView) findViewById(R.id.listView);
        mostraIngs();

    }

    public void mostraIngs() {
        DataSourceRebost dataSource = new DataSourceRebost(this);
        try {
            dataSource.open();
            llistaingredients = dataSource.getLlistaCompra();
            ArrayAdapter<Ingredient> adap = new IngredientArrayAdapter(this, R.layout.ingredient_a_llista, llistaingredients);
            lv.setAdapter(adap);
            dataSource.close();
        }catch(SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mostraIngs();
    }
}
