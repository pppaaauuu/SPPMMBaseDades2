package com.example.alumne.basedades2;

public class Categoria {
    private long id;
    private String nom;
    

    public Categoria(String nom1){
        this.nom = nom1;
    }

    public Categoria(){

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

}
