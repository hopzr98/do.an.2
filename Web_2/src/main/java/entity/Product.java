package entity;

public class Product {
	private int id;
	private String productName;
	private String productCategoryPath;
	private String img;
	private double unitPrice;
	private double oldPrice;
	private int quantity;
	private String description;

	public Product(int id, String productName, String productCategoryPath, String img, double unitPrice, double oldPrice) {
		super();
		this.id = id;
		this.productName = productName;
		this.productCategoryPath = productCategoryPath;
		this.img = img;
		this.unitPrice = unitPrice;
		this.oldPrice = oldPrice;
	}
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product(int id, String name, String description, double price) {
	    this.id = id;
	    this.productName = name;
	    this.description = description;
	    this.oldPrice = price;
	}
	public Product(int id, String productName, double unitPrice) {
        this.id = id;
        this.productName = productName;
        this.unitPrice = unitPrice;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCategoryPath() {
		return productCategoryPath;
	}
	public void setProductCategoryPath(String productCategoryPath) {
		this.productCategoryPath = productCategoryPath;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", productCategoryPath=" + productCategoryPath
				+ ", img=" + img + ", unitPrice=" + unitPrice + ", oldPrice=" + oldPrice + "]";
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
