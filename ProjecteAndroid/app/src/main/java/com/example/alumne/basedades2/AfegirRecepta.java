package com.example.alumne.basedades2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AfegirRecepta extends AppCompatActivity implements View.OnClickListener {
    Button afegirings ;
    ImageButton guardar, delete;
    EditText ings, textrec, nom;
    Recepta rec;
    private final int REQUEST_CODE = 3;
    ArrayList<Ingredient> ingredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_recepta);
        guardar = (ImageButton) findViewById(R.id.imageButton6);
        delete = (ImageButton) findViewById(R.id.imageButton5);
        afegirings = (Button) findViewById(R.id.button13);
        ings = (EditText) findViewById(R.id.editText4);
        textrec = (EditText) findViewById(R.id.editText5);
        nom = (EditText) findViewById(R.id.editText2);
        ings.setFocusable(false);
        guardar.setOnClickListener(this);
        delete.setOnClickListener(this);
        afegirings.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            long id = extras.getLong("recepta");
            DataSourceRebost db = new DataSourceRebost(this);
            try{
                db.open();
                rec = db.getRec(id);
                db.close();
                nom.setText(rec.getNom());
                textrec.setText(rec.getText());
                ingredients = rec.getIngredients();
                ings.setText("");
                for (int i = 0; i < ingredients.size(); i++) {
                   ings.setText(ings.getText() + ingredients.get(i).getNom() + ", ");
                }
            }catch(SQLException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }


    public void onClick(View v) {
        if (v == guardar) {
            if(rec == null) {
                rec = new Recepta(nom.getText().toString());

                rec.setText(textrec.getText().toString());
                rec.setIngredients(ingredients);
                DataSourceRebost db = new DataSourceRebost(this);
                try {
                    db.open();
                    rec = db.createRec(rec);
                    db.close();
                } catch (SQLException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                if (rec.getId() > 0) {
                    finish();
                }
            }else{
                rec.setText(textrec.getText().toString());
                rec.setIngredients(ingredients);
                DataSourceRebost db = new DataSourceRebost(this);
                try {
                    db.open();
                    boolean i = db.updateRec(rec);
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
        } else if (v == afegirings) {
            Intent inte = new Intent(this, AfegirIngsARec.class);
            if(ingredients != null && ingredients.size() > 0) {
                long[] ingsids = new long[ingredients.size()];

                for (int i = 0; i < ingredients.size(); i++) {
                    ingsids[i] = (long) ingredients.get(i).getId();
                }
                inte.putExtra("ingredients", ingsids);
            }
            startActivityForResult(inte, REQUEST_CODE);
        }else if(v == delete){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            esborrar();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:

                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Estàs segur de voler borrar la recepta?").setPositiveButton("Si", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            long[] ingsid1 = data.getExtras().getLongArray("ingredients");
            ArrayList<Long> ingsid = new ArrayList<>();
            for( int i = 0; i < ingsid1.length; i++){
                ingsid.add(ingsid1[i]);
            }
            DataSourceRebost db = new DataSourceRebost(this);
            ingredients.clear();
            try {
                db.open();
                ings.setText("");
                for (int i = 0; i < ingsid.size(); i++) {
                    ingredients.add(db.getIng(ingsid.get(i)));
                    ings.setText(ings.getText() + ingredients.get(i).getNom() + ", ");
                }
                db.close();
            } catch (SQLException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }

    public void esborrar(){
        DataSourceRebost db = new DataSourceRebost(this);
        try{
            db.open();
            db.deleteRec(rec);
            db.close();
            finish();
        }catch(SQLException e){
            Toast.makeText(this, "No s'ha pogut esborrar. Error a la BD", Toast.LENGTH_LONG).show();
        }
    }
}
