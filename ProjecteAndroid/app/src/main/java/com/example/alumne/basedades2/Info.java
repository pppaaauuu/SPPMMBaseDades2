package com.example.alumne.basedades2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Info extends AppCompatActivity {
    ImageButton back, eat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        back = findViewById(R.id.imageButton8);
        eat = findViewById(R.id.imageButton19);
        back.setOnClickListener(new android.widget.TextView.OnClickListener() {
            @Override
            public void onClick (View v) {
                finish();
            }});

        eat.setOnClickListener(new android.widget.TextView.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent inten = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http:\\\\www.errorandtrial.com"));

                startActivity(inten);
            }});
    }
}
