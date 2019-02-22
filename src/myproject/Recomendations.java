package com.apple.java.myproject;

public class Recomendations {
    private Integer id;
    private Users userId;
    private String textRecomendation = "None";
    private Integer rating;
    private Integer averageCena;

    public Recomendations(Object id){
        this.userId = (Users)id;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        if(rating > 0 && rating <= 5) {
            this.rating = rating;
            /*
            Establishments establishment = new Establishments(this.id);
            establishment.setReiting(this.rating);*/
        }
        else
            System.out.println("Ошибка: оценка заведения не может быть менее 1 и более 5");
    }

    public Users getUserId() {
        return this.userId;
    }
    public String getTextRecomendation() {
        return this.textRecomendation;
    }
    public void setTextRecomendation(String textRecomendation){
        if(textRecomendation.length() > 500)
            System.out.println("Вы ввели слишком большой отзыв о заведении, сократите текст до 500 символов");
        else if(textRecomendation.length() < 60)
            System.out.println("Вы написали слишком мало, минимальный объем текста - 60 символов");
        else
            this.textRecomendation = textRecomendation;
    }

    public void setAverageCena(int averageCena) {
        this.averageCena = averageCena;
        /*Establishments establishment = new Establishments(this.id);
        establishment.setAverageCena(this.averageCena);*/
    }

    public Integer getAverageCena() {
        return this.averageCena;
    }
}
