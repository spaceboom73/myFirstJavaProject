package com.apple.java;

import com.apple.java.myproject.*;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.lang.Object;

import com.apple.java.myproject.databases.establishmentfacade.EstablishmentDatabase;
import com.apple.java.myproject.databases.userfacade.UsersDatabase;
import com.apple.java.myproject.exception.*;
import com.apple.java.myproject.textprocessing.EstablishmentBase;
import com.apple.java.myproject.textprocessing.RecomendationsBase;
import com.apple.java.myproject.textprocessing.UsersBase;
import com.apple.java.myproject.utils.enums.rangUser;
import com.apple.java.myproject.utils.enums.ratingSelection;
import org.postgresql.core.SqlCommand;


import static com.apple.java.myproject.utils.FileJobber.*;



public class Main {
    public static void main(String[] args) throws LoadBaseException, UsersNotFoundException, UncorrectInputException,
            CloneNotSupportedException, UsersBaseException, EstablishmentBaseException, InputTextException{
        List<Users> users = new ArrayList<Users>();
        List<Recomendations> recomendations = new ArrayList<Recomendations>();
        List<Establishments> establishments = new ArrayList<Establishments>();
        while(true){
            int select = showGlobalMenu();
            switch (select) {
                case 1: {
                    Integer selectUser = 0;
                    while(selectUser != 7) {
                        selectUser = showUsersMenu();
                        switch (selectUser) {
                            case 1: {
                                System.out.print("[CREATE USER] Введите имя пользователя: ");
                                Scanner sc = new Scanner(System.in);
                                String userName = sc.nextLine();
                                Users user = new Users();
                                user.setId(users.size());
                                user.setUserName(userName);
                                user.registrationDate = LocalDate.now().toString();
                                users.add(user);
                                System.out.println("[CREATE USER] Пользователь " + userName + " | ID: " + user.getId() + " создан");
                                break;
                            }
                            case 2: {
                                Integer returnValue = null;
                                while (returnValue == null) {
                                    System.out.print("[CHANGE USER] Введите ID пользователя, которого хотите изменить: ");
                                    returnValue = inputId();
                                }
                                boolean haveUser = false;
                                for (Users curretUser : users) {
                                    if (returnValue.equals(curretUser.getId())) {
                                        haveUser = true;
                                        Integer selectChangeUser = showChangeUserMenu(curretUser.getId());
                                        switch (selectChangeUser) {
                                            case 1: {
                                                System.out.print("[CHANGE USER NAME] Введите новое имя: ");
                                                Scanner sc = new Scanner(System.in);
                                                String userName = sc.nextLine();
                                                System.out.println("[CHANGE USER NAME] Имя пользователя успешно изменено с "
                                                        + curretUser.getUserName() + " на " + userName);
                                                curretUser.setUserName(userName);
                                                break;
                                            }
                                            case 2: {
                                                System.out.println("[CHANGE USER RANG] Выберите новый ранг для пользователя:");
                                                System.out.println("1. Новичок");
                                                System.out.println("2. Постоянный пользователь");
                                                System.out.println("3. Опытный");
                                                System.out.println("4. Мастер");
                                                Scanner sc = new Scanner(System.in);
                                                Integer selectValue = 0;
                                                try {
                                                    selectValue = Integer.parseInt(sc.nextLine());
                                                } catch (Exception ex) {
                                                    System.out.println("[CHANGE RANG ERROR] Введён некорректный пункт меню");
                                                    break;
                                                }
                                                if (selectValue < 1 || selectValue > 4 && selectValue != null) {
                                                    System.out.println("[CHANGE RANG ERROR] Введён некорректный пункт меню");
                                                    break;
                                                }
                                                switch (selectValue) {
                                                    case 1:
                                                        curretUser.setRangUser(rangUser.Newcomer);
                                                        break;
                                                    case 2:
                                                        curretUser.setRangUser(rangUser.Userman);
                                                        break;
                                                    case 3:
                                                        curretUser.setRangUser(rangUser.Empirical);
                                                        break;
                                                    case 4:
                                                        curretUser.setRangUser(rangUser.Hardman);
                                                        break;
                                                }
                                                System.out.println("[CHANGE USER RANG] Ранг пользователя изменён на '" + curretUser.getRangUser() + "'");
                                                break;
                                            }
                                            case 3: {
                                                System.out.print("[CHANGE RECOMENDATIONS SUMM] Введите новое количество рекомендаций: ");
                                                Scanner sc = new Scanner(System.in);
                                                Integer summValue = 0;
                                                try {
                                                    summValue = Integer.parseInt(sc.nextLine());
                                                } catch (Exception ex) {
                                                    System.out.println("[CHANGE SUMM ERROR]: Введённое не является числом");
                                                    break;
                                                }
                                                if (summValue < 0 || summValue > 100000 && summValue != null) {
                                                    System.out.println("[CHANGE SUMM ERROR]: Введёное число не реально");
                                                    break;
                                                }
                                                curretUser.setSummRatings(summValue);
                                                System.out.println("[CHANGE RECOMENDATIONS SUMM] Значение успешно изменено на " + curretUser.getSummRatings());
                                                break;
                                            }
                                            case 4:
                                                break;
                                        }
                                        break;
                                    }
                                }
                                if(!haveUser) System.out.println("[USER NOT FOUND]: Пользователь с данным ID не найден");
                                break;
                            }
                            case 3: {
                                Integer selectId = null;
                                while(selectId == null) {
                                    System.out.print("[DELETE USER] Введите ID пользователя, который хотите удалить ");
                                    selectId = inputId();
                                    if(selectId != null) {
                                        boolean haveUser = false;
                                        for (Users currentUser : users) {
                                            if (selectId.equals(currentUser.getId())) {
                                                System.out.println("[DELETE USER]: Пользователь " + currentUser.getUserName() +
                                                        " | " + currentUser.getId() + " удалён");
                                                users.remove(currentUser);
                                                for(int i = currentUser.getId(); i < users.size(); i++){
                                                    users.get(i).setId(users.get(i).getId()-1);
                                                }
                                                // Место для применения лямбда выражения
                                                haveUser = true;
                                                break;
                                            }
                                        }
                                        if (!haveUser)
                                            System.out.println("[USER NOT FOUND]: Пользователь с данным ID не найден");
                                    }
                                }
                                break;
                            }
                            case 4: { // загрузка из файла
                                users.clear();
                                users = new UsersBase().loadUsersList("src/main/java/com/apple/java/users.txt");
                                System.out.println("[LOAD USERS INFO]: Коллекция очищена.");
                                System.out.println("[LOAD USERS INFO]: Коллекция пользователей из файла успешно загружена.");
                                break;
                            }
                            case 5: { // загрузка в файл
                                String text = "";
                                for(Users currentUser: users){
                                    text += currentUser.toFile();
                                }
                                if(text.length() > 0){
                                    WriteFile("src/main/java/com/apple/java/users.txt", text, false);
                                    System.out.println("[WRITE USERS IN FILE]: Запись пользователей из класса произошла успешно");
                                }
                                break;
                            }
                            case 6: {
                                System.out.println("[SHOW INFORMATION] Введите ID пользователя, информацию о котором хотите получить");
                                System.out.println("[SHOW INFORMATION] Чтобы вывести информацию обо всех пользователях, введите all");
                                Integer selectId = null;
                                while(selectId == null) {
                                    System.out.print(">: ");
                                    Scanner sc = new Scanner(System.in);
                                    String text = sc.nextLine();
                                    if (text.equals("all")) {
                                        users.forEach((currentUser) -> currentUser.printInfo());
                                        selectId = 1;
                                    } else {
                                        try {
                                            selectId = Integer.parseInt(text);
                                        } catch (Exception ex) {
                                            System.out.println("[SHOW INFORMATION ERROR]: Некорректный ID");
                                        }
                                        if (selectId != null){
                                            if(selectId < 0) {
                                                System.out.println("[SHOW INFORMATION ERROR]: Некорректный ID");
                                                selectId = null;
                                            }
                                            else {
                                                boolean haveUser = false;
                                                for(Users currentUser: users){
                                                    if(selectId.equals(currentUser.getId())) {
                                                        currentUser.printInfo();
                                                        haveUser = true;
                                                    }
                                                }
                                                if(haveUser == false){
                                                    System.out.println("[USER BOT FOUND]: Пользователь с данным ID не найден");
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                            case 7:
                                break;
                        }
                    }
                    break;
                }
                case 2: {
                    Integer selectEstablishment = 0;
                    while(selectEstablishment != 7){
                        selectEstablishment = showEstablishmentsMenu();
                        switch(selectEstablishment){
                            case 1: {
                                System.out.print("[CREATE ESTABLISHMENT] Введите название заведения: ");
                                Scanner sc = new Scanner(System.in);
                                String establishmentName = sc.nextLine();
                                Establishments establishment = new Establishments(establishments.size(), establishmentName);
                                establishments.add(establishment);
                                System.out.println("[CREATE ESTABLISHMENT] Заведение " + establishmentName + " | ID: " + establishment.getId() + " создано");
                                break;
                            }
                            case 2: {
                                Integer returnValue = null;
                                boolean haveEstabl = false;
                                while (returnValue == null) {
                                    System.out.print("[CHANGE ESTABLISHMENT] Введите ID заведения, которого хотите изменить: ");
                                    returnValue = inputId();
                                }
                                for (Establishments curretEstabl : establishments) {
                                    if (returnValue.equals(curretEstabl.getId())) {
                                        haveEstabl = true;
                                        Integer selectEstablUser = showChangeEstablishmentMenu(curretEstabl.getId());
                                        switch (selectEstablUser) {
                                            case 1: {
                                                System.out.print("[CHANGE ESTABLISHMENT NAME] Введите новое название: ");
                                                Scanner sc = new Scanner(System.in);
                                                String establName = sc.nextLine();
                                                System.out.println("[CHANGE ESTABLISHMENT NAME] Название успешно изменено с "
                                                        + curretEstabl.getName() + " на " + establName);
                                                curretEstabl.setName(establName);
                                                break;
                                            }
                                            case 2: {
                                                System.out.print("[CHANGE ESTABLISHMENT CHECK] Введите новый чек: ");
                                                Scanner sc = new Scanner(System.in);
                                                Integer currentCheck = null;
                                                if (currentCheck == null) {
                                                    try {
                                                        currentCheck = Integer.parseInt(sc.nextLine());
                                                    } catch (Exception ex) {
                                                        System.out.println("[CHANGE ESTABLISHMENT ERROR]: Введённое не является числом");
                                                        break;
                                                    }
                                                    if (currentCheck != null) {
                                                        if (currentCheck < 1) {
                                                            System.out.println("[CHANGE ESTABLISHMENT ERROR]: Введённое не может быть средним чеком");
                                                            break;
                                                        }
                                                    }
                                                    curretEstabl.setAverageSumm(currentCheck);
                                                    System.out.println("[CHANGE ESTABLISHMENT CHECK]: Новый чек успешно добавлен");
                                                    break;
                                                }
                                            }
                                            case 3: {
                                                Integer currentRating = selectRatingMenu();
                                                if (currentRating != 6) {
                                                    curretEstabl.setReiting(ratingSelection.getChoice(currentRating));
                                                    System.out.println("[CHANGE ESTABLISHMENT RATING]: Новый рейтинг успешно добавлен к коллекцтт");
                                                    break;
                                                } else break;
                                            }
                                            case 4:
                                                break;
                                        }
                                    }
                                }
                                if(!haveEstabl){
                                    System.out.println("[CHANGE ESTABLISHMENT ERROR]: Данное заведение не найдено в коллекции");
                                    break;
                                }
                                break;
                            }
                            case 3: {
                                System.out.print("[DELETE ESTABLISHMENT] Введите ID заведения, который хотите удалить: ");
                                Integer currentEstabl = null;
                                boolean hasEstabl = false;
                                while(currentEstabl == null) {
                                    currentEstabl = inputId();
                                    for(Establishments establishment: establishments){
                                        if(establishment.getId().equals(currentEstabl)){
                                            hasEstabl = true;
                                            establishments.remove(establishment);
                                            System.out.println("[DELETE ESTABLISHMENT]: Заведение успешно удалено из коллекции");
                                            for(Establishments currentEstableshment: establishments){
                                                currentEstableshment.setId(currentEstableshment.getId()-1);
                                            }
                                            break;
                                        }
                                    }
                                }
                                if(!hasEstabl){
                                    System.out.println("[DELETE ESTABLISHMENT ERROR]: Заведение с данным ID не найдено в коллекции");
                                    break;
                                }
                                break;
                            }
                            case 4: {
                                establishments.clear();
                                establishments = new EstablishmentBase().loadEstablishmentList("src/main/java/com/apple/java/establishment.txt");
                                System.out.println("[LOAD ESTABLISHMENT INFO]: Коллекция очищена.");
                                System.out.println("[LOAD ESTABLISHMENT INFO]: Коллекция пользователей из файла успешно загружена.");
                                break;
                            }
                            case 5: {
                                String text = "";
                                for(Establishments currentEstabl: establishments){
                                    text += currentEstabl.toFile();
                                }
                                if(text.length() > 0){
                                    WriteFile("src/main/java/com/apple/java/establishment.txt", text, false);
                                    System.out.println("[WRITE ESTABLISHMENT IN FILE]: Запись заведений из коллекции произошла успешно");
                                }
                                break;
                            }
                            case 6: {
                                System.out.println("[SHOW INFORMATION] Введите ID заведения, информацию о котором хотите получить");
                                System.out.println("[SHOW INFORMATION] Чтобы вывести информацию обо всех заведениях, введите all");
                                Scanner sc = new Scanner(System.in);
                                String text = sc.nextLine();
                                if(text.equals("all"))
                                    establishments.forEach((currentEstabl)-> currentEstabl.printInfo());
                                else{
                                    Integer currentId = null;
                                    while (currentId == null) {
                                        try {
                                            currentId = Integer.parseInt(text);
                                        } catch (Exception ex) {
                                            System.out.println("[INPUT ERROR]: Введённое не может быть числом");
                                            break;
                                        }
                                        if (currentId != null) {
                                            if (currentId < 0) {
                                                System.out.println("[INPUT ERROR]: Данное число не может быть ID заведения");
                                                break;
                                            }
                                        }
                                        boolean hasEstabl = false;
                                        for (Establishments currentEstabl : establishments) {
                                            if(currentEstabl.getId().equals(currentId)){
                                                hasEstabl = true;
                                                currentEstabl.printInfo();
                                            }
                                        }
                                        if(!hasEstabl){
                                            System.out.println("[SHOW INFORMATION ERROR]: Данное заведение не найдено в коллекции");
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                            case 7:{
                                break;
                            }



                        }
                    }
                    break;
                }
                case 3: {
                    Integer selectRecomendation = 0;
                    while(selectRecomendation != 7) {
                        selectRecomendation = showRecomendationMenu();
                        switch (selectRecomendation) {
                            case 1: {
                                System.out.print("[CREATE RECOMENDATION] Введите ID пользователя, который оставит рекомендацию: ");
                                Integer currentId = inputId();
                                if(currentId == null){
                                    System.out.println("[CREATE RECOMENDATION ERROR]: Некорректное значение ID");
                                    break;
                                }
                                else{

                                    boolean hasEstabl = false;
                                    boolean hasUser = false;
                                    for(Users user: users){
                                        if(user.getId().equals(currentId)){
                                            hasUser = true;
                                            System.out.print("[CREATE RECOMENDATION] Введите ID заведение, которому пользователь оставит рекомендацию: ");
                                            Integer currentIdEstablishment = inputId();
                                            if(currentIdEstablishment == null){
                                                System.out.println("[CREATE RECOMENDATION ERROR]: Некорректное значение ID");
                                            }
                                            else{
                                                for(Establishments establishment: establishments){
                                                    if(establishment.getId().equals(currentIdEstablishment)) {
                                                        hasEstabl = true;
                                                        System.out.print("[CREATE RECOMENDATION] Введите текст рекомендации: ");
                                                        Scanner sc = new Scanner(System.in);
                                                        String text = sc.nextLine();
                                                        Integer checkSumm = null;
                                                        while (checkSumm == null) {
                                                            System.out.print("[CREATE RECOMENDATION] Введите чек посещения: ");
                                                            try {
                                                                checkSumm = Integer.parseInt(sc.nextLine());
                                                            } catch (Exception ex) {
                                                                System.out.println("[CREATE RECOMENDATION ERROR]: Введёное не является числом");
                                                                checkSumm = null;
                                                            }
                                                            if (checkSumm != null) {
                                                                if (checkSumm < 0) {
                                                                    System.out.println("[CREATE RECOMENDATION ERROR]: Введённое не является реальной ценой");
                                                                    checkSumm = null;
                                                                }
                                                            }
                                                        }
                                                        System.out.println("[CREATE RECOMENDATION] Выберите оценку заведения");
                                                        Integer ratingSumm = selectRatingMenu();
                                                        Recomendations returnRecomendation = new Recomendations(user);
                                                        returnRecomendation.setId(currentIdEstablishment);
                                                        returnRecomendation.setRating(ratingSelection.getChoice(ratingSumm));
                                                        returnRecomendation.setAverageSumm(checkSumm);
                                                        returnRecomendation.setTextRecomendation(text);
                                                        recomendations.add(returnRecomendation);
                                                        establishments.get(currentIdEstablishment).setAverageSumm(checkSumm);
                                                        establishments.get(currentIdEstablishment).setReiting(ratingSelection.getChoice(ratingSumm));
                                                        System.out.println("[CREATE RECOMENDATIONS]: Рекомендация успешно создалась");
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if(!hasUser){
                                        System.out.println("[CREATE RECOMENDATION ERROR]: Данный пользователь не найден в коллекции");
                                        break;
                                    }
                                    if(!hasEstabl){
                                        System.out.println("[CREATE RECOMENDATION ERROR]: Данное заведение не найдено в коллекции");
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                case 4: {
                    Integer selectJobBase = showDatabaseMenu();
                    switch (selectJobBase) {
                        case 1: {
                            Integer selectUserJobBase = showUserDatabaseMenu();
                            switch (selectUserJobBase) {
                                case 1: {
                                    System.out.println("[ADD USER DB] Введите ID пользователя, который хотите добавить в БД");
                                    Integer currentId = null;
                                    while (currentId == null) {
                                        currentId = inputId();
                                        if (currentId != null) {
                                            boolean haveUser = false;
                                            for (Users currentUser : users) {
                                                if (currentId.equals(currentUser.getId())) {
                                                    haveUser = true;
                                                    if (new UsersDatabase().checkUser(currentUser.getId())) {
                                                        System.out.println("Пользователь с таким ID уже есть в БД. Обновите пользователя с данным ID");
                                                        break;
                                                    } else {
                                                        UsersDatabase usersDatabase = new UsersDatabase();
                                                        usersDatabase.createUser(currentUser);
                                                    }
                                                }
                                            }
                                            if (!haveUser) {
                                                System.out.println("[USER BOT FOUND]: Пользователь с данным ID не найден");
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                                case 2: {
                                    System.out.println("[UPDATE USER DB] Введите ID пользователя, который хотите обновить");
                                    Integer currentId = null;
                                    boolean hasUser = false;
                                    while (currentId == null) {
                                        currentId = inputId();
                                        if (currentId != null) {
                                            for (Users user : users) {
                                                if (user.getId().equals(currentId)) {
                                                    hasUser = true;
                                                    if (new UsersDatabase().checkUser(currentId)) {
                                                        UsersDatabase usersDatabase = new UsersDatabase();
                                                        usersDatabase.updateUser(user);
                                                    } else {
                                                        System.out.println("[UPDATE USER ERROR]: Пользователь с данным ID не найден в базе. Вы можете добавить этого пользователя");
                                                        break;
                                                    }
                                                }
                                            }
                                            if (!hasUser) {
                                                System.out.println("[UPDATE USER ERROR]: Пользователь с данным ID не найден в коллекции");
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                                case 3: {
                                    System.out.print("[DELETE USER] Введите ID пользователя, который хотите удалить: ");
                                    Integer currentId = null;
                                    while (currentId == null) {
                                        currentId = inputId();
                                        if (currentId != null) {
                                            if (!new UsersDatabase().checkUser(currentId)) {
                                                System.out.println("[DELETE ERROR]: Пользовать с данным ID не найден в базе данных");
                                                break;
                                            } else {
                                                UsersDatabase usersDatabase = new UsersDatabase();
                                                usersDatabase.deleteUser(currentId);
                                            }
                                        }
                                    }
                                    break;
                                }
                                case 4: {
                                    System.out.print("[SELECT USER BY ID] Введите ID пользователя, которого хотите получить: ");
                                    Integer currentId = null;
                                    while (currentId == null) {
                                        currentId = inputId();
                                        if (currentId != null) {
                                            if (new UsersDatabase().checkUser(currentId)) {
                                                UsersDatabase usersDatabase = new UsersDatabase();
                                                Users currentUser = usersDatabase.selectUser(currentId);
                                                currentUser.printInfo();
                                                break;
                                            } else {
                                                System.out.println("[SELECT USER BY ID]: Пользователь с данным ID не найден в базе данных");
                                                break;
                                            }

                                        }
                                    }
                                    break;
                                }
                                case 5: {
                                    break;
                                }
                            }
                            break;
                        }
                        case 2:
                            Integer selectEstablishmentJobBase = showEstablishmentDatabaseMenu();
                            switch (selectEstablishmentJobBase) {
                                case 1: {
                                    System.out.println("[ADD ESTABLISHMENT DB] Введите ID заведения, который хотите добавить в БД");
                                    Integer currentId = null;
                                    while (currentId == null) {
                                        currentId = inputId();
                                        if (currentId != null) {
                                            boolean haveEstabl = false;
                                            for (Establishments currentEstabl : establishments) {
                                                if (currentId.equals(currentEstabl.getId())) {
                                                    haveEstabl = true;
                                                    if (new EstablishmentDatabase().checkEstablishment(currentEstabl.getId())) {
                                                        System.out.println("Заведение с таким ID уже есть в БД. Обновите заведение с данным ID");
                                                        break;
                                                    } else {
                                                        EstablishmentDatabase establDatabase = new EstablishmentDatabase();
                                                        establDatabase.createEstablishment(currentEstabl);
                                                    }
                                                }
                                            }
                                            if (!haveEstabl) {
                                                System.out.println("[ESTABLISHMENT BOT FOUND]: Завдение с данным ID не найдено");
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                                case 2: {
                                    System.out.println("[UPDATE USER DB] Введите ID пользователя, который хотите обновить");
                                    Integer currentId = null;
                                    boolean hasEstabl = false;
                                    while (currentId == null) {
                                        currentId = inputId();
                                        if (currentId != null) {
                                            for (Establishments establishment : establishments) {
                                                if (establishment.getId().equals(currentId)) {
                                                    hasEstabl = true;
                                                    if (new EstablishmentDatabase().checkEstablishment(currentId)) {
                                                        EstablishmentDatabase establishmentDatabase = new EstablishmentDatabase();
                                                        establishmentDatabase.updateEstablishment(establishment);
                                                    } else {
                                                        System.out.println("[UPDATE ESTABLISHMENT ERROR]: Заведение с данным ID не найдено в базе. Вы можете добавить это заведение");
                                                        break;
                                                    }
                                                }
                                            }
                                            if (!hasEstabl) {
                                                System.out.println("[UPDATE ESTABLISHMENT ERROR]: Заведение с данным ID не найдено в коллекции");
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                                case 3: {
                                    System.out.print("[DELETE ESTABLISHMENT] Введите ID заведения, который хотите удалить: ");
                                    Integer currentId = null;
                                    while(currentId == null){
                                        currentId = inputId();
                                        if(currentId != null) {
                                            if (!new EstablishmentDatabase().checkEstablishment(currentId)) {
                                                System.out.println("[DELETE ERROR]: Заведение с данным ID не найдено в базе данных");
                                                break;
                                            } else {
                                                EstablishmentDatabase establishmentDatabase = new EstablishmentDatabase();
                                                establishmentDatabase.deleteEstabl(currentId);
                                            }
                                        }
                                    }
                                    break;
                                }
                                case 4: {
                                    System.out.print("[SELECT ESTABLISHMENT BY ID] Введите ID заведения, которое хотите получить: ");
                                    Integer currentId = null;
                                    while(currentId == null) {
                                        currentId = inputId();
                                        if (currentId != null) {
                                            if (new EstablishmentDatabase().checkEstablishment(currentId)) {
                                                EstablishmentDatabase establishmentDatabase = new EstablishmentDatabase();
                                                Establishments currentEstabl = establishmentDatabase.selectEstabl(currentId);
                                                currentEstabl.printInfo();
                                                break;
                                            } else {
                                                System.out.println("[SELECT ESTABLISHMENT BY ID]: Заведение с данным ID не найдено в базе данных");
                                                break;
                                            }

                                        }
                                    }
                                    break;
                                }
                                case 5: {
                                    break;
                                }
                        }
                        case 3:{
                            break;
                        }
                        case 4:{
                            break;
                        }
                    }
                    break;
                }
                case 5: {
                    System.out.println("[INFO]: Вы завершили программу.");
                    System.exit(1);
                    break;
                }
            }
        }
    }
    public static Integer inputId(){
        Integer selectId = null;
        Scanner sc = new Scanner(System.in);
        try{
            selectId = Integer.parseInt(sc.nextLine());
        }
        catch (Exception ex){
            System.out.println("[INPUT ERROR]: Введённое не является числом");
            selectId = null;
        }
        if(selectId != null && selectId < 0){
            System.out.println("[INPUT ERROR]: Введённое число не может быть ID");
            selectId = null;
        }
        return selectId;
    }
    public static Integer selectRatingMenu() throws UncorrectInputException{
        System.out.println("[CHANGE RATING ESTABLISHMENT] Выберите рейтинг заведения: ");
        System.out.println("|===================================================|");
        System.out.println("1. Ужасно");
        System.out.println("2. Плохо");
        System.out.println("3. Удовлетворительно");
        System.out.println("4. Хорошо");
        System.out.println("5. Отлично");
        System.out.println("6. Назад");
        System.out.println("|===================================================|");
        Scanner sc = new Scanner(System.in);
        Integer returnValue = scannerTextWithRange(1, 6);
        return returnValue;
    }
    public static Integer showUserDatabaseMenu() throws UncorrectInputException{
        System.out.println("|===================================================|");
        System.out.println("1. Добавить пользователя в БД");
        System.out.println("2. Обновить пользователя");
        System.out.println("3. Удалить пользователя");
        System.out.println("4. Показать пользователя из БД");
        System.out.println("5. Назад");
        System.out.println("|===================================================|");
        Scanner sc = new Scanner(System.in);
        Integer returnValue = scannerTextWithRange(1, 4);
        return returnValue;
    }
    public static Integer showEstablishmentDatabaseMenu() throws UncorrectInputException{
        System.out.println("|===================================================|");
        System.out.println("1. Добавить заведение в БД");
        System.out.println("2. Обновить заведение");
        System.out.println("3. Удалить заведение");
        System.out.println("4. Показать заведение из БД");
        System.out.println("5. Назад");
        System.out.println("|===================================================|");
        Scanner sc = new Scanner(System.in);
        Integer returnValue = scannerTextWithRange(1, 4);
        return returnValue;
    }
    public static Integer showDatabaseMenu() throws UncorrectInputException{
        System.out.println("|===================================================|");
        System.out.println("1. Пользователи");
        System.out.println("2. Заведения");
        System.out.println("3. Рекомендации");
        System.out.println("4. Вернуться назад");
        System.out.println("|===================================================|");
        Scanner sc = new Scanner(System.in);
        Integer returnValue = scannerTextWithRange(1, 4);
        return returnValue;
    }
    public static Integer showChangeUserMenu(Integer id) throws UncorrectInputException{
        System.out.println("[CHANGE USER " + id + "] Пользователь найден");
        System.out.println("[CHANGE USER " + id + "] Выберите хар-ку для изменения:");
        System.out.println("1. Имя пользователя");
        System.out.println("2. Ранг пользователя");
        System.out.println("3. Количество оставленных отзывов");
        System.out.println("4. Вернуться назад");
        System.out.println("|===================================================|");
        Scanner sc = new Scanner(System.in);
        Integer returnValue = scannerTextWithRange(1, 4);
        return returnValue;
    }
    public static Integer showChangeEstablishmentMenu(Integer id) throws UncorrectInputException{
        System.out.println("[CHANGE ESTABLISHMENT " + id + "] Заведение найдено");
        System.out.println("[CHANGE ESTABLISHMENT " + id + "] Выберите хар-ку для изменения:");
        System.out.println("1. Название заведения");
        System.out.println("2. Добавить чек");
        System.out.println("3. Добавить рейтинг заведения");
        System.out.println("4. Вернуться назад");
        System.out.println("|===================================================|");
        Scanner sc = new Scanner(System.in);
        Integer returnValue = scannerTextWithRange(1, 4);
        return returnValue;
    }
    public static Integer scannerTextWithRange(Integer min, Integer max) throws  UncorrectInputException{
        Scanner sc = new Scanner(System.in);
        Integer returnValue = 0;
        try {
            returnValue = Integer.parseInt(sc.nextLine());
        } catch (Exception ex) {
            System.out.println("[WARNING]: Произошла ошибка. Введено не число");
            System.exit(1);
        }
        if (returnValue < 1 || returnValue > 7) {
            throw new UncorrectInputException("Вы ввели не верный пункт меню");
        }
        return returnValue;
    }
    public static Integer showEstablishmentsMenu() throws UncorrectInputException {
        System.out.println("|===================================================|");
        System.out.println("1. Создать заведение");
        System.out.println("2. Изменить заведение");
        System.out.println("3. Удалить заведение");
        System.out.println("4. Загрузить коллекцию заведений из файла");
        System.out.println("5. Cохранить коллекцию заведений в файл");
        System.out.println("6. Показать информацию о заведении (для всех заведений введите all)");
        System.out.println("7. Вернуться назад");
        System.out.println("|===================================================|");
        Integer returnValue = scannerTextWithRange(1, 7);
        return returnValue;
    }
    public static Integer showUsersMenu() throws UncorrectInputException {
        System.out.println("|===================================================|");
        System.out.println("1. Создать пользователя");
        System.out.println("2. Изменить пользователя");
        System.out.println("3. Удалить пользователя");
        System.out.println("4. Загрузить коллекцию пользователей");
        System.out.println("5. Cохранить коллекцию пользователей");
        System.out.println("6. Показать информацию о пользователе (для всех пользователей введите all)");
        System.out.println("7. Вернуться назад");
        System.out.println("|===================================================|");
        Scanner sc = new Scanner(System.in);
        Integer returnValue = scannerTextWithRange(1, 7);
        return returnValue;
    }
    public static Integer showRecomendationMenu() throws UncorrectInputException {
        System.out.println("|===================================================|");
        System.out.println("1. Создать рекомендацию");
        System.out.println("2. Удалить рекомендацию");
        System.out.println("3. Загрузить коллекцию рекомендаций");
        System.out.println("4. Cохранить коллекцию рекомендаций");
        System.out.println("5. Показать информацию о рекомендации (для всех рекомендаций введите all)");
        System.out.println("6. Вернуться назад");
        System.out.println("|===================================================|");
        Scanner sc = new Scanner(System.in);
        Integer returnValue = scannerTextWithRange(1, 6);
        return returnValue;
    }
    public static Integer showGlobalMenu() throws UncorrectInputException{
        System.out.println("|===================================================|");
        System.out.println("1. Работа с пользователем");
        System.out.println("2. Работа с заведением");
        System.out.println("3. Работа с рекомендацией");
        System.out.println("4. Работа с базой данных SQL");
        System.out.println("5. Выйти");
        System.out.println("|===================================================|");
        Scanner sc = new Scanner(System.in);
        Integer returnValue = scannerTextWithRange(1, 4);
        return returnValue;
    }
}
