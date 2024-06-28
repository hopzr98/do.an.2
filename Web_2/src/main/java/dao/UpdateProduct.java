package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import context.DBContext;
import entity.Product;

public class UpdateProduct {
    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
        
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getProductName());
            statement.setDouble(2, product.getUnitPrice());
            statement.setInt(3, product.getQuantity());
            statement.setInt(4, product.getId());
            
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Sản phẩm đã được cập nhật thành công.");
            } else {
                System.out.println("Không tìm thấy sản phẩm để cập nhật.");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
    }
}