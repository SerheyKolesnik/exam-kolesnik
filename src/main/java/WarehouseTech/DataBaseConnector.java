package WarehouseTech;

import WarehouseTech.connector.TechDatabaseConnector;
import WarehouseTech.models.Tech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
    public static void main(String[] args) {
        Tech tech1 = new Tech(123654,"LAPTOP","HP","9081","06.07.2020","1");
        TechDatabaseConnector t = new TechDatabaseConnector();
        t.getInstance().createNewTable();
        t.insert(tech1);

    }
}
