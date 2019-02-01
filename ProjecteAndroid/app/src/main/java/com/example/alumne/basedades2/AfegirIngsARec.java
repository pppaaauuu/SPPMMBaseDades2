package com.example.alumne.basedades2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AfegirIngsARec extends AppCompatActivity implements View.OnClickListener {
    private List<Long> indexos = new ArrayList<>();
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private Spinner spin;
    private Button afegir, guardar;
    private ImageButton afegiring;
    private EditText llista;
    private int REQUEST_CODE = 45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_ings_arec);
        afegir      = findViewById(R.id.button16);
        guardar     = findViewById(R.id.button15);
        afegiring   = findViewById(R.id.imageButton);
        llista      = findViewById(R.id.editText3);
        spin        = findViewById(R.id.spinner);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            long[] i1 = extras.getLongArray("ingredients");
            for(int i = 0; i < i1.length; i++){
                indexos.add(i1[i]);
            }
        }
        mostraIngRec();
        carregaSpinner();
        afegir.setOnClickListener(this);
        afegiring.setOnClickListener(this);
        guardar.setOnClickListener(this);
    }

    private void mostraIngRec(){
        DataSourceRebost dataSource = new DataSourceRebost(this);
        llista.setText("");
        try {
            dataSource.open();
            for(int i = 0; i < indexos.size(); i++){
                llista.setText(llista.getText() + dataSource.getIng(indexos.get(i)).getNom() + ", ");
            }
            ingredients = dataSource.getAllIng();
            dataSource.close();
        }catch(SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    private void carregaSpinner(){
        ArrayList<String> noms = new ArrayList<>();
        for(int i = 0; i < ingredients.size(); i++){
            noms.add(ingredients.get(i).getNom());

        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item , noms);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);
    }

    public void onClick(View v){
        if(v == afegir){
            int ind = spin.getSelectedItemPosition();
            long id = ingredients.get(ind).getId();
            if(indexos.contains(id)){
                indexos.remove(id);
            }else{
                indexos.add(id);
            }
            mostraIngRec();
        }else if(v == guardar){
            Intent data = new Intent();
            long[] longarr = new long[indexos.size()];
            for(int i = 0; i < indexos.size(); i++){
                longarr[i] = indexos.get(i);
            }
            data.putExtra("ingredients", longarr);
            setResult(Activity.RESULT_OK, data);
            super.finish();
        }else if(v == afegiring){
            Intent inten = new Intent(this, AfegirIngredient.class);
            startActivityForResult(inten, REQUEST_CODE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if(data.hasExtra("id")) {
                long id = data.getExtras().getLong("id");
                indexos.add(id);
            }
            mostraIngRec();
            carregaSpinner();
        }
    }
}
