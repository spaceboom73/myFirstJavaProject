package myproject;
import java.util.Date;

public class Users {
    private int id;
    private int summRatings;
    private String registrationDate;
    private String userName;
    private String rangUser;

    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRangUser() {
        return this.rangUser;
    }

    public void setRangUser(String rangUser) {
        this.rangUser = rangUser;
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

    public int getSummRatings() {
        return this.summRatings;
    }

    public void summRatingsPlus() {
        this.summRatings = getSummRatings()+1;
        if(this.summRatings > 5 && this.summRatings <= 10)
            setRangUser("Новичок");
        if(this.summRatings > 10 && this.summRatings <= 50)
            setRangUser("Бывалый");
        if(this.summRatings > 50 && this.summRatings <= 100)
            setRangUser("Опытный");
        if(this.summRatings > 100)
            setRangUser("Мастер");
    }
    public Recomendations createRecomendation(int rating, String textRecomendation, int averageCena){
        Recomendations recomendations = new Recomendations(this.id);
        recomendations.setRating(rating);
        recomendations.setTextRecomendation(textRecomendation);
        recomendations.setAverageCena(averageCena);
        return recomendations;
    }
}
