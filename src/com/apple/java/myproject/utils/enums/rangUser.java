package com.apple.java.myproject.utils.enums;

public enum rangUser {
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
        if(text.equals("Newcomer")) returnRang = Newcomer;
        if(text.equals("Userman")) returnRang = Userman;
        if(text.equals("Empirical")) returnRang = Empirical;
        if(text.equals("Hardman")) returnRang = Hardman;
        return returnRang;
    }
}
