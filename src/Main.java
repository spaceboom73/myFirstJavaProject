
import com.apple.java.myproject.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Users user = new Users();
        user.setId(1);
        user.setUserName("Первый пользователь");
        user.RegistrationDate();
        for(int i=0; i < 100; i++)
            user.summRatingsPlus();
        System.out.println("Информация о новом пользователе: ");
        System.out.println("1. ID - " + user.getId());
        System.out.println("2. userName - " + user.getUserName());
        System.out.println("3. registrationDate - " + user.getRegistrationDate());
        System.out.print("4. ");
        user.getRangUser();
        Recomendations recomendation = user.createRecomendation(5, "Даннное заведение оставило только хорошее впечатление", 500);
        recomendation.setId(1);
        System.out.println("Информация о новой рекомендации:");
        System.out.println("1. Average check user - " + recomendation.getAverageCena());
        System.out.println("2. ID of establishment - " + recomendation.getId());
        System.out.println("3. Rating of user now - "+ recomendation.getRating());
        System.out.println("4. TextRecomendation - "+ recomendation.getTextRecomendation());

   }

}
