package com.apple.java.myproject;

import com.apple.java.myproject.utils.enums.rangUser;

import java.util.*;
import static com.apple.java.myproject.utils.constants.UsersConstants.*;

public class Users{

    private Integer id = 0; // ID пользователя
    private Integer summRatings = 0; // количество оставленных отзывов/рекомендаций
    public String registrationDate; // дата регистрации
    private String userName; // Имя пользователя
    private rangUser rangUserSelection = rangUser.None;
    public Users(){ // конструктор создание пользователя
    }
    public Users(Map<String,String> baseInformation){ // конструктор для создания объекта с информации из БД
        this.setId(new Integer(baseInformation.get(idBase)));
        this.setUserName(baseInformation.get(userNameBase));
        //this.registrationDate = (Date)baseInformation.get("registrationDate");
        this.summRatings = new Integer(baseInformation.get(summRatingsBase));
        this.setRangUser(rangUser.getChoice(baseInformation.get(rangUserBase)));
        this.registrationDate = baseInformation.get("registrationDate");
    }
    public Integer getId(){//получить ID пользователя
        return this.id;
    }

    public void setId(Integer id) { // изменить ID пользователя
        this.id = id;
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
    public void setSummRatings(Integer summ){this.summRatings = summ;}

    public void summRatingsPlus() { // увелечение количества оставленных отзывов
        this.summRatings += 1;

        if(this.summRatings > 5 && this.summRatings <= 10)
            this.setRangUser(rangUser.Newcomer);// изменение звания
        if(this.summRatings > 10 && this.summRatings <= 50)
            this.setRangUser(rangUser.Userman);
        if(this.summRatings > 50 && this.summRatings <= 100)
            this.setRangUser(rangUser.Empirical);
        if(this.summRatings > 100)
            this.setRangUser(rangUser.Hardman);
    }
    public void setRangUser(rangUser selection){ // изменение звания
        this.rangUserSelection = selection;
    }
    public String getRangUser(){ // получить текст с званием пользователя
        return rangUserSelection.getRangText();
    }
    public String toFile(){ // формирование String для записи в файл
        String returnText = idBase + " - " + this.id + ";" + userNameBase + " - " + this.userName + ";" +
                summRatingsBase + " - " + this.summRatings + ";" + registrationDateBase + " - " + this.registrationDate
                + ";" + rangUserBase + " - " + this.getRangUser() + ";" + "\n";
        return returnText;
    }
    public void printInfo(){ // Вывести информацию о пользователе
        System.out.println("Информация о пользователе: ");
        System.out.println("1. ID - " + this.getId());
        System.out.println("2. userName - " + this.getUserName());
        System.out.println("3. registrationDate - " + this.registrationDate.toString());
        System.out.println("4. rangUser - " + this.getRangUser());
    }
}
