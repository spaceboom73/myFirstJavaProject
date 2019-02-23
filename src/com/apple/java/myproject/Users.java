package com.apple.java.myproject;
import java.util.Date;

public class Users {
    private Integer id = 0; // ID пользователя
    private Integer summRatings = 0; // количество оставленных отзывов/рекомендаций
    private String registrationDate; // дата регистрации
    private String userName; // Имя пользователя
    private enum rangUser{
        Newcomer, // новичок
        Userman, // постоянный пользователь
        Empirical, // опытный
        Hardmad // мастер
    }; // звание пользователя
    private rangUser rangUserSelection;
    public Users(){ // конструктор создание пользователя
    }

    public Integer getId(){//получить ID пользователя
        return this.id;
    }

    public void setId(int id) { // изменить ID пользователя
        this.id = id;
    }

    public String getRegistrationDate() {//получить дату регистрации
        return this.registrationDate;
    }

    public void RegistrationDate() {//инициализировать дату регистрации на сегодняшний день
        Date date = new Date();
        this.registrationDate = date.toString();
    }

    public String getUserName() {//получить имя пользователя
        return this.userName;
    }

    public void setUserName(String userName) {// изменить имя пользователя
        this.userName = userName;
    }

    public Integer getSummRatings() {//получить общее количество оставленных отзывов/рекомендаций
        return this.summRatings;
    }

    public void summRatingsPlus() { // увелечение количества оставленных отзывов
        this.summRatings += 1;

        if(this.summRatings > 5 && this.summRatings <= 10)
            this.setRangUser(rangUser.Newcomer);// изменение звания
        if(this.summRatings > 10 && this.summRatings <= 50)
            this.setRangUser(rangUser.Userman);
        if(this.summRatings > 50 && this.summRatings <= 100)
            this.setRangUser(rangUser.Empirical);
        if(this.summRatings > 100)
            this.setRangUser(rangUser.Hardmad);
    }
    private void setRangUser(rangUser selection){ // изменение звания
        this.rangUserSelection = selection;
    }
    public void getRangUser(){ // получить текст с званием пользователя
        rangUser selection = this.rangUserSelection;
        if(selection == rangUser.Newcomer)
            System.out.println("Этот пользователь новичок");
        if(selection == rangUser.Userman)
            System.out.println("Этот пользователь бывалый");
        if(selection == rangUser.Empirical)
            System.out.println("Этот пользователь опытный");
        if(selection == rangUser.Hardmad)
            System.out.println("Этот пользователь мастер");
    }
    public Recomendations createRecomendation(int rating, String textRecomendation, int averageCena){ // создание рекомендации с опр. параметрами
        Recomendations recomendation = new Recomendations(this);
        recomendation.setRating(rating);
        recomendation.setTextRecomendation(textRecomendation);
        recomendation.setAverageCena(averageCena);
        return recomendation;
    }
}
