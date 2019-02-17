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
    private String[] allColumnsIng = {RebostHelper.COLUMN_IDINGREDIENT, RebostHelper.COLUMN_NOMINGREDIENT, RebostHelper.COLUMN_BASICINGREDIENT, RebostHelper.COLUMN_QUEDAINGREDIENT, RebostHelper.COLUMN_COMPRAINGREDIENT, RebostHelper.COLUMN_CAT1INGREDIENT, RebostHelper.COLUMN_CAT2INGREDIENT};
    private String[] allColumnsRec = {RebostHelper.COLUMN_IDRECEPTA, RebostHelper.COLUMN_NOMRECEPTA, RebostHelper.COLUMN_TEXTRECEPTA};
    private String[] allColumnsRecIng = {RebostHelper.COLUMN_RECEPTA, RebostHelper.COLUMN_INGREDIENT};
    private String[] allColumnsCatIng = {RebostHelper.COLUMN_IDCATEGORIA, RebostHelper.COLUMN_NOMCATEGORIA};


    public DataSourceRebost(Context context) { //CONSTRUCTOR
        dbAjuda = new RebostHelper(context);
    }

    public void open() throws SQLException {
        database = dbAjuda.getWritableDatabase();
    }
   
    public void close() {
        dbAjuda.close();
    }

    public Ingredient createIng(Ingredient ing){
        ContentValues values = new ContentValues();
        values.put(RebostHelper.COLUMN_NOMINGREDIENT, ing.getNom());
        values.put(RebostHelper.COLUMN_BASICINGREDIENT, ing.isBasic());
        values.put(RebostHelper.COLUMN_QUEDAINGREDIENT, ing.isQueda());
        values.put(RebostHelper.COLUMN_COMPRAINGREDIENT, ing.isCompra());
        values.put(RebostHelper.COLUMN_CAT1INGREDIENT, ing.getCat1());
        values.put(RebostHelper.COLUMN_CAT2INGREDIENT, ing.getCat2());
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
        values.put(RebostHelper.COLUMN_CAT1INGREDIENT, ing.getCat1());
        values.put(RebostHelper.COLUMN_CAT2INGREDIENT, ing.getCat2());
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

    public ArrayList<Ingredient> getAllIng(){
        ArrayList<Ingredient> ings = new ArrayList<Ingredient>();
        Cursor cursor = database.query(RebostHelper.TABLE_INGREDIENTS, allColumnsIng, null, null, null, null, RebostHelper.COLUMN_NOMINGREDIENT + " ASC");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Ingredient ing = getIng(cursor.getLong(0));
            ings.add(ing);
            cursor.moveToNext();
        }
        cursor.close();
        return ings;
    }

    private Ingredient cursorToIng(Cursor cursor){
        Ingredient ing = new Ingredient();
        cursor.moveToPosition(0);
        ing.setId(cursor.getLong(0));
        ing.setNom(cursor.getString(1));
        if(cursor.getInt(2) == 0) ing.setBasic(false);
        else ing.setBasic(true);
        if(cursor.getInt(3) == 0) ing.setQueda(false);
        else ing.setQueda(true);
        if(cursor.getInt(4) == 0) ing.setCompra(false);
        else ing.setCompra(true);
        ing.setCat1(cursor.getInt(5));
        ing.setCat2(cursor.getInt(6));
        return ing;
    }

    public Recepta createCat(Categoria cat){
        ContentValues values = new ContentValues();
        values.put(RebostHelper.COLUMN_NOMCATEGORIA, cat.getNom());
        int insertId = database.insert(RebostHelper.TABLE_CATEGORIA,null,values);
        cat.setId(insertId);
        return cat;
    }

    public boolean updateCat(Categoria cat){
        ContentValues values = new ContentValues();
        long id = cat.getId();
        values.put(RebostHelper.COLUMN_NOMCATEGORIA, cat.getNom());
        return database.update(RebostHelper.TABLE_CATEGORIA, values,RebostHelper.COLUMN_IDCATEGORIA + "=" + id,null) > 0;
    }

	public void deleteCat(Categoria cat){
        long id = cat.getId();
        database.delete(RebostHelper.TABLE_CATEGORIA, RebostHelper.COLUMN_IDCATEGORIA + "=" + id, null );       
    }

    public Recepta createRec(Recepta rec){
        ContentValues values = new ContentValues();
        values.put(RebostHelper.COLUMN_NOMRECEPTA, rec.getNom());
        values.put(RebostHelper.COLUMN_TEXTRECEPTA, rec.getText());
        long insertId = database.insert(RebostHelper.TABLE_RECEPTA,null,values);
        rec.setId(insertId);
        List<Ingredient> ings = rec.getIngredients();
        if(ings != null && ings.size() > 0) {
            for (int i = 0; i < ings.size(); i++) {
                ContentValues values2 = new ContentValues();
                values2.put(RebostHelper.COLUMN_INGREDIENT, ings.get(i).getId());
                values2.put(RebostHelper.COLUMN_RECEPTA, rec.getId());
                database.insert(RebostHelper.TABLE_RECPING, null, values2);
            }
        }

        return rec;
    }

    public boolean updateRec(Recepta rec){
        ContentValues values = new ContentValues();
        long id = rec.getId();
        values.put(RebostHelper.COLUMN_NOMRECEPTA, rec.getNom());
        values.put(RebostHelper.COLUMN_TEXTRECEPTA, rec.getText());
        database.delete(RebostHelper.TABLE_RECPING, RebostHelper.COLUMN_RECEPTA + "=" + id, null );
        List<Ingredient> ings = rec.getIngredients();
        for(int i = 0; i < ings.size(); i++){
            ContentValues values2 = new ContentValues();
            values2.put(RebostHelper.COLUMN_INGREDIENT, ings.get(i).getId());
            values2.put(RebostHelper.COLUMN_RECEPTA, rec.getId());
            database.insert(RebostHelper.TABLE_RECPING, null, values2);
        }
        return database.update(RebostHelper.TABLE_RECEPTA, values,RebostHelper.COLUMN_IDRECEPTA + "=" + id,null) > 0;
    }

    public void deleteRec(Recepta rec){
        long id = rec.getId();
        database.delete(RebostHelper.TABLE_RECPING, RebostHelper.COLUMN_RECEPTA + "=" + id, null );
        database.delete(RebostHelper.TABLE_RECEPTA, RebostHelper.COLUMN_IDRECEPTA + "=" + id, null );
    }

    public Recepta getRec(long id){
        Recepta rec;
        ArrayList<Ingredient> ings = new ArrayList<>();
        Cursor cursor = database.query(RebostHelper.TABLE_RECEPTA, allColumnsRec, RebostHelper.COLUMN_IDRECEPTA + '=' + id, null, null, null, null );
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            rec = cursorToRec(cursor);
        }else{
            rec = new Recepta();
        }
        cursor.close();
        rec.setIngredients(getIngsRec(id));
        return rec;
    }

    public ArrayList<Recepta> getAllRec(){
        ArrayList<Recepta> recs = new ArrayList<Recepta>();
        Cursor cursor = database.query(RebostHelper.TABLE_RECEPTA, allColumnsRec, null, null, null, null, RebostHelper.COLUMN_NOMRECEPTA + " ASC");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Recepta rec = cursorToRec(cursor);
            recs.add(rec);
            cursor.moveToNext();
        }
        cursor.close();
        return recs;
    }

    private Recepta cursorToRec(Cursor cursor){
        Recepta rec = new Recepta();
        rec.setId(cursor.getLong(0));
        rec.setNom(cursor.getString(1));
        rec.setText(cursor.getString(2));
        rec.setIngredients(getIngsRec(rec.getId()));
        return rec;
    }

    private ArrayList<Ingredient> getIngsRec(Long recid){
        ArrayList<Ingredient> ings = new ArrayList<Ingredient>();
        String[] colings = {RebostHelper.COLUMN_INGREDIENT};
        Cursor cursor = database.query(RebostHelper.TABLE_RECPING, colings, RebostHelper.COLUMN_RECEPTA + '=' + recid, null, null, null, RebostHelper.COLUMN_INGREDIENT + " ASC");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Ingredient ing = getIng(cursor.getLong(0));
            ings.add(ing);
            cursor.moveToNext();
        }
        cursor.close();
        return ings;
    }

    public ArrayList<Ingredient> getLlistaCompra(){
        ArrayList<Ingredient> ings = new ArrayList<Ingredient>();
        Cursor cursor = database.query(RebostHelper.TABLE_INGREDIENTS, allColumnsIng, RebostHelper.COLUMN_COMPRAINGREDIENT + "=1", null, null, null, RebostHelper.COLUMN_NOMINGREDIENT + " ASC");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Ingredient ing = getIng(cursor.getLong(0));
            ings.add(ing);
            cursor.moveToNext();
        }
        cursor.close();
        return ings;
    }

    public void deleteDb(){
        database.execSQL("delete from "+ RebostHelper.TABLE_RECEPTA);
        database.execSQL("delete from "+ RebostHelper.TABLE_INGREDIENTS);
        database.execSQL("delete from "+ RebostHelper.TABLE_RECPING);
        database.execSQL("delete from "+ RebostHelper.TABLE_CATING);
    }

}
