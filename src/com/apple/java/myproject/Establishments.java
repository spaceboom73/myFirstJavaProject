package com.apple.java.myproject;
import java.util.*;

class Establishments { // класс заведений
    private Integer id; // ID заведения
    private String name = "None"; // название заведения
    private Float reiting = new Float(0); // средний рейтинг заведения, взятый от всех рекомендаций
    private Float averageCena = new Float(0); // средний чек, взятый от всех рекомендаций
    public Establishments(){ // конструкто
    }
    public Establishments(int id){
        this.id = id;
    } // конструктор с ID
    public Establishments(int id, String name){ // конструктор с ID и названием заведения
        this.id = id;
        this.name = name;
    }
    public Integer getId(){
        return this.id;
    } // получить ID заведения
    public String getName(){
        return this.name;
    } // получить название завеления
    public void setName(String name) {
        this.name = name;
    } // изменить название заведения

    public Float getAverageCena() {
        return this.averageCena;
    } //получить стоимость среднего чека

    public void setAverageCena(int averageCena) {
        this.averageCena = (this.getAverageCena() + averageCena)/2;
    }// изменит средний чек с учетом параметра, который пришёл от класса рекомендаций

    public Float getReiting() {
        return this.reiting;
    } // получить рейтинг

    public void setReiting(int reiting) {
        this.reiting = (reiting + this.getReiting())/2;
    }// изменить средний рейтинг с учетом параметра из класса рекомендаций
}