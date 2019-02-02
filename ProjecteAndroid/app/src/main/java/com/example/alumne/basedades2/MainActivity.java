package com.example.alumne.basedades2;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private Button buting, butrec, butcomp, butsort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buting = (Button) findViewById(R.id.button);
        butrec = (Button) findViewById(R.id.button2);
        butcomp = (Button) findViewById(R.id.button3);
        butsort = (Button) findViewById(R.id.button4);

        buting.setOnClickListener(this);
        butrec.setOnClickListener(this);
        butcomp.setOnClickListener(this);
        butsort.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v == buting){
            Intent inten = new Intent(this, MenuIngredients.class);
            startActivity(inten);
        }else if(v == butrec){
            Intent inten = new Intent(this, MenuReceptes.class);
            startActivity(inten);
        }else if(v == butcomp){
            Intent inten = new Intent(this, MenuCompra.class);
            startActivity(inten);
        }else if(v == butsort){
            this.finish();
        }
    }
}
