package com.example.alumne.basedades2;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class CapturaImatge extends AppCompatActivity {
    private Bitmap bitmap;

    private MenuItem opcioGuardaFoto;
    static final int IMATGE_DESDE_CAMERA = 9;
    static final int IMATGE_DESDE_GALERIA = 8;
    static final int AMPLADA_IMATGE = 1080;
    private DataSourceRebost db;
    private long codi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_foto);
        Intent in = getIntent();
        codi=in.getLongExtra("ID",-1);
        if(codi!=-1) {
            Bitmap bm = ImatgeRecepta.agafaImatge(codi);
            if (bm != null) {
                ImageView imageView = (ImageView) findViewById(R.id.imageView5);
                imageView.setImageBitmap(bm);
            }
        }
        iniciBotons();
    }



    private void iniciBotons() {
        ImageButton tiraFotoBt = (ImageButton) findViewById(R.id.imageButton21);
        tiraFotoBt.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

                     File foto2 = new File(Environment.getExternalStorageDirectory(), "Foto.jpg");
                     Uri fotoUriCamera = Uri.fromFile(foto2);
                     intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto2));

                     startActivityForResult(intent, IMATGE_DESDE_CAMERA);
                 }
            });
        ImageButton tiraFotoBtGaleria = (ImageButton) findViewById(R.id.imageButton20);
        tiraFotoBtGaleria.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String strAvatarPrompt = "Agafa una imatge dede la galeria";
                Intent pickPhoto = new Intent(Intent.ACTION_PICK);
                pickPhoto.setType("image/*");
                startActivityForResult(Intent.createChooser(pickPhoto, strAvatarPrompt),IMATGE_DESDE_GALERIA);
            }
        });
        ImageButton guardaFoto = (ImageButton) findViewById(R.id.imageButton22);
        guardaFoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                guardaFoto();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
}

protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.d("DEV:", "ON ACTIVITY RESULT ENTRANT");
        File foto2 = new File(Environment.getExternalStorageDirectory(), "Foto.jpg");
        Uri fotoUriCamera = Uri.fromFile(foto2);
        if(requestCode == IMATGE_DESDE_CAMERA && resultCode == Activity.RESULT_OK){
            Log.d("DEV:", "IMATGE CAMERA ENTRANT");
            ContentResolver contRes = getContentResolver();
            Log.d("DEV:", "CONTENT RESOLVER");
            contRes.notifyChange(fotoUriCamera,null);
            Log.d("DEV:", "NOTIFY CHANGE");
            bitmap = carregaFoto(contRes,fotoUriCamera);
            Log.d("DEV:", "BITMAP CARREGAT");
            if(bitmap == null){
                Toast.makeText(this,"No es pot carregar la imatge desde la càmera", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == IMATGE_DESDE_GALERIA && resultCode == Activity.RESULT_OK){
            Uri fotoUriGaleria = data.getData();
            if(fotoUriGaleria != null){
                ContentResolver contRes = getContentResolver();
                bitmap = carregaFoto(contRes,fotoUriGaleria);
               /* if(bitmap != null){
                    opcioGuardaFoto.setVisible(true);
                }else{
                    Toast.makeText(this,"No es pot carregar la imatge desde la galeria",Toast.LENGTH_SHORT).show();
                }*/
            }
        }
 }

 private Bitmap carregaFoto(ContentResolver contRes, Uri fotoUri) {
    try {
         ImageView imageView = (ImageView) findViewById(R.id.imageView5);
        Bitmap bitmap = android.provider.MediaStore.Images.Media.getBitmap(contRes, fotoUri);
        int alt = (int) (bitmap.getHeight() * AMPLADA_IMATGE/ bitmap.getWidth());
        Bitmap reduit = Bitmap.createScaledBitmap(bitmap, AMPLADA_IMATGE, alt, true);
        imageView.setImageBitmap(reduit);return reduit;
        } catch (Exception e) {
            return null;
        }
}

public void guardaFoto() {
    String nomimatge="img_"+codi+".jpg";
    ImatgeRecepta.guardarImatge(bitmap,nomimatge);
}
}



