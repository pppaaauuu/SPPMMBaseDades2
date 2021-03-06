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
        llistrec = (ImageButton) findViewById(R.id.imageButton18);
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
        ArrayList<Recepta> recs2 = new ArrayList<>();
        DataSourceRebost db = new DataSourceRebost (this);
        try{
            db.open();
            recs2 = db.getAllRec();
            db.close();
        }catch(SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        for(int i = 0; i < recs2.size(); i++){
            ArrayList<Ingredient> ings = recs2.get(i).getIngredients();
            for(int j = 0; j < ings.size(); j++){
                if(!ings.get(j).isQueda()){
                    recs2.remove(i);
                    i--;
                    break;
                }
            }
        }
        if(recs2 != null && recs2.size() > 0){
            Random r = new Random();
            int index1 = r.nextInt(recs2.size());
            long id = recs2.get(index1).getId();
            inten.putExtra("recepta", id);
            startActivity(inten);
        }else{
            Toast.makeText(this, "No hi ha ingredients com per suggerir receptes", Toast.LENGTH_LONG).show();
        }
    }
}
