package com.apple.java;

import com.apple.java.myproject.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.apple.java.myproject.exception.EstablishmentNotFoundException;
import com.apple.java.myproject.exception.InputTextException;
import com.apple.java.myproject.exception.LoadBaseException;
import com.apple.java.myproject.exception.UsersNotFoundException;


import static com.apple.java.myproject.utils.FileJobber.*;



public class Main {
    public static void main(String[] args) throws LoadBaseException, EstablishmentNotFoundException, UsersNotFoundException,
            InputTextException {

        /*Users [] users = new Users[5];

        users[0] = new Users();
        users[1] = new Users();
        users[2] = new Users();
        users[3] = new Users();
        users[4] = new Users();

        users[0].setId(0);
        users[0].setUserName("Первый пользователь");
        for(int i = 0; i < 100; i++)
            users[0].summRatingsPlus();
        users[0].registrationDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());

        users[1].setId(1);
        users[1].setUserName("Второй пользователь");
        for(int i = 0; i < 100; i++)
            users[1].summRatingsPlus();
        users[1].registrationDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());

        users[2].setId(0);
        users[2].setUserName("Третий пользователь");
        for(int i = 0; i < 100; i++)
            users[2].summRatingsPlus();
        users[2].registrationDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());

        users[3].setId(3);
        users[3].setUserName("Четвертый пользователь");
        for(int i = 0; i < 100; i++)
            users[3].summRatingsPlus();
        users[3].registrationDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());

        users[4].setId(4);
        users[4].setUserName("Пятый пользователь");
        for(int i = 0; i < 100; i++)
            users[4].summRatingsPlus();
        users[4].registrationDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());

        String baseText = users[0].toFile() + users[1].toFile() + users[2].toFile() + users[3].toFile() + users[4].toFile();
        WriteFile("users.txt", baseText, false);*/

        ArrayList <Users> users = Users.createUsersFromBase("users.txt"); // читаем пользователей из файла в коллекцию
        /*for(int i = 0; i < users.size(); i++)
            users.get(i).getInfo();*/

        //читаем заведения из файла в коллекцию
        ArrayList <Establishments> establishments = Establishments.createEstablishmentFromBase("establishment.txt");

        try {
            for(int i = 0; i < establishments.size(); i++)
                establishments.get(i).getInfo();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        /*Recomendations recomendation = new Recomendations(5, "Круто", 500, users.get(2));
        recomendation.setId(1);
        establishments.get(0).setReiting(5);
        establishments.get(0).setAverageSumm(500);

        Recomendations recomendation1 = new Recomendations(4, "Круто", 250, users.get(0));
        recomendation1.setId(1);
        establishments.get(0).setReiting(4);
        establishments.get(0).setAverageSumm(250);


        Recomendations recomendation2 = new Recomendations(5, "Круто", 2530, users.get(1));
        recomendation2.setId(1);
        establishments.get(0).setReiting(5);
        establishments.get(0).setAverageSumm(2530);


        WriteFile("recomendations.txt", recomendation.toFile() + recomendation1.toFile() + recomendation2.toFile(), false);
        */
        try{
            ArrayList <Recomendations> recomendations = Recomendations.createRecomendationsFromBase("recomendations.txt",
                    users);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }


    }
}
