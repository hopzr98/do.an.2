package dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AdminProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Kết nối cơ sở dữ liệu
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/favorite", "root", "password");

            // Truy vấn dữ liệu từ bảng "product"
            String sql = "SELECT id, productName, unitPrice FROM product";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Tạo danh sách sản phẩm
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("productName");
                double unitPrice = resultSet.getDouble("unitPrice");
                Product product = new Product(id, productName, unitPrice);
                products.add(product);
            }

            // Truyền danh sách sản phẩm đến trang JSP
            request.setAttribute("products", products);
            request.getRequestDispatcher("admin-product.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi kết nối cơ sở dữ liệu");
        }
    }
}