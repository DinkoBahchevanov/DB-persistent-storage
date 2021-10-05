package com.example.persistancestorage.service;

import com.example.persistancestorage.exceptions.ProductNotFoundException;
import com.example.persistancestorage.models.Product;
import com.example.persistancestorage.web.dtos.ProductDto;
import com.example.persistancestorage.web.dtos.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    static final String DB_URL = "jdbc:h2:mem:memDb";
    static final String USER = "sa";
    static final String PASS = "";
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
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

            String query = "INSERT INTO products (`id`, `value`) VALUES (?,?)";

            PreparedStatement baba = conn.prepareStatement(query);
            baba.setObject(1, "Baba");
            baba.setObject(2, "Meca", Types.OTHER);
            baba.executeUpdate();

            PreparedStatement kumcho = conn.prepareStatement(query);
            kumcho.setObject(1, "Kumcho");
            kumcho.setObject(2, 3, Types.OTHER);
            kumcho.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProductDto put(ProductDto productDto) {
        String query = "INSERT INTO products (`id`, `value`) VALUES (?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, productDto.getId());
            stmt.setObject(2, productDto.getValue(), Types.OTHER);
            stmt.executeUpdate();
            return productDto;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ProductDto get(String key) {
        String query = "SELECT * FROM products WHERE products.id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            Object object = rs.getObject(2);
            String id = rs.getString(1);
            Product product = new Product(id, object);

            rs.close();
            pstmt.close();
            return productMapper.productToProductGetDto(product);
        } catch (SQLException throwables) {
            throw new ProductNotFoundException(String.format("Product with id: %s NOT found in the DB", key));
        }
    }

    @Override
    public List<ProductDto> get() {
        String query = "SELECT * FROM products";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            List<ProductDto> productGetDtos = new ArrayList<>();
            while (rs.next()) {
                Object object = rs.getObject(2);
                String id = rs.getString(1);
                Product product = new Product(id, object);
                productGetDtos.add(productMapper.productToProductGetDto(product));
            }

            rs.close();
            pstmt.close();
            return (productGetDtos);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

//    public String get() {
//        String query = "SELECT * FROM products";
//
//        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, key);
//            ResultSet rs = pstmt.executeQuery();
//            rs.next();
//            Object object = rs.getObject(1);
//
//            rs.close();
//            pstmt.close();
//            return object.toString();
//        }
//        catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return null;
//    }

    public boolean contains(String key) {
        String query = "SELECT * FROM products where id = '" + key + "';";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
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

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            int resultSet = stmt.executeUpdate();
            if (resultSet == 1) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
