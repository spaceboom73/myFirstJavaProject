package com.apple.java;

import com.apple.java.myproject.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.apple.java.myproject.exception.LoadBaseException;
import com.apple.java.myproject.exception.UsersNotFoundException;
import com.apple.java.myproject.textprocessing.EstablishmentBase;
import com.apple.java.myproject.textprocessing.RecomendationsBase;
import com.apple.java.myproject.textprocessing.UsersBase;
import com.apple.java.myproject.utils.enums.ratingSelection;


import static com.apple.java.myproject.utils.FileJobber.*;



public class Main {
    public static void main(String[] args) throws LoadBaseException, UsersNotFoundException {
/*
        Users [] users = new Users[5];

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
        WriteFile("users.txt", baseText, false);
        /*UsersBase userFile = new UsersBase();
        ArrayList<Users> users = userFile.loadUsersList("users.txt");
        //Users("users.txt"); // читаем пользователей из файла в коллекцию
        for(Users user:users){
            user.printInfo();
        }
       // System.out.println("Кол-во пользователей: " + i);
        System.out.println();
        System.out.println();
        /*for(int i = 0; i < users.size(); i++)
            users.get(i).getInfo();*/

        //читаем заведения из файла в коллекцию
        EstablishmentBase establishmentList = new EstablishmentBase();
        ArrayList <Establishments> establishments = establishmentList.loadEstablishmentList("establishment.txt");

        try {
            for(Establishments establishment:establishments){
                establishment.printInfo();
                /*establishment.setAverageSumm(300);
                establishment.setReiting(ratingSelection.Goodly);*/
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        String fileText = "";
        for(Establishments establishment:establishments){
            fileText += establishment.toFile();
        }
        WriteFile("establishment.txt", fileText, false);
        System.out.println();
        System.out.println();
        System.out.println();

   /*     Recomendations recomendation = new Recomendations(ratingSelection.Perfectly, "Круто", 500, users[2]);
        recomendation.setId(1);
        establishments.get(0).setReiting(ratingSelection.Satisfactorily);
        establishments.get(0).setAverageSumm(500);

        Recomendations recomendation1 = new Recomendations(ratingSelection.Goodly, "Круто", 250, users[0]);
        recomendation1.setId(1);
        establishments.get(0).setReiting(ratingSelection.Goodly);
        establishments.get(0).setAverageSumm(250);


        Recomendations recomendation2 = new Recomendations(ratingSelection.Perfectly, "Круто", 2530, users[1]);
        recomendation2.setId(1);
        establishments.get(0).setReiting(ratingSelection.Perfectly);
        establishments.get(0).setAverageSumm(2530);

        WriteFile("recomendations.txt", recomendation.toFile() + recomendation1.toFile() + recomendation2.toFile(), false);
       */ /*try{
            RecomendationsBase recomendationsList = new RecomendationsBase();
            ArrayList <Recomendations> recomendations = recomendationsList.loadRecomendationsList("recomendations.txt",
                    users);

            for(Recomendations recomendation:recomendations){
                recomendation.printInfo();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }*/


    }
}
