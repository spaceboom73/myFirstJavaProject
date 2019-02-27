
import com.apple.java.myproject.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Users user = new Users();
        user.setId(1);
        user.setUserName("Первый пользователь");
        user.registrationDate = new Date();
        for(int i=0; i < 100; i++)
            user.summRatingsPlus();
        System.out.println("Информация о новом пользователе: ");
        System.out.println("1. ID - " + user.getId());
        System.out.println("2. userName - " + user.getUserName());
        System.out.println("3. registrationDate - " + user.registrationDate.toString());
        System.out.print("4. ");
        user.getRangUser();
        Establishments establishment = new Establishments(1, "Gonzo Bar");
        Recomendations recomendation1 = new Recomendations(5, "Даннное заведение оставило" +
                " только хорошее впечатление", 500, user, establishment);
        recomendation1.setId(1);
        Recomendations recomendation2 = new Recomendations(2, "Даннное заведение оставило" +
                " только хорошее впечатление", 1000, user, establishment);
        recomendation2.setId(2);
        Recomendations recomendation3 = new Recomendations(2, "Даннное заведение оставило" +
                " только хорошее впечатление", 650, user, establishment);
        recomendation3.setId(3);
        Recomendations recomendation4 = new Recomendations(4, "Даннное заведение оставило" +
                " только хорошее впечатление", 700, user, establishment);
        recomendation4.setId(4);
        System.out.println("Информация о заведении:");
        System.out.println("1. Название заведения: "+establishment.getName());
        System.out.println("2. Средняя оценка: "+establishment.getReiting());
        System.out.println("3. Средняя цена в заведении: " + establishment.getAverageSumm());
        /*System.out.println("1. Average check user - " + recomendation.getAverageSumm());
        System.out.println("2. ID of establishment - " + recomendation.getId());
        System.out.println("3. Rating of user now - "+ recomendation.getRating());
        System.out.println("4. TextRecomendation - "+ recomendation.getTextRecomendation());*/

   }

}
