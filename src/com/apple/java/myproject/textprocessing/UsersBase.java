package com.apple.java.myproject.textprocessing;

import com.apple.java.myproject.Users;
import com.apple.java.myproject.exception.LoadBaseException;

import java.util.ArrayList;
import java.util.Map;

import static com.apple.java.myproject.utils.FileJobber.ReadFile;
import static com.apple.java.myproject.utils.FileJobber.createFromBase;
import static com.apple.java.myproject.utils.constants.UsersConstants.*;

public class UsersBase{
    public UsersBase(){

    }
    public ArrayList<Users> loadUsersList(String fileText) throws LoadBaseException{
        String [] textFromFile = ReadFile(fileText).split("\n"); // записываем строки из файла в массив String
        ArrayList<Users> returnList = new ArrayList<Users>();
        if(textFromFile[0].length() < 1) // проверяем на пустоту
            throw new LoadBaseException("Ошибка загрузки данных. Файл пуст");
        else {
            for (String text : textFromFile) {
                try{
                    Map<String, String> thisRow = createFromBase(text); // читаем строку из файла в Map
                    if(thisRow.containsKey(idBase) && thisRow.containsKey(userNameBase) && thisRow.containsKey(summRatingsBase)
                    && thisRow.containsKey(registrationDateBase) && thisRow.containsKey(rangUserBase)) {
                        Users thisUser = new Users(thisRow); // создаем объект пользователя в конструкторе с Map
                        returnList.add(thisUser); // записываем пользователя в возвращаемую коллекцию
                    }
                    else throw new LoadBaseException("Ошибка загрузки данных. Не найдено уникального ключа");
                }
                catch (Exception ex){
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
