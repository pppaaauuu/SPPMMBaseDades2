package com.example.alumne.basedades2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class AfegirIngredient extends AppCompatActivity implements View.OnClickListener{
ImageButton butafegir, delete;
EditText edit;
TextView text;
Ingredient ing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_ingredient);
        butafegir = findViewById(R.id.imageButton2);
        delete = findViewById(R.id.imageButton7);
        delete.setOnClickListener(this);
        butafegir.setOnClickListener(this);
        edit = (EditText) findViewById(R.id.editText);
        text = (TextView) findViewById(R.id.textView4);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            long id = extras.getLong("ingredient");
            DataSourceRebost db = new DataSourceRebost(this);
            try{
                db.open();
                ing = db.getIng(id);
                db.close();
                edit.setText(ing.getNom());

            }catch(SQLException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        edit.setActivated(true);
        edit.setSelection(0, edit.getText().length()-1);
    }

    public void onClick(View v) {
        if (v == butafegir) {
            if(ing == null) {
                DataSourceRebost db = new DataSourceRebost(this);

                String nom = edit.getText().toString();
                Ingredient ing = new Ingredient(nom);
                try {
                    db.open();
                    ing = db.createIng(ing);
                    db.close();
                } catch (SQLException e) {

                }
                if (ing.getId() != 0 && ing.getId() != -1) {
                    text.setText("");
                    finish(ing.getNom(), ing.getId());
                } else {
                    text.setText("No s'ha pogut introduïr");
                }
            }else{
                ing.setNom(edit.getText().toString());
                DataSourceRebost db = new DataSourceRebost(this);
                try {
                    db.open();
                    boolean i = db.updateIng(ing);
                    db.close();
                    if(i){
                        finish();
                    }else{
                        Toast.makeText(this, "No s'ha pogut actualitzar. Error a la BD", Toast.LENGTH_LONG).show();
                    }
                } catch (SQLException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }else if(v == delete) {
            if (ing != null) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                esborrar();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Estàs segur de voler borrar l'ingredient?").setPositiveButton("Si", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }else{
                Toast.makeText(this, "No estava a la base de dades", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void finish(String s, long id){
        Intent data = new Intent();
        data.putExtra("nom", s);
        data.putExtra("id", id);
        setResult(Activity.RESULT_OK, data);
        super.finish();
    }

    public void esborrar(){
        DataSourceRebost db = new DataSourceRebost(this);
        try{
            db.open();
            db.deleteIng(ing);
            db.close();
            finish();
        }catch(SQLException e){
            Toast.makeText(this, "No s'ha pogut esborrar. Error a la BD", Toast.LENGTH_LONG).show();
        }
    }
}
