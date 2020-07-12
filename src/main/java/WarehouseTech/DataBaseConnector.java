package WarehouseTech;

import WarehouseTech.config.Config;
import WarehouseTech.connector.TechDatabaseConnector;
import WarehouseTech.connector.UserDatabaseConnector;
import WarehouseTech.models.Tech;
import WarehouseTech.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnector {
    public static void main(String[] args) {
        Tech tech1 = new Tech(123654, "LAPTOP", "HP", "9081", "06.07.2020", "1");
        Tech tech2 = new Tech(2, "TABLET", "APPLE", "15pro", "08.07.2020", "2");
        User u1 = new User(1, "john", "smith", "ds@i.ua", "user", "plant");
        User u2 = new User(2, "david", "jew", "jd@i.ua", "admin", "admins");

        final String sql = String.format("CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY  autoincrement," +
                "surname VARCHAR(20) NOT NULL," +
                "name VARCHAR(20) NOT NULL," +
                "email VARCHAR(20) NOT NULL," +
                "role VARCHAR(20) NOT NULL," +
                "team VARCHAR(20)" +
                ");"
        );

        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
//        TechDatabaseConnector t = new TechDatabaseConnector();
//        //t.insert(tech1);
//        t.insert(tech2);
        UserDatabaseConnector u = new UserDatabaseConnector();
        u.getInstance().createNewTable();
        u.insert(u1);
        u.insert(u2);


    }
}
