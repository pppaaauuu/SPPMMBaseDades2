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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class CapturaImatge extends AppCompatActivity {
    private Bitmap bitmap;
    private Uri fotoUriCamera;
    private MenuItem opcioGuardaFoto;
    static final int IMATGE_DESDE_CAMERA = 1;
    static final int IMATGE_DESDE_GALERIA = 2;
    static final int AMPLADA_IMATGE = 1080;
    private DataSourceRebost db;
    private int codi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_foto);
        // Agafam id vi
        Intent in = getIntent();
        codi=in.getIntExtra("ID",-1);
        if(codi!=-1) {
            // Agafam imatge ja guardada
            Bitmap bm = ImatgeRecepta.agafaImatgeVi(codi);
            if (bm != null) {
                ImageView imageView = (ImageView) findViewById(R.id.imatgeFoto);
                imageView.setImageBitmap(bm);
            }
        }
        iniciBotons();
    }



    private void iniciBotons() {
        Button tiraFotoBt = (Button) findViewById(R.id.btCamera);
        tiraFotoBt.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     // Es crea l’intent per l’aplicació de fotos
                     Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

                     // Es crea un nou fitxer a l’emmagatzematge extern i se li passa a l’intent
                     File foto = new File(Environment.getExternalStorageDirectory(), "Foto.jpg");
                     intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));
                     // Es guarda l’identificador de la imatge per recuperar-la quan estigui feta
                     fotoUriCamera = Uri.fromFile(foto);
                     // Obrim l’activitat
                     startActivityForResult(intent, IMATGE_DESDE_CAMERA);
                 }
            });
        Button tiraFotoBtGaleria = (Button) findViewById(R.id.btGaleria);
        tiraFotoBtGaleria.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String strAvatarPrompt = "Agafa una imatge dede la galeria";
                Intent pickPhoto = new Intent(Intent.ACTION_PICK);
                pickPhoto.setType("image/*");
                startActivityForResult(Intent.createChooser(pickPhoto, strAvatarPrompt),IMATGE_DESDE_GALERIA);
            }
        });
        Button guardaFoto = (Button) findViewById(R.id.btGuradaFoto);
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
        if(requestCode == IMATGE_DESDE_CAMERA&&resultCode == Activity.RESULT_OK){
            /* El ContentResolver dóna accés als continguts (la imatge emmagatzemada en aquest cas)*/
            ContentResolver contRes=getContentResolver();
            // Cal indicar que el contingut del fitxer ha canviat
            contRes.notifyChange(fotoUriCamera,null);
             bitmap=carregaFoto(contRes,fotoUriCamera);
            if(bitmap==null){
                Toast.makeText(this,"No es pot carregar la imatge desde la càmera", Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode==IMATGE_DESDE_GALERIA&&resultCode==Activity.RESULT_OK){
            Uri fotoUriGaleria=data.getData();
            if(fotoUriGaleria!=null){
                ContentResolver contRes=getContentResolver();
                bitmap=carregaFoto(contRes,fotoUriGaleria);
                if(bitmap!=null){
                    opcioGuardaFoto.setVisible(true);
                }else{
                    Toast.makeText(this,"No es pot carregar la imatge desde la galeria",Toast.LENGTH_SHORT).show();
                }
            }
        }
 }

 private Bitmap carregaFoto(ContentResolver contRes, Uri fotoUri) {
    /* Com que la càrrega de la imatge pot fallar, cal tractar  les possibles excepcions*/
       try {
            /* Accedeix a l’ImageView per carregar la foto */
        ImageView imageView = (ImageView) findViewById(R.id.imatgeFoto);
        Bitmap bitmap = android.provider.MediaStore.Images.Media.getBitmap(contRes, fotoUri);
        /* Reduïm la imatge per no tenir problemes de visualització.Calculem l’alçada per mantenir la  proporció amb una amplada de AMPLADA_IMATGE píxels*/
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


}
