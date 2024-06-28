package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.Product;

public class ProductDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/favorite";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public void createProduct(String name, String description, double price) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO products (name, description, price) VALUES (?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.executeUpdate();
        }
    }
    
    public int insertProduct(Product product) {
        String sql = "INSERT INTO products (id, name, description, price) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getOldPrice());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}