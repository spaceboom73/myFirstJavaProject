package com.apple.java.myproject;

public class Recomendations { // класс рекомендаций
    private Integer id; // ID заведения, которому будет присвоена рекомендация
    private Users user; // объект пользователя, который данную рекомендацию создал
    private String textRecomendation = "None"; // текст рекомендации
    private Integer rating; // рейтинг посещения, выставленный пользователем
    private Integer averageCena; // цена чека, выставленная пользователем

    public Recomendations(Object id){
        this.user = (Users)id;
    } // конструктор

    public Integer getId() { // ID заведения
        return this.id;
    }
    public void setId(int id){ // изменить ID заведения
        this.id = id;
    }

    public Integer getRating() { // рейтинг
        return this.rating;
    }

    public void setRating(int rating) { // изменить рейтинг
        if(rating > 0 && rating <= 5) {
            this.rating = rating;
            /*
            Establishments establishment = new Establishments(this.id);
            establishment.setReiting(this.rating);*/
        }
        else
            System.out.println("Ошибка: оценка заведения не может быть менее 1 и более 5");
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

    public void setAverageCena(int averageCena) { // изменить цену чека за данное посещение
        this.averageCena = averageCena;
        /*Establishments establishment = new Establishments(this.id);
        establishment.setAverageCena(this.averageCena);*/
    }

    public Integer getAverageCena() { // узнать среднюю цену
        return this.averageCena;
    }
}
