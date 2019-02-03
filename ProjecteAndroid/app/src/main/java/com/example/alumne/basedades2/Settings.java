package com.example.alumne.basedades2;

import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.sql.SQLException;

public class Settings extends AppCompatActivity implements View.OnClickListener{
    ImageButton deletedb, importdb, exportdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        deletedb = findViewById(R.id.imageButton9);
        importdb = findViewById(R.id.imageButton10);
        exportdb = findViewById(R.id.imageButton11);
        deletedb.setOnClickListener(this);
        importdb.setOnClickListener(this);
        exportdb.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v == deletedb){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            esborrarBD();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:

                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Estàs segur de voler borrar la Base de Dades sencera??").setPositiveButton("Si", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();

        }
    }

    private void esborrarBD(){
        DataSourceRebost db = new DataSourceRebost(this);
        try{
            db.open();
            db.deleteDb();
            db.close();
        }catch (SQLException e){
            Toast.makeText(this, "No s'ha actualitzat ingredient", Toast.LENGTH_LONG).show();
        }
    }
}
