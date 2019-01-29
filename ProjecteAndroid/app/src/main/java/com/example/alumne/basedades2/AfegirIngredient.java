package com.example.alumne.basedades2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

public class AfegirIngredient extends AppCompatActivity implements View.OnClickListener{
Button butafegir;
EditText edit;
TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_ingredient);
        butafegir = findViewById(R.id.button12);
        butafegir.setOnClickListener(this);
        edit = (EditText) findViewById(R.id.editText);
        text = (TextView) findViewById(R.id.textView4);
    }

    public void onClick(View v) {
        if (v == butafegir) {
            DataSourceRebost db = new DataSourceRebost(this);
            String nom = edit.getText().toString();
            Ingredient ing = new Ingredient(nom);
            try {
                db.open();
                ing = db.createIng(ing);
                db.close();
            }catch(SQLException e){

            }
            if(ing.getId() != 0 && ing.getId() != -1){
                text.setText("");
                finish(ing.getNom());
            }else{
                text.setText("No s'ha pogut introduïr");
            }
        }

    }

    public void finish(String s){
        Intent data = new Intent();
        data.putExtra("nom", s);
        setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}
