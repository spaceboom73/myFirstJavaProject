package com.apple.java.myproject.textprocessing;

import com.apple.java.myproject.Recomendations;
import com.apple.java.myproject.Users;
import com.apple.java.myproject.exception.LoadBaseException;
import com.apple.java.myproject.exception.UsersNotFoundException;
import com.apple.java.myproject.utils.enums.rangUser;
import com.apple.java.myproject.utils.enums.ratingSelection;

import java.util.ArrayList;
import java.util.Map;

import static com.apple.java.myproject.utils.FileJobber.ReadFile;
import static com.apple.java.myproject.utils.FileJobber.createFromBase;
import static com.apple.java.myproject.utils.constants.RecomendationsConstant.*;

public class RecomendationsBase{
    public RecomendationsBase(){

    }
    public ArrayList<Recomendations> loadRecomendationsList (String fileText, ArrayList<Users> users)
            throws LoadBaseException, UsersNotFoundException {
        //создание листа с рекомендациями, с данными из файла
        String [] textFromFile = ReadFile(fileText).split("\n"); // записываем в массив стринг каждую строку из файла
        ArrayList<Recomendations> returnList = new ArrayList<Recomendations>();
        if(textFromFile[0].length() < 1) // проверяем наличие данных каких-либо в файле
            throw new LoadBaseException("Ошибка загрузки данных. Файл пуст");
        else {
            for (String text : textFromFile) {
                try{
                    Map<String, String> thisRow = createFromBase(text); // формируем Map  с ключами из файла
                    if(thisRow.containsKey(establishmentID) && thisRow.containsKey(userID) && thisRow.containsKey(textBase) &&
                            thisRow.containsKey(ratingBase) && thisRow.containsKey(summBase)) {
                        Users thisUser = null;
                        for (int i = 0; i < users.size(); i++)
                            if (users.get(i).getId().equals(new Integer(thisRow.get("userID"))))
                                thisUser = users.get(i);

                        if (thisUser == null) // проверяем, есть ли среди объектов Users, объект с ID одного из объектов из файла
                            throw new UsersNotFoundException("Пользователя с таким ID для рекомендаций не найден");
                        else {
                            Recomendations thisRecomendation = new Recomendations(ratingSelection.getChoice(new Integer(thisRow.get(ratingBase))),
                                    thisRow.get(textBase), new Integer(thisRow.get(summBase)), thisUser); // создаем объект
                            thisRecomendation.setId(new Integer(thisRow.get(establishmentID)));
                            returnList.add(thisRecomendation); // добавляем к возвращаемой коллекции
                        }
                    }
                    else throw new LoadBaseException("Ошибка загрузки. Не найдено всех уникальных ключей");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Программа была остановлена из за ошибки в загрузке данных из файла.");
                    System.out.println("Обычно это происходит потому что данные в файле указаны не верным образом");
                    System.exit(1);
                }
            }
        }
        return returnList;
    }
}
