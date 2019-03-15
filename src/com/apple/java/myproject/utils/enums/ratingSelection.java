package com.apple.java.myproject.utils.enums;

public enum ratingSelection {
    Terribly("ужасно", 1),
    Badly("плохо", 2),
    Satisfactorily("удовлетворительно", 3),
    Goodly("хорошо", 4),
    Perfectly("отлично", 5);
    private String description;
    private Integer assessment;
    private ratingSelection(String text, int assessment){
        this.description = text;
        this.assessment = assessment;
    }
    public String getDescription(){
        return this.description;
    }
    public Integer getAssessment(){
        return this.assessment;
    }
    public static ratingSelection getChoice(int assessment){
        ratingSelection returnRating = null;
        if(assessment == 1) returnRating = Terribly;
        if(assessment == 2) returnRating = Badly;
        if(assessment == 3) returnRating = Satisfactorily;
        if(assessment == 4) returnRating = Goodly;
        if(assessment == 5) returnRating = Perfectly;
        return returnRating;
    }
}
