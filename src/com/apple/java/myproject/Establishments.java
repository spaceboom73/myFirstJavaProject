package com.apple.java.myproject;

import com.apple.java.myproject.utils.enums.ratingSelection;

import java.util.*;

import static com.apple.java.myproject.utils.constants.EstablishmentConstants.*;
public class Establishments { // класс заведений
    private Integer id; // ID заведения
    private String name = "None"; // название заведения
    private List<Integer> ratingList = new ArrayList<Integer>();
    private List<Integer> summList = new ArrayList<Integer>();
    public Establishments(){ // конструкто
    }
    public Establishments(int id){
        this.id = id;
    } // конструктор с ID
    public Establishments(int id, String name){ // конструктор с ID и названием заведения
        this.id = id;
        this.name = name;
    }
    public Establishments(Map<String,String> baseInformation){ // конструктор создания заведения с информацией из БД по ключам
        this.id = new Integer(baseInformation.get(idBase)); // идентификатор
        this.setName(baseInformation.get(nameBase)); // название заведения
        String averageSummFromBase = baseInformation.get(averageSummBase);
        if(!averageSummFromBase.equals("None")){
            String averageSummEvery [] = averageSummFromBase.split(",");
            for(String summ:averageSummEvery) {
                if(summ.length() > 0)
                    this.setAverageSumm(new Integer(summ)); //запись всех чеков для вычисления среднего
            }
        }
        String averageRatingFromBase = baseInformation.get(averageRatingBase);
        if(!averageRatingFromBase.equals("None")){
            String averageRatingEvery [] = averageRatingFromBase.split(",");
            for(String rating:averageRatingEvery) {
                if (rating.length() > 0)
                    this.ratingList.add(new Integer(rating)); // запись всех рейтингов для вычисления среднего
            }
        }
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

    public String getAverageSumm() { // вычисление среднего чека в заведении
        Float allSumm = new Float(0.0);
        Integer chislo = 0;
        for(Integer summ : this.summList){
            allSumm += summ.floatValue();
            chislo++;
        }
        if(chislo == 0){
            return "нет ни одного указания стоимости";
        }
        return "" + allSumm/chislo.floatValue();
    } //получить стоимость среднего чека

    public void setAverageSumm(int averageSumm) { // добавление среднего чека в коллекцию чеков
        this.summList.add(averageSumm);
    }// изменит средний чек с учетом параметра, который пришёл от класса рекомендаций

    public String getReiting() { // вычисление средних рейтингов заведения
        Float averageReting = new Float(0.0);
        Integer chislo = 0;
        for(Integer rating : this.ratingList)
        {
            averageReting += rating.floatValue();
            chislo++;
        }
        float returnValue;
        if(chislo == 0)
        {
            return "нет ни одного рейтинга";
        }
        else return "" + averageReting/chislo.floatValue();
    } // получить рейтинг

    public void setReiting(ratingSelection rating) { // добавление рейтинга к коллекции рейтингов заведения
        this.ratingList.add(rating.getAssessment());
    }// изменить средний рейтинг с учетом параметра из класса рекомендаций
    public String toFile(){ // сформировать String для записи в файл
        String returnText = idBase + " - " + this.id + ";" + nameBase + " - " + this.name + ";" +
        averageRatingBase +  " - ";
        if(this.ratingList.size() == 0)
            returnText += "None";
        else {
            for (Integer rating : this.ratingList)
                returnText += (rating + ",");
        }
        returnText += ";" + averageSummBase + " - ";
        if(this.summList.size() == 0)
            returnText += "None";
        else {
            for (Integer summ : this.summList)
                returnText += (summ + ",");
        }
        returnText += ";\n";
        return returnText;
    }
    public void printInfo() { // метод возвращающий всю информацию о заведении
        System.out.println("Информация о заведении:");
        System.out.println("1. Название заведения: " + this.getName());
        System.out.println("2. Средняя оценка: " + this.getReiting());
        System.out.println("3. Средняя цена в заведении: " + this.getAverageSumm());
    }
}