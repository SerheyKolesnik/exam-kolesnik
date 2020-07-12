package WarehouseTech;


import WarehouseTech.connector.TechDatabaseConnector;
import WarehouseTech.connector.UserDatabaseConnector;
import WarehouseTech.models.Tech;
import WarehouseTech.models.User;

import java.util.List;
import java.util.Scanner;

public class AppController {
    private static Scanner sc = new Scanner(System.in);

    public void execute(String action) {
        UserDatabaseConnector db = UserDatabaseConnector.getInstance();
        TechDatabaseConnector dbTech = TechDatabaseConnector.getInstance();
        switch (action) {
            case "listOfUsers":
                List<User> list = db.getAll();
                System.out.println(list);
                break;
            case "insertUser":
                db.insert(getNewUser());
                break;
            case "delete":
                db.delete(getId());
                break;
            case "findUserById":
                db.findBy("id", String.valueOf(getId()));
                break;
            case "findUserByEmail":
                db.findBy("email", getEmail());
                break;
            case "findUserBySurname":
                db.findBy("surname", getSurname());
                break;
            case "listOfTech":
                List<Tech> listTech = dbTech.getAll();
                System.out.println(listTech);
                break;
            case "TechOfUserId":
                dbTech.findBy("id", String.valueOf(getId()));
                break;
            case "insertTechToWarehouse":
                dbTech.insert(getNewTechToWH());
                break;
            case "findTechById":
                dbTech.insert(getNewTechToWH());
                break;
            case "deleteTechById":
                if (dbTech.findById(getIdTech()).getUserId() == null) {
                    dbTech.delete(getIdTech());
                } else {
                    System.out.println("Оборудование закреплено за пользователем\nНевозможно списать");
                }
                break;

            default:
                break;
        }

    }

    public static Tech getNewTechToWH() {
        System.out.println("type Tech");
        String type = sc.nextLine();
        System.out.println("name");
        String name = sc.nextLine();
        System.out.println("model");
        String model = sc.nextLine();
        System.out.println("date");
        String date = sc.nextLine();
        return new Tech(type, name, model, date);
    }

    public static User getNewUser() {
        System.out.println("surname");
        String surname = sc.nextLine();
        System.out.println("name");
        String name = sc.nextLine();
        System.out.println("email");
        String email = sc.nextLine();
        System.out.println("role");
        String role = sc.nextLine();
        System.out.println("team");
        String team = sc.nextLine();
        return new User(surname, name, email, role, team);
    }

    public static int getId() {
        System.out.println("id of user");
        int id = sc.nextInt();
        return id;
    }

    public static String getEmail() {
        System.out.println("insert email");
        String email = sc.nextLine();
        return email;
    }

    public static String getSurname() {
        System.out.println("surname");
        String surname = sc.nextLine();
        return surname;
    }

    public static int getIdTech() {
        System.out.println("id of tech");
        int id = sc.nextInt();
        return id;
    }

}

