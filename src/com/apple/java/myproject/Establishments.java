package com.apple.java.myproject;

import com.apple.java.myproject.exception.DivisionOnNullException;
import com.apple.java.myproject.exception.LoadBaseException;

import java.util.*;
import static com.apple.java.myproject.utils.FileJobber.*;

public class Establishments { // класс заведений
    private Integer id; // ID заведения
    private String name = "None"; // название заведения
    private List<Integer> averageRating = new ArrayList<Integer>();
    private List<Integer> averageSumm = new ArrayList<Integer>();
    public Establishments(){ // конструкто
    }
    public Establishments(int id){
        this.id = id;
    } // конструктор с ID
    public Establishments(int id, String name){ // конструктор с ID и названием заведения
        this.id = id;
        this.name = name;
    }
    public Establishments(Map<String,String> baseInformation){ // конструктор создания заведения с информацией из БД по ключам
        this.id = new Integer(baseInformation.get("ID")); // идентификатор
        this.setName(baseInformation.get("name")); // название заведения
        String averageSummFromBase = baseInformation.get("averageSumm");
        String averageSummEvery [] = averageSummFromBase.split(",");
        for(String summ:averageSummEvery) {
            if(summ.length() > 0)
                this.setAverageSumm(new Integer(summ)); //запись всех чеков для вычисления среднего
        }
        String averageRatingFromBase = baseInformation.get("averageRating");
        String averageRatingEvery [] = averageRatingFromBase.split(",");
        for(String rating:averageRatingEvery) {
            if (rating.length() > 0)
                this.setReiting(new Integer(rating)); // запись всех рейтингов для вычисления среднего
        }
    }
    public Integer getId(){
        return this.id;
    } // получить ID заведения
    public String getName(){
        return this.name;
    } // получить название завеления
    public void setName(String name) {
        this.name = name;
    } // изменить название заведения

    public Float getAverageSumm() throws DivisionOnNullException{ // вычисление среднего чека в заведении
        Float allSumm = new Float(0.0);
        Integer chislo = 0;
        for(Integer summ : this.averageSumm){
            allSumm += summ.floatValue();
            chislo++;
        }
        float returnValue;
        if(chislo == 0)
        {
            returnValue = allSumm/chislo.floatValue()+1;
            throw new DivisionOnNullException("Обнаружено деление на ноль в getAverageSumm()");
        }
        else
            returnValue = allSumm/chislo.floatValue();
        return returnValue;
    } //получить стоимость среднего чека

    public void setAverageSumm(int averageSumm) { // добавление среднего чека в коллекцию чеков
        this.averageSumm.add(averageSumm);
    }// изменит средний чек с учетом параметра, который пришёл от класса рекомендаций

    public Float getReiting() throws DivisionOnNullException { // вычисление средних рейтингов заведения
        Float averageReting = new Float(0.0);
        Integer chislo = 0;
        for(Integer rating : this.averageRating)
        {
            averageReting += rating.floatValue();
            chislo++;
        }
        float returnValue;
        if(chislo == 0)
        {
            returnValue = averageReting/chislo.floatValue()+1;
            throw new DivisionOnNullException("Обнаружено деление на ноль в getReiting()");
        }
        returnValue = averageReting/chislo.floatValue();
        return returnValue;
    } // получить рейтинг

    public void setReiting(int reiting) { // добавление рейтинга к коллекции рейтингов заведения
        this.averageRating.add(reiting);
    }// изменить средний рейтинг с учетом параметра из класса рекомендаций
    public String toFile(){ // сформировать String для записи в файл
        String returnText = "ID - " + this.id + ";" + "name - " + this.name + ";" + "averageRating - ";
        for(Integer rating:  this.averageRating)
            returnText+= (rating + ",");
        returnText += ";averageSumm - ";
        for(Integer summ:this.averageSumm)
            returnText += (summ + ",");
        returnText += ";";
        return returnText;
    }
    public void getInfo() throws DivisionOnNullException { // метод возвращающий всю информацию о заведении
        System.out.println("Информация о заведении:");
        System.out.println("1. Название заведения: " + this.getName());
        System.out.println("2. Средняя оценка: " + this.getReiting());
        System.out.println("3. Средняя цена в заведении: " + this.getAverageSumm());
    }
    public static ArrayList<Establishments> createEstablishmentFromBase (String fileText) throws LoadBaseException {
        //метод для обработки строки, полученной из файла establishment.txt, возвращает объекты заведений, хранящихся в файле
        String [] textFromFile = ReadFile(fileText).split("\n"); // записываем в массив string все строки
        ArrayList<Establishments> returnList = new ArrayList<Establishments>();
        if(textFromFile[0].length() < 1) // проверяем наличие каких-либо данных в файле
            throw new LoadBaseException("Ошибка загрузки данных. Файл пуст");
        else {
            for (String text : textFromFile) {
                if(text.lastIndexOf("averageRating") == -1) //проверяем, записались ли в этот файл данные именно
                    // для класса Establishment
                    throw new LoadBaseException("Ошибка загрузки данных. Не найдено уникального ключа");
                else {
                    Map<String, String> thisRow = createFromBase(text); // создаем Map разбитый по ключу
                    Establishments thisEstablishment = new Establishments(thisRow); // отправляем данные в конструктор c Map
                    returnList.add(thisEstablishment); // добавляем заведение к коллекцию, которую будем возвращать
                }
            }
        }
        return returnList;
    }
}