package com.apple.java.myproject;

import com.apple.java.myproject.exception.*;

import static com.apple.java.myproject.utils.FileJobber.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public Recomendations (int rating, String textRecomendation, int averageSumm, Object user)
    throws InputTextException { // создание рекомендации с опр. параметрами
        this.user = (Users)user;
        try {
            this.setRating(rating);
        }
        catch(RatingException except){
            System.out.println(except.getMessage());
        }
        this.setTextRecomendation(textRecomendation);
        this.setAverageSumm(averageSumm);
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

    public void setRating(int rating) throws RatingException { // изменить рейтинг
        if(rating < 1 || rating > 5) {
            throw new RatingException("Значение рейтинга некорректно");
        }
        else {
            this.rating = rating;
        }
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
        String returnText = "establishmentID - " + this.id + ";" + "userID - " + this.user.getId() + ";" + "Text - " +
                this.textRecomendation + ";" + "rating - " + this.rating + ";" + "summ - " + this.averageSumm + ";\n";
        return returnText;
    }
    public static ArrayList<Recomendations> createRecomendationsFromBase (String fileText, ArrayList<Users> users)
                                                throws LoadBaseException, UsersNotFoundException, InputTextException {
        //создание листа с рекомендациями, с данными из файла
        String [] textFromFile = ReadFile(fileText).split("\n"); // записываем в массив стринг каждую строку из файла
        ArrayList<Recomendations> returnList = new ArrayList<Recomendations>();
        if(textFromFile[0].length() < 1) // проверяем наличие данных каких-либо в файле
            throw new LoadBaseException("Ошибка загрузки данных. Файл пуст");
        else {
            for (String text : textFromFile) {
                if(text.lastIndexOf("establishmentID") == -1) // проверяем, хранятся ли в данном файле данные
                    // именно для класса Recomendations
                    throw new LoadBaseException("Ошибка загрузки данных. Не найдено уникального ключа");
                else {
                    Map<String, String> thisRow = createFromBase(text); // формируем Map  с ключами из файла
                    Users thisUser = null;
                    for(int i = 0; i < users.size(); i++)
                        if(users.get(i).getId().equals(new Integer(thisRow.get("userID"))))
                            thisUser = users.get(i);

                    if(thisUser == null) // проверяем, есть ли среди объектов Users, объект с ID одного из объектов из файла
                        throw new UsersNotFoundException("Пользователя с таким ID для рекомендаций не найден");
                    else{
                        Recomendations thisRecomendation = new Recomendations(new Integer(thisRow.get("rating")),
                                thisRow.get("Text"), new Integer(thisRow.get("summ")), thisUser); // создаем объект
                        returnList.add(thisRecomendation); // добавляем к возвращаемой коллекции
                    }
                }
            }
        }
        return returnList;
    }
}
