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
                    "value OTHER NOT NULL," +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(dropTable);
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void put(String key, Object value) {
        String query = "INSERT INTO products (`id`, `value`) VALUES (?,?)";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, key);
            stmt.setObject(2, value, Types.OTHER);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String get(String key) {
        String query = "SELECT value FROM products WHERE products.id = ?";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            Object object = rs.getObject(1);

            rs.close();
            pstmt.close();
            return object.toString();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
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
