package com.example.alumne.basedades2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

public class ImatgeRecepta {
    public static final int AMPLADA_IMATGE = 1080;
    public static boolean guardarImatge(Bitmap bitmap, String filename) {
        boolean ok = false;
        File sdcard = Environment.getExternalStorageDirectory() ;
        File folder = new File(sdcard.getAbsoluteFile(), RebostHelper.DIRECTORIFOTOS);
        //Si posam un punt davant el nom del directori fa que aquest sigui ocult per l'usuari
        //Si volem fer servir la memòria interna
        //  File folder =new File(getFilesDir(),RebostHelper.DIRECTORIFOTOS);
        folder.mkdir();
        File file = new File(folder.getAbsoluteFile(), filename) ;
        Log.i("Imatge","path: " + file.getAbsoluteFile());
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
        }return ok;
    }

    public static File agafaImage(String nomimatge) {
        File mediaImage = null;
        try {
            String root = Environment.getExternalStorageDirectory().toString();
            File meuDir = new File(root);
            if (!meuDir.exists())return null;
            mediaImage = new File(meuDir.getPath() + "/" + RebostHelper.DIRECTORIFOTOS + "/" + nomimatge);
        } catch (Exception e) {
            e.printStackTrace();
        }return mediaImage;
    }

    public static boolean checkifImatgeExisteix(String imagename){
        Bitmap b = null ;
        File file = ImatgeRecepta.agafaImage(imagename);
        String path = file.getAbsolutePath();
        Log.i("ImatgeMagatzem","Path :"+path);
        if (path != null) {
            b = BitmapFactory.decodeFile(path);
        }if(b == null ||  b.equals("")) {
            return false ;
        }
        return true;
    }
    public static Bitmap agafaImatgeVi(long id) {
        String nomimatge="img_"+id+".jpg";
        Bitmap res=null;
        Bitmap reduit=null;
        if (checkifImatgeExisteix(nomimatge)){
            File file = ImatgeRecepta.agafaImage(nomimatge);
            String path = file.getAbsolutePath();
            res = BitmapFactory.decodeFile(path);
            int alt = (int) (res.getHeight() * AMPLADA_IMATGE/ res.getWidth());
            reduit = Bitmap.createScaledBitmap(res, AMPLADA_IMATGE, alt, true);
        }return  reduit;
    }
}

