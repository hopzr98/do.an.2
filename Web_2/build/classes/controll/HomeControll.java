package controll;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import dao.CreateProduct;
import dao.DAO;
import dao.ProductDAO;
import entity.Category;
import entity.Product;
import entity.User;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/Home")
public class HomeControll extends HttpServlet {
    List<Product> products = new ArrayList<>();
    List<Product> list = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public HomeControll() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isLoggedIn = checkLoginStatus(request);

        if (isLoggedIn) {
            String action = request.getParameter("action");
            if (action == null) {
                action = "list1";
            }
            switch (action) {

                default:
                    home(request, response);
                    break;
            }
        } else {
            // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
            String action = request.getParameter("action");
            if (action == null) {
                action = "list1";
            }
            switch (action) {
                case "customer":
                    customer(request, response);
                    break;
                default:
                    home(request, response);
                    break;
            }
        }
    }

    private void customer(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void home(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private boolean checkLoginStatus(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lưu thông tin sản phẩm mới
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));

        // Tạo một đối tượng sản phẩm mới
        Product newProduct = new Product(0, name, description, price);

        // Sử dụng phương thức createProduct của DAO để thêm sản phẩm mới
        DAO productDAO = new DAO();
        int newProductId = productDAO.createProduct(newProduct);

        // Lấy danh sách sản phẩm mới sau khi thêm
        List<Product> updatedProducts = productDAO.getProducts();

        // Chuyển danh sách sản phẩm về dạng JSON
        Gson gson = new Gson();
        String productsJson = gson.toJson(updatedProducts);

        // Trả về dữ liệu JSON về trang add-product.jsp
        response.setContentType("application/json");
        response.getWriter().write(productsJson);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean authenticated = authenticate(username, password);

        if (authenticated) {
            // Lưu thông tin người dùng vào session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("isLoggedIn", true);

            // Lấy giá trị id từ cơ sở dữ liệu dựa trên username và lưu vào session
            DAO userDAO = new DAO();
            String id = userDAO.getUserIdByUsername(username);
            session.setAttribute("id", id);
        }
    }

	private boolean authenticate(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
}