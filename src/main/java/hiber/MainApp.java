package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Timur", "Shamoyan", "shamoyan@mail.ru");
      User user2 = new User("Konstantin", "Ivanov", "ivanov@mail.ru");
      User user3 = new User("Ivan", "Kotov", "kotov@mail.ru");
      User user4 = new User("Petr", "Petrov", "petrov@mail.ru");

      user1.setCar(new Car("BMW",7));
      user2.setCar(new Car("LADA",2114));
      user3.setCar(new Car("Audi",4));
      user4.setCar(new Car("Geely",10));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car Model = " + user.getCar().getModel());
            System.out.println("Car Series = " + user.getCar().getSeries());
         }
         System.out.println();
      }

      User foundUser = userService.getUserByCar("BMW", 7);
      if (foundUser != null) {
         System.out.println("Found User: " + foundUser.getFirstName() + " " + foundUser.getLastName());
      }

      context.close();
   }
}
