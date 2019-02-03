package com.example.alumne.basedades2;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;


public class MenuReceptes extends AppCompatActivity implements View.OnClickListener{
    private Button butllist, butafegir, butsuggerir, butsortir;
    final int REQUEST_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_receptes);
        butllist    = (Button) findViewById(R.id.button8);
        butafegir   = (Button) findViewById(R.id.button9);
        butsuggerir = (Button) findViewById(R.id.button10);
        butsortir   = (Button) findViewById(R.id.button11);
        butllist.setOnClickListener(this);
        butafegir.setOnClickListener(this);
        butsuggerir.setOnClickListener(this);
        butsortir.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v == butllist){
            Intent inten = new Intent(this, LlistaReceptes.class);
            startActivity(inten);
        }else if(v == butafegir){
            Intent inten = new Intent(this, AfegirRecepta.class);
            startActivityForResult(inten, REQUEST_CODE);
        }else if(v == butsuggerir){
            suggerirRecepta();



        }else if(v == butsortir){
           finish();
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
