package com.apple.java.myproject;
import java.util.Date;

public class Users {
    private Integer id;
    private Integer summRatings;
    private String registrationDate;
    private String userName;
    private enum rangUser{
        Newcomer, // новичок
        Userman, // постоянный пользователь
        Empirical, // опытный
        Hardmad // мастер
    };
    private rangUser rangUserSelection;

    public Integer getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationDate() {
        return this.registrationDate;
    }

    public void RegistrationDate() {
        Date date = new Date();
        this.registrationDate = date.toString();
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSummRatings() {
        return this.summRatings;
    }

    public void summRatingsPlus() {
        this.summRatings = getSummRatings()+1;
        if(this.summRatings > 5 && this.summRatings <= 10)
            setRangUser(rangUser.Newcomer);
        if(this.summRatings > 10 && this.summRatings <= 50)
            setRangUser(rangUser.Userman);
        if(this.summRatings > 50 && this.summRatings <= 100)
            setRangUser(rangUser.Empirical);
        if(this.summRatings > 100)
            setRangUser(rangUser.Hardmad);
    }
    private void setRangUser(rangUser selection){
        this.rangUserSelection = selection;
    }
    public void getRangUser(rangUser selection){
        if(selection == rangUser.Newcomer)
            System.out.println("Этот пользователь новичок");
        if(selection == rangUser.Userman)
            System.out.println("Этот пользователь бывалый");
        if(selection == rangUser.Empirical)
            System.out.println("Этот пользователь опытный");
        if(selection == rangUser.Hardmad)
            System.out.println("Этот пользователь мастер");

    }
    public Recomendations createRecomendation(int rating, String textRecomendation, int averageCena){
        Recomendations recomendation = new Recomendations(this);
        recomendation.setRating(rating);
        recomendation.setTextRecomendation(textRecomendation);
        recomendation.setAverageCena(averageCena);
        return recomendation;
    }
}
