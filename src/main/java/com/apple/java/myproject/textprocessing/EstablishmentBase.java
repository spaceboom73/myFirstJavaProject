package com.apple.java.myproject.textprocessing;

import com.apple.java.myproject.Establishments;
import com.apple.java.myproject.exception.LoadBaseException;

import java.util.ArrayList;
import java.util.Map;

import static com.apple.java.myproject.utils.constants.EstablishmentConstants.*;
import static com.apple.java.myproject.utils.FileJobber.ReadFile;
import static com.apple.java.myproject.utils.FileJobber.createFromBase;

public class EstablishmentBase {
    public EstablishmentBase(){

    }
    public static ArrayList<Establishments> loadEstablishmentList (String fileText) throws LoadBaseException {
        //метод для обработки строки, полученной из файла establishment.txt, возвращает объекты заведений, хранящихся в файле
        String [] textFromFile = ReadFile(fileText).split("\n"); // записываем в массив string все строки
        ArrayList<Establishments> returnList = new ArrayList<Establishments>();
        if(textFromFile[0].length() < 1) // проверяем наличие каких-либо данных в файле
            throw new LoadBaseException("Ошибка загрузки данных. Файл пуст");
        else {
            for (String text : textFromFile) {
                try {
                    Map<String, String> thisRow = createFromBase(text); // создаем Map разбитый по ключу
                    if (thisRow.containsKey(idBase) && thisRow.containsKey(nameBase) && thisRow.containsKey(averageRatingBase)
                            && thisRow.containsKey(averageSummBase)) {
                        Establishments thisEstablishment = new Establishments(thisRow); // отправляем данные в конструктор c Map
                        returnList.add(thisEstablishment); // добавляем заведение к коллекцию, которую будем возвращать
                    }
                    else throw new LoadBaseException("Ошибка загрузки. Не найдено уникальных ключей");
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
