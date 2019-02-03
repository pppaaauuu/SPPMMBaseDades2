package com.example.alumne.basedades2;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private ImageButton llisting, llistrec, afing, afrec, butcomp, butsug, info, settings;
private Button sortir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llisting = (ImageButton) findViewById(R.id.imageButton12);
        llistrec = (ImageButton) findViewById(R.id.imageButton13);
        afing = (ImageButton) findViewById(R.id.imageButton14);
        afrec = (ImageButton) findViewById(R.id.imageButton15);
        butcomp = (ImageButton) findViewById(R.id.imageButton17);
        butsug = (ImageButton) findViewById(R.id.imageButton16);
        sortir = (Button) findViewById(R.id.button);
        info = findViewById(R.id.imageButton3);
        settings = findViewById(R.id.imageButton4);

        llisting.setOnClickListener(this);
        llistrec.setOnClickListener(this);
        afing.setOnClickListener(this);
        afrec.setOnClickListener(this);
        butcomp.setOnClickListener(this);
        butsug.setOnClickListener(this);
        sortir.setOnClickListener(this);
        settings.setOnClickListener(this);
        info.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v == llisting){
            Intent inten = new Intent(this, LlistaIngredients.class);
            startActivity(inten);
        }else if(v == llistrec){
            Intent inten = new Intent(this, LlistaReceptes.class);
            startActivity(inten);
        }else if(v == afing){
            Intent inten = new Intent(this, AfegirIngredient.class);
            startActivity(inten);
        }else if(v == afrec){
            Intent inten = new Intent(this, AfegirRecepta.class);
            startActivity(inten);
        }else if(v == butcomp) {
            Intent inten = new Intent(this, MenuCompra.class);
            startActivity(inten);
        }else if(v == butsug) {
            suggerirRecepta();
        }else if(v == sortir){
            this.finish();
        }else if(v == info){
            Intent inten = new Intent(this, Info.class);
            startActivity(inten);
        }else if(v == settings){
            Intent inten = new Intent(this, Settings.class);
            startActivity(inten);
        }
    }

    public void suggerirRecepta(){
        Intent inten = new Intent(this, AfegirRecepta.class);
        ArrayList<Recepta> recs = new ArrayList<>();
        DataSourceRebost db = new DataSourceRebost (this);
        try{
            db.open();
            recs = db.getAllRec();
            db.close();
        }catch(SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        for(int i = 0; i < recs.size(); i++){
            ArrayList<Ingredient> ings = recs.get(i).getIngredients();
            for(int j = 0; j < ings.size(); j++){
                if(!ings.get(j).isQueda()){
                    recs.remove(i);
                }
            }
        }
        if(recs != null){
            Random random = new Random();
            int index = random.nextInt(recs.size());
            long id = recs.get(index).getId();
            inten.putExtra("recepta", id);
            startActivity(inten);
        }
    }
}
