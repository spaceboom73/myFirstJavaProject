package com.apple.java.myproject.utils.enums;

public enum rangUser {
    None("зарегистрированный"),
    Newcomer("новичок"),// новичок
    Userman("постоянный пользователь"), // постоянный пользователь
    Empirical("опытный"), // опытный
    Hardman("мастер"); // мастер
    private String description;
    private rangUser(String text){
        this.description = text;
    }
    public String getRangText(){
        return this.description;
    }
    public static rangUser getChoice(String text){
        rangUser returnRang = null;
        if(text.equals("зарегистрированный")) returnRang = None;
        if(text.equals("новичок")) returnRang = Newcomer;
        if(text.equals("постоянный пользователь")) returnRang = Userman;
        if(text.equals("опытный")) returnRang = Empirical;
        if(text.equals("мастер")) returnRang = Hardman;
        return returnRang;
    }
}
