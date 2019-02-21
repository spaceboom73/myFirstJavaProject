package myproject;

public class Recomendations {
    private int id;
    private int userId;
    private String textRecomendation = "None";
    private int rating;
    private int averageCena;

    public Recomendations(int id){
        this.userId = id;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        if(rating > 0 && rating <= 5) {
            this.rating = rating;
            Zavedenie zavedenie = new Zavedenie(this.id);
            zavedenie.setReiting(this.rating);
        }
        else
            System.out.println("Ошибка: оценка заведения не может быть менее 1 и более 5");
    }

    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getTextRecomendation() {
        return this.textRecomendation;
    }
    public void setTextRecomendation(String textRecomendation){
        if(textRecomendation.length() > 500)
            System.out.println("Вы ввели слишком большой отзыв о заведении, сократите текст до 500 символов");
        else if(textRecomendation.length() < 60)
            System.out.println("Вы написали слишком мало, минимальный объем текста - 60 символов");
        else
            this.textRecomendation = textRecomendation;
    }

    public void setAverageCena(int averageCena) {
        this.averageCena = averageCena;
        Zavedenie zavedenie = new Zavedenie(this.id);
        zavedenie.setAverageCena(this.averageCena);
    }

    public int getAverageCena() {
        return this.averageCena;
    }
}
