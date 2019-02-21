package myproject;
import java.util.*;

class Zavedenie {
    private int id;
    private String name = "None";
    private float reiting = 0;
    private float averageCena = 0;
    public Zavedenie(int id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public float getAverageCena() {
        return this.averageCena;
    }

    public void setAverageCena(int averageCena) {
        this.averageCena = (this.getAverageCena() + averageCena)/2;
    }

    public float getReiting() {
        return this.reiting;
    }

    public void setReiting(int reiting) {
        this.reiting = (reiting + this.getReiting())/2;
    }
}