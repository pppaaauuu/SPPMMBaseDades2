package com.example.alumne.basedades2;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MenuIngredients extends AppCompatActivity implements View.OnClickListener{
    private Button butllistaing, butafegiring, buttornar;
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ingredients);
        butllistaing = (Button) findViewById(R.id.button5);
        butafegiring = (Button) findViewById(R.id.button6);
        buttornar = (Button) findViewById(R.id.button7);
        butllistaing.setOnClickListener(this);
        butafegiring.setOnClickListener(this);
        buttornar.setOnClickListener(this);

    }

    public void onClick(View v){
        if(v == butllistaing){
            Intent inten = new Intent(this, LlistaIngredients.class);
            startActivity(inten);
        }else if(v == butafegiring){
            Intent inten = new Intent(this, AfegirIngredient.class);
            startActivityForResult(inten, REQUEST_CODE);
        }else if(v == buttornar){
            Intent inten = new Intent(this, MainActivity.class);
            startActivity(inten);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
                String g1 = data.getExtras().getString("nom");
                String g2 = "Ingredient " + g1 + " introduït a BD";
                Toast.makeText(this, g2, Toast.LENGTH_LONG).show();
        }
    }


}
