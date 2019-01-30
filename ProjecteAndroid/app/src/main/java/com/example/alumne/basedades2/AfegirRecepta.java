package com.example.alumne.basedades2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;
import java.util.ArrayList;

public class AfegirRecepta extends AppCompatActivity implements View.OnClickListener {
    Button guardar, afegirings;
    EditText ings, textrec, nom;
    private final int REQUEST_CODE = 3;
    ArrayList<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_recepta);
        guardar = (Button) findViewById(R.id.button14);
        afegirings = (Button) findViewById(R.id.button13);
        ings = (EditText) findViewById(R.id.editText4);
        textrec = (EditText) findViewById(R.id.editText5);
        nom = (EditText) findViewById(R.id.editText2);
        ings.setFocusable(false);
        guardar.setOnClickListener(this);
        afegirings.setOnClickListener(this);
        if(nom.isFocused()){
            nom.selectAll();
        }
    }


    public void onClick(View v) {
        if (v == guardar) {
            Recepta rec = new Recepta(nom.getText().toString());
            rec.setText(textrec.getText().toString());
            rec.setIngredients(ingredients);
            DataSourceRebost db = new DataSourceRebost(this);
            try {
                db.open();
                rec = db.createRec(rec);
                db.close();
            } catch (SQLException e) {
            }
            if(rec.getId() >0){
                finish();
            }
        } else if (v == afegirings) {
            Intent inte = new Intent(this, AfegirIngsARec.class);
            ArrayList<Integer> ingsids = new ArrayList<>();
            for(int i = 0; i < ingredients.size(); i++){
                ingsids.add(new Integer((int)ingredients.get(i).getId()));
            }
            inte.putExtra("ingredients", ingsids);
            startActivityForResult(inte, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            ArrayList<Integer> ingsid = data.getExtras().getIntegerArrayList("ingredients");
            DataSourceRebost db = new DataSourceRebost(this);
            ingredients.clear();
            try {
                db.open();
                for (int i = 0; i < ingsid.size(); i++) {
                    ingredients.add(db.getIng(ingsid.get(i)));
                    ings.setText(ings.getText() + ", " + ingredients.get(i).getNom());
                }
                db.close();
            } catch (SQLException e) {
            }

        }
    }
}
