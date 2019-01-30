package com.example.alumne.basedades2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

public class LlistaReceptes extends AppCompatActivity {
    private ListView lv;
    private ArrayList<Recepta> llistareceptes = new ArrayList<Recepta>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_receptes);
        lv = (ListView) findViewById(R.id.listView2);
        mostraRecs();
    }

    public void mostraRecs() {
        DataSourceRebost dataSource = new DataSourceRebost(this);
        try {
            dataSource.open();
            llistareceptes = dataSource.getAllRec();
            ArrayAdapter<Recepta> adap = new ReceptaArrayAdapter(this, R.layout.recepta_a_llista, llistareceptes);
            lv.setAdapter(adap);
            dataSource.close();
        }catch(SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mostraRecs();
    }
}
