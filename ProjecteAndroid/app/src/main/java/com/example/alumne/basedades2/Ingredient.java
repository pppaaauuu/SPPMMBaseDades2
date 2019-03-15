package com.example.alumne.basedades2;

public class Ingredient {
    private long id;
    private String nom;
    private boolean queda;
    private boolean compra;
    private boolean basic;
    private long cat1;
    private long cat2;



    public Ingredient(String nom1){
        this.nom = nom1;
        this.queda = true;
        this.basic = false;
        this.compra = false;
    }

    public Ingredient(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isQueda() {
        return queda;
    }

    public void setQueda(boolean queda) {
        if (isBasic() && queda == false){
            setCompra(true);
        }
        this.queda = queda;
    }

    public boolean isCompra() {
        return compra;
    }

    public void setCompra(boolean compra) {

        this.compra = compra;
    }
    public void canviaCompra(boolean compra){
        if(compra == false && !isQueda()){
            setQueda(true);
        }
        this.compra = compra;
    }

    public boolean isBasic() {
        return basic;
    }

    public void setBasic(boolean basic) {
        this.basic = basic;
    }


    public long getCat1() {
        return cat1;
    }

    public void setCat1(long cat1) {
        this.cat1 = cat1;
    }

    public long getCat2() {
        return cat2;
    }

    public void setCat2(long cat2) {
        this.cat2 = cat2;
    }
}
