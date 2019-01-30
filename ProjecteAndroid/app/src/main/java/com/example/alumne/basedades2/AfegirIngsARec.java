package com.example.alumne.basedades2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

public class AfegirIngsARec extends AppCompatActivity {
    private ListView lv;
    private ArrayList<Integer> index;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_ings_arec);
        lv = (ListView) findViewById(R.id.listView4);
        Bundle extras = getIntent().getExtras();
        index = new ArrayList<Integer>();
        if (extras != null) {
            index = extras.getIntegerArrayList("ingredients");
        }
        mostraIngRec();
    }

    private void mostraIngRec(){
        DataSourceRebost dataSource = new DataSourceRebost(this);
        try {
            dataSource.open();
            ingredients = dataSource.getAllIng();
            ArrayAdapter<Ingredient> adap = new IngsRecArrayAdapter(this, R.layout.recepta_a_llista, ingredients, index);
            lv.setAdapter(adap);
            dataSource.close();
        }catch(SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };
}
