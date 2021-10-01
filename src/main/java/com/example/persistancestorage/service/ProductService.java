package com.example.persistancestorage.service;

import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class ProductService {

    static final String DB_URL = "jdbc:h2:mem:memDb";
    static final String USER = "sa";
    static final String PASS = "";

    public ProductService( ) {

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String dropTable = "DROP TABLE IF EXISTS products;";
            String sql = "CREATE TABLE IF NOT EXISTS products " +
                    "(id VARCHAR(255) not NULL, " +
                    "value VARCHAR(255) NOT NULL," +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(dropTable);
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void put(String key, String value) {
        String query = "INSERT INTO products (`id`, `value`) VALUES ('"+ key +"','"+ value +"')";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String get(String key) {
       String query = "SELECT value FROM products WHERE products.id = '" + key + "';";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("value");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "No such key in DB!";
    }

    public boolean contains(String key) {
        String query = "SELECT * FROM products where id = '" + key +"';";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }

    public boolean remove(String key) {
        String query = "DELETE FROM products WHERE id = '" + key + "';";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            int resultSet = stmt.executeUpdate();
            if (resultSet == 1) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
