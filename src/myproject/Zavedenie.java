package com.apple.java.myproject;
import java.util.*;

class Establishments {
    private Integer id;
    private String name = "None";
    private Float reiting = new Float(0);
    private Float averageCena = new Float(0);
    public Establishments(){

    }
    public Establishments(int id){
        this.id = id;
    }
    public Integer getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Float getAverageCena() {
        return this.averageCena;
    }

    public void setAverageCena(int averageCena) {
        this.averageCena = (this.getAverageCena() + averageCena)/2;
    }

    public Float getReiting() {
        return this.reiting;
    }

    public void setReiting(int reiting) {
        this.reiting = (reiting + this.getReiting())/2;
    }
}