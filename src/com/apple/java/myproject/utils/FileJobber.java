package com.apple.java.myproject.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileJobber {
    public static void WriteFile(String fileName, String content, boolean append){ // метод для записи данных в файл
        FileWriter outputStream = null; // инициализируем поток записи

        try {
            outputStream = new FileWriter(fileName, append);
            for (char symbol : content.toCharArray()) {
                outputStream.write((int) symbol); // начинаем посимвольную запись в файл
            }
            outputStream.flush(); // подгружаем блок в файл, чтобы не потерять данные
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if(outputStream != null){
                try{
                    outputStream.close(); // закрываем файл, если он был открыт
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }
    public static String ReadFile(String fileName){ // метод чтения из файла
        FileReader inputStream = null; // инициализируем поток чтения из файла
        Scanner scan = null;
        StringBuilder result = new StringBuilder("");

        try {
            inputStream = new FileReader(fileName);
            scan = new Scanner(inputStream);
            int symbol;
            while((symbol = inputStream.read()) != -1){
                result.append((char)symbol); // посимвольно записываем результат
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            try {
                inputStream.close(); //  закрываем файл
                scan.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return result.toString(); // переводим StringBuilder в String и возвращаем результат
    }
    public static Map<String,String> createFromBase(String text){ // метод для преобразования строки в Map
        String [] dateFromBase = text.split(";"); // разбиваем по ; на массив String
        Map<String, String> informationFromDate = new HashMap<String, String>();
        for(String dates:dateFromBase) {
            try{
                String[] date = dates.split(" - "); // разбиваем каждый из массива на две части ключ-значение
                if(date.length > 1)
                    informationFromDate.put(date[0], date[1]); // записываем в Map соответственно, ключ-значение
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            //
        }
        return informationFromDate; // возвращаем Map
    }
}
