package WarehouseTech;

import WarehouseTech.models.User;

import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static String action;

    public static void main(String[] args) {
        User newU = new User("Anna", "Zubko", "123@i.ua", "dir", "fintech");

        System.out.println("Input Action:" +
                "\nпросмотр списка пользователей - listOfUsers" +
                "\nдобавить нового пользователя - insertUser" +
                "\nудалить пользователя - delete" +
                "\nпоиск пользователя по id - findUserById" +
                "\nпоиск пользователя по email - findUserByEmail" +
                "\nпоиск пользователя по фамилии - findUserBySurname" +
                "\nпоказать список оборудования у пользователя - TechOfUserId" +
                "\nсписок всего оборудования - listOfTech " +
                "\nдобавление нового оборудования \"на склад\" - insertTechToWarehouse" +
                "\nудаление оборудования (нельзя удалить, если оборудование выдано пользователю)" +
                "\nвыдача оборудования пользователю" +
                "\nпоиск оборудования по типу / по \"выдано/насклда\" / по пользователю" +
                "\nсоздание отчета по пользователям - текстовый файл" +
                "\nвыход - exit" );
        action = sc.nextLine();
        while (!action.equals("exit")){
            new AppController().execute(action);
            action = sc.nextLine();
        }
    }
}
