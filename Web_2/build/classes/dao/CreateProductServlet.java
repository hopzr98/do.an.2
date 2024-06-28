package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import context.DBContext;
import java.sql.Connection;

public class CreateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));

        // Thực hiện các kiểm tra và validate
        if (id == null || id.trim().isEmpty() || name == null || name.trim().isEmpty() || price <= 0) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid product data.");
            return;
        }

        try (Connection conn = DBContext.getConnection();) {
            String sql = "INSERT INTO product (id, name, description, price) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, description);
            stmt.setDouble(4, price);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Trả về thông tin sản phẩm đã thêm
                response.setContentType("application/json");
                response.getWriter().write(String.format("{\"id\":\"%s\",\"name\":\"%s\",\"description\":\"%s\",\"price\":%.2f}",
                        id, name, description, price));
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Failed to create product.");
            }
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error creating product: " + e.getMessage());
        }
    }
}