package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import context.DBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.Product;

@WebServlet("/CreateProduct")
public class CreateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;
    public void init() {
        productDAO = new ProductDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product(id, name, description, price);
        
        int result = productDAO.insertProduct(product);
        
        if (result > 0) {
            // Xử lý khi thêm sản phẩm thành công
            response.sendRedirect("admin.jsp");
        } else {
            // Xử lý khi thêm sản phẩm thất bại
            request.setAttribute("errorMessage", "Thêm sản phẩm thất bại.");
            request.getRequestDispatcher("add-product.jsp").forward(request, response);
        }

        //response.sendRedirect("admin.jsp");
    }

    public void createProduct(String id, String name, String categoryPath, String img, double unitPrice, double oldPrice, String cid) {
        try (Connection con = DBContext.getConnection();
             PreparedStatement stmt = con.prepareStatement("INSERT INTO product (id, productName, productCategoryPath, img, unitPrice, oldPrice, cid) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, categoryPath);
            stmt.setString(4, img);
            stmt.setDouble(5, unitPrice);
            stmt.setDouble(6, oldPrice);
            stmt.setString(7, cid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}