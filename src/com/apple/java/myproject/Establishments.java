package com.apple.java.myproject;
import java.util.*;

public class Establishments { // класс заведений
    private Integer id; // ID заведения
    private String name = "None"; // название заведения
    private List<Integer> averageRating = new ArrayList<Integer>();
    private List<Integer> averageSumm = new ArrayList<Integer>();
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

    public Float getAverageSumm() {
        Float allSumm = new Float(0.0);
        Integer chislo = 0;
        for(Integer summ : this.averageSumm){
            allSumm += summ.floatValue();
            chislo++;
        }
        return allSumm/chislo.floatValue();
    } //получить стоимость среднего чека

    public void setAverageSumm(int averageSumm) {
        this.averageSumm.add(averageSumm);
    }// изменит средний чек с учетом параметра, который пришёл от класса рекомендаций

    public Float getReiting() {
        Float averageReting = new Float(0.0);
        Integer chislo = 0;
        for(Integer rating : this.averageRating)
        {
            averageReting += rating.floatValue();
            chislo++;
        }
        return averageReting/chislo.floatValue();
    } // получить рейтинг

    public void setReiting(int reiting) {
        this.averageRating.add(reiting);
    }// изменить средний рейтинг с учетом параметра из класса рекомендаций
}