package WarehouseTech.connector;

import WarehouseTech.config.Config;
import WarehouseTech.exception.UserNotFoundException;
import WarehouseTech.models.Tech;
import WarehouseTech.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechDatabaseConnector {

    private static TechDatabaseConnector instance;

    public static TechDatabaseConnector getInstance() {
        if (instance == null) {
            instance = new TechDatabaseConnector();

        }
        return instance;
    }

    public TechDatabaseConnector() {
        try {
            createNewTable();
        } catch (Exception e) {
            throw new RuntimeException("Failed createNewTable connection");
        }
    }

    public void createNewTable() {
        final String sql = String.format(
                "CREATE TABLE tech (" +
                "id INTEGER PRIMARY KEY  autoincrement," +
                "type VARCHAR(20) NOT NULL," +
                "name VARCHAR(20) NOT NULL," +
                "model VARCHAR(20) NOT NULL," +
                "date VARCHAR(20) NOT NULL," +
                "userId INTEGER);"
        );

        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insert(Tech tech) {
        String sql = "INSERT INTO tech(type,name, model, date) VALUES(?,?,?,?);";
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tech.getType());
            pstmt.setString(2, tech.getName());
            pstmt.setString(3, tech.getModel());
            pstmt.setString(4, tech.getDate());

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM tech WHERE id = ?;";
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e);
        }
        System.out.println("tech deleted = " + id);
    }

    public void update(Tech tech) {
        String sql = "UPDATE tech set type = ?, name =?, model = ?, date = ?, userId = ? WHERE id = ?";
        int id = Integer.valueOf(tech.getId());
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tech.getType());
            pstmt.setString(2, tech.getName());
            pstmt.setString(3, tech.getModel());
            pstmt.setString(4, tech.getDate());
            pstmt.setString(5, tech.getUserId());
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e);
        }
        System.out.println("User update = " + tech);

    }

    public Tech findBy(String param, String value) {
        String sql = "SELECT * FROM tech WHERE " + param + "='" + value + "';";
        List<User> array = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Tech(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getString("date"),
                        rs.getString("userId"));
            } else {
                throw new UserNotFoundException(param + " with " + value + " NOT FOUND");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection");
        }
    }

    public Tech findByWH() {
        String sql = "SELECT * FROM tech WHERE userId = null;";
        List<User> array = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Tech(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getString("date"),
                        rs.getString("userId"));
            } else {
                throw new UserNotFoundException("Tech on warehouse NOT FOUND");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection");
        }
    }
    public Tech findAtUser() {
        String sql = "SELECT * FROM tech WHERE userId != null;";
        List<User> array = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Tech(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getString("date"),
                        rs.getString("userId"));
            } else {
                throw new UserNotFoundException("Tech at user NOT FOUND");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection");
        }
    }

    public Tech findById(int value) {
        String sql = "SELECT * FROM tech WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, value);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Tech(rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getString("date"),
                        rs.getString("userId"));
            } else {
                throw new RuntimeException("not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e.getMessage());
        }
    }

    public List<Tech> getAll() {
        String sql = "SELECT * FROM tech;";
        List<Tech> array = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Config.getInstance().getDbUrl());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                array.add(new Tech(rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getString("date"),
                        rs.getString("userId")));
            }
            return array;
        } catch (SQLException e) {
            throw new RuntimeException("Failed connection" + e);
        }
    }
}
