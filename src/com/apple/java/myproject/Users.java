package com.apple.java.myproject;
import com.apple.java.myproject.exception.LoadBaseException;

import java.util.*;
import static com.apple.java.myproject.utils.FileJobber.*;

public class Users {
    private Integer id = 0; // ID пользователя
    private Integer summRatings = 0; // количество оставленных отзывов/рекомендаций
    public String registrationDate; // дата регистрации
    private String userName; // Имя пользователя
    public enum rangUser{
        Newcomer, // новичок
        Userman, // постоянный пользователь
        Empirical, // опытный
        Hardmad // мастер
    }; // звание пользователя
    private rangUser rangUserSelection;
    public Users(){ // конструктор создание пользователя
    }
    public Users(Map<String,String> baseInformation){ // конструктор для создания объекта с информации из БД
        this.setId(new Integer(baseInformation.get("ID")));
        this.setUserName(baseInformation.get("userName"));
        //this.registrationDate = (Date)baseInformation.get("registrationDate");
        this.summRatings = new Integer(baseInformation.get("summRatings"));
        String rangUserBase = baseInformation.get("rangUser");
        if(rangUserBase.equals("Newcomer"))
            this.setRangUser(rangUser.Newcomer);
        if(rangUserBase.equals("Userman"))
            this.setRangUser(rangUser.Userman);
        if(rangUserBase.equals("Empirical"))
            this.setRangUser(rangUser.Empirical);
        if(rangUserBase.equals("Hardmad"))
            this.setRangUser(rangUser.Hardmad);
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
    public String toFile(){ // формирование String для записи в файл
        String returnText = "ID - " + this.id + ";" + "userName - " + this.userName + ";" + "summRatings - "
                + this.summRatings + ";" + "registrationDate - " + this.registrationDate +
                ";" + "rangUser - " + this.rangUserSelection + ";" + "\n";
        return returnText;
    }
    public void getInfo(){ // Вывести информацию о пользователе
        System.out.println("Информация о пользователе: ");
        System.out.println("1. ID - " + this.getId());
        System.out.println("2. userName - " + this.getUserName());
        System.out.println("3. registrationDate - " + this.registrationDate.toString());
        System.out.print("4. ");
        this.getRangUser();
    }
    public static ArrayList<Users> createUsersFromBase (String fileText) throws LoadBaseException{ // создание коллекции
        // пользователей из базы
        String [] textFromFile = ReadFile(fileText).split("\n"); // записываем строки из файла в массив String
        ArrayList<Users> returnList = new ArrayList<Users>();
        if(textFromFile[0].length() < 1) // проверяем на пустоту
            throw new LoadBaseException("Ошибка загрузки данных. Файл пуст");
        else {
            for (String text : textFromFile) {
                if(text.lastIndexOf("userName") == -1) // Проверяем, записан ли пользователь в файле
                    throw new LoadBaseException("Ошибка загрузки данных. Не найдено уникального ключа");
                else {
                    Map<String, String> thisRow = createFromBase(text); // читаем строку из файла в Map
                    Users thisUser = new Users(thisRow); // создаем объект пользователя в конструкторе с Map
                    returnList.add(thisUser); // записываем пользователя в возвращаемую коллекцию
                }
            }
        }
        return returnList;
    }
}
