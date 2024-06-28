package controll;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.CreateProduct;
import dao.DAO;
import entity.Category;
import entity.Product;

@WebServlet("/CategoryControll")
public class CategoryControll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CategoryControll() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		 processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
		
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    response.setContentType("text/html; charset=UTF-8");
	    
	    // Xử lý tạo sản phẩm
	    String id = request.getParameter("id");
	    String name = request.getParameter("productName");
	    String categoryPath = request.getParameter("productCategoryPath");
	    String img = request.getParameter("img");
	    double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
	    double oldPrice = Double.parseDouble(request.getParameter("oldPrice"));
	    String cid = request.getParameter("cid");
	    
	    CreateProduct createProduct = new CreateProduct();
	    createProduct.createProduct(id, name, categoryPath, img, unitPrice, oldPrice, cid);
	    
	    // Tiếp tục xử lý các logic khác liên quan đến danh mục
	    String cateID = request.getParameter("id");
	    
	    DAO dao = new DAO();
	    List<Product> list = dao.getProductByCateID(cateID);
	    List<Category> listC = dao.getCategory();
	    List<Product> top = dao.getTop1();
	    
	    
	    
	    request.setAttribute("listP", list);
	    request.setAttribute("Top1", top);
	    request.setAttribute("ListC", listC);
	    request.setAttribute("tag", cateID);  
	    request.getRequestDispatcher("admin-product.jsp").forward(request, response);
	    
	    System.out.println("List product: " + list);
	    
	}
}