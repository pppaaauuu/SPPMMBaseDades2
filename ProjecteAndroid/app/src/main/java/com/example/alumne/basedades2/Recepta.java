package com.example.alumne.basedades2;

import com.example.alumne.basedades2.Ingredient;

import java.util.ArrayList;

public class Recepta {
    private String nom;
    private String text;
    private ArrayList<Ingredient> ingredients;



    public Recepta(){
        this.nom = "BUIDA";
        this.ingredients = new ArrayList<Ingredient>();
    }
    public Recepta(String nom){
        this.nom = nom;
        this.ingredients = new ArrayList<Ingredient>();
    }

    public Recepta(String nom, ArrayList<Ingredient> rece){
        this.nom = nom;
        this.ingredients = rece;
    }

    public boolean teIngredient(Ingredient ing){

        return ingredients.contains(ing);
    }

    public void afegirIngredient(Ingredient ing){
        if(!teIngredient(ing)){
            ingredients.add(ing);

        }else{
            System.out.println("Ja té aquest ingredient");
        }
    }

    public ArrayList<Ingredient> getIngredients(){
        return ingredients;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getNom(){
        return this.nom;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
