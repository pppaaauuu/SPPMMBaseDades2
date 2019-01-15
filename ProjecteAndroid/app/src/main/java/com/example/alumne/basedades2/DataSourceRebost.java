package com.example.alumne.basedades2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataSourceRebost {

    private SQLiteDatabase database;
    private RebostHelper dbAjuda;
    private String[] allColumnsIng = {RebostHelper.COLUMN_IDINGREDIENT, RebostHelper.COLUMN_NOMINGREDIENT, RebostHelper.COLUMN_BASICINGREDIENT, RebostHelper.COLUMN_QUEDAINGREDIENT, RebostHelper.COLUMN_COMPRAINGREDIENT};

    public DataSourceRebost(Context context) { //CONSTRUCTOR
        dbAjuda = new RebostHelper(context);
    }
    public void open() throws SQLException {
        database = dbAjuda.getWritableDatabase();
    }
    public void close() {
        dbAjuda.close();
    }
    //PAG4 DEL PDF DE PERSISTENCIA

    public Ingredient createIng(Ingredient ing){
        ContentValues values = new ContentValues();
        values.put(RebostHelper.COLUMN_NOMINGREDIENT, ing.getNom());
        values.put(RebostHelper.COLUMN_BASICINGREDIENT, ing.isBasic());
        values.put(RebostHelper.COLUMN_QUEDAINGREDIENT, ing.isQueda());
        values.put(RebostHelper.COLUMN_COMPRAINGREDIENT, ing.isCompra());
        long insertId = database.insert(RebostHelper.TABLE_INGREDIENTS,null,values);
        ing.setId(insertId);
        return ing;
    }

    public boolean updateIng(Ingredient ing){
        ContentValues values = new ContentValues();
        long id = ing.getId();
        values.put(RebostHelper.COLUMN_NOMINGREDIENT, ing.getNom());
        values.put(RebostHelper.COLUMN_BASICINGREDIENT, ing.isBasic());
        values.put(RebostHelper.COLUMN_QUEDAINGREDIENT, ing.isQueda());
        values.put(RebostHelper.COLUMN_COMPRAINGREDIENT, ing.isCompra());
        return database.update(RebostHelper.TABLE_INGREDIENTS, values,RebostHelper.COLUMN_IDINGREDIENT + "=" + id,null) > 0;
    }

    public void deleteIng(Ingredient ing){
        long id = ing.getId();
        database.delete(RebostHelper.TABLE_INGREDIENTS, RebostHelper.COLUMN_IDINGREDIENT + "=" + id, null );
    }

    public Ingredient getIng(long id){
        Ingredient ing;
        Cursor cursor = database.query(RebostHelper.TABLE_INGREDIENTS, allColumnsIng, RebostHelper.COLUMN_IDINGREDIENT + '=' + id, null, null, null, null );
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            ing = cursorToIng(cursor);
        }else{
            ing = new Ingredient();
        }
        cursor.close();
        return ing;
    }

    public List<Ingredient> getAllIng(){
        List<Ingredient> ings = new ArrayList<Ingredient>();
        Cursor cursor = database.query(RebostHelper.TABLE_INGREDIENTS,allColumnsIng, null, null, null, null, RebostHelper.COLUMN_NOMINGREDIENT + "DESC");
        cursor.moveToFirst();
        while(cursor.isAfterLast()){
            Ingredient ing = cursorToIng(cursor);
            ings.add(ing);
            cursor.moveToNext();
        }
        cursor.close();
        return ings;
    }

    private Ingredient cursorToIng(Cursor cursor){
        Ingredient ing = new Ingredient();
        ing.setId(cursor.getLong(0));
        ing.setNom(cursor.getString(1));
        if(cursor.getInt(2) == 0) ing.setBasic(false);
        else ing.setBasic(true);
        if(cursor.getInt(3) == 0) ing.setQueda(false);
        else ing.setQueda(true);
        if(cursor.getInt(4) == 0) ing.setCompra(false);
        else ing.setCompra(true);
        return ing;
    }

    public Recepta createRec(Recepta rec){
        ContentValues values = new ContentValues();
        values.put(RebostHelper.COLUMN_NOMRECEPTA, rec.getNom());
        values.put(RebostHelper.COLUMN_TEXTRECEPTA, rec.getNom());
    }


}
