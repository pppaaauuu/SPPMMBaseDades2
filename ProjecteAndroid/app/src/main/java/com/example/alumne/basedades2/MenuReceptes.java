package com.example.alumne.basedades2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


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
            Intent inten = new Intent(this, SuggerirRecepta.class);
            startActivity(inten);
        }else if(v == butsortir){
           finish();
        }
    }
}
