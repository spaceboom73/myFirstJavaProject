package com.apple.java.myproject;

import com.apple.java.myproject.exception.RatingException;

public class Recomendations { // класс рекомендаций
    private Integer id; // ID заведения, которому будет присвоена рекомендация
    private Users user; // объект пользователя, который данную рекомендацию создал
    private String textRecomendation = "None"; // текст рекомендации
    private Integer rating; // рейтинг посещения, выставленный пользователем
    private Integer averageSumm; // цена чека, выставленная пользователем

    public Recomendations(){

    }
    public Recomendations(Object id){
        this.user = (Users)id;
    } // конструктор
    public Recomendations (int rating, String textRecomendation, int averageSumm, Object user, Object establishment){ // создание рекомендации с опр. параметрами
        this.user = (Users)user;
        try {
            this.setRating(rating, establishment);
        }
        catch(RatingException except){
            System.out.println(except.getMessage());
        }
        this.setTextRecomendation(textRecomendation);
        this.setAverageSumm(averageSumm, establishment);
    }
    public Integer getId() { // ID заведения
        return this.id;
    }
    public void setId(int id){ // изменить ID заведения
        this.id = id;
    }

    public Integer getRating() { // рейтинг
        return this.rating;
    }

    public void setRating(int rating, Object establishment) throws RatingException { // изменить рейтинг
        if(rating < 1 || rating > 5) {
            throw new RatingException("Значение рейтинга некорректно");
        }
        else {
            this.rating = rating;
            ((Establishments) establishment).setReiting(this.rating);
        }
    }

    public Users getUserId() { // получить класс пользователя, который оставил рекомендацию
        return this.user;
    }
    public String getTextRecomendation() { // получить текст рекомендации
        return this.textRecomendation;
    }
    public void setTextRecomendation(String textRecomendation){ // изменить текст рекомендации
        if(textRecomendation.length() > 500)
            System.out.println("Вы ввели слишком большой отзыв о заведении, сократите текст до 500 символов");
        /*else if(textRecomendation.length() < 60)
            System.out.println("Вы написали слишком мало, минимальный объем текста - 60 символов");*/
        else
            this.textRecomendation = textRecomendation;
    }

    public void setAverageSumm(int averageSumm, Object establishment) { // изменить цену чека за данное посещение
        this.averageSumm = averageSumm;
        ((Establishments)establishment).setAverageSumm(this.averageSumm);
        //нужно дополнить изменение среднего чека новым классом
    }

    public Integer getAverageSumm() { // узнать среднюю цену
        return this.averageSumm;
    }
}
