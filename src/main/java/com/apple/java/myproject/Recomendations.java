package com.apple.java.myproject;

import com.apple.java.myproject.exception.*;
import com.apple.java.myproject.utils.enums.ratingSelection;

import static com.apple.java.myproject.utils.constants.RecomendationsConstant.*;


public class Recomendations { // класс рекомендаций
    private Integer id; // ID заведения, которому будет присвоена рекомендация
    private Users user; // объект пользователя, который данную рекомендацию создал
    private String textRecomendation = "None"; // текст рекомендации
    private ratingSelection rating; // рейтинг посещения, выставленный пользователем
    private Integer averageSumm; // цена чека, выставленная пользователем

    public Recomendations(){

    }
    public Recomendations(Object id){
        this.user = (Users)id;
    } // конструктор
    public Recomendations (ratingSelection rating, String textRecomendation, int averageSumm, Object user) throws
            InputTextException{ // создание рекомендации с опр. параметрами
        this.user = (Users)user;
        this.setRating(rating);
        this.setTextRecomendation(textRecomendation);
        this.setAverageSumm(averageSumm);
    }
    public Integer getId() { // ID заведения
        return this.id;
    }
    public void setId(int id){ // изменить ID заведения
        this.id = id;
    }

    public String getRating() { // рейтинг
        return this.rating.getDescription();
    }

    public void setRating(ratingSelection rating){ // изменить рейтинг
        this.rating = rating;
    }

    public Users getUser() { // получить класс пользователя, который оставил рекомендацию
        return this.user;
    }
    public String getTextRecomendation() { // получить текст рекомендации
        return this.textRecomendation;
    }
    public void setTextRecomendation(String textRecomendation) throws InputTextException { // изменить текст рекомендации
        if(textRecomendation.length() > 500){
            throw new InputTextException("Количество символов введённого текста не должно превышать 500 символов");
        }

        /*else if(textRecomendation.length() < 60)
            System.out.println("Вы написали слишком мало, минимальный объем текста - 60 символов");*/
        else
            this.textRecomendation = textRecomendation;
    }

    public void setAverageSumm(int averageSumm) { // изменить цену чека за данное посещение
        this.averageSumm = averageSumm;
        //нужно дополнить изменение среднего чека новым классом
    }

    public Integer getAverageSumm() { // узнать среднюю цену
        return this.averageSumm;
    }
    public String toFile(){ // стринг для записи в recomendations.txt
        String returnText = establishmentID + " - " + this.getId() + ";" + userID + " - " + this.user.getId() + ";" +
                textBase + " - " + this.getTextRecomendation() + ";" + ratingBase + " - " + this.rating.toString() + ";" +
                summBase + " - " + this.getAverageSumm() + ";\n";
        return returnText;
    }
    public void printInfo(){
        System.out.println("ID заведения: " + this.getId());
        System.out.println("ID пользователя: " + this.user.getId());
        System.out.println("Text: " + this.getTextRecomendation());
        System.out.println("rating: " + this.getRating());
        System.out.println("summ: " + this.getAverageSumm());
    }
}
