<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Sản Phẩm</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1>Thêm Sản Phẩm</h1>
    <form action="category" method="post">
    <label for="id">ID:</label>
    <input type="text" id="id" name="id" required>

    <label for="productName">Product Name:</label>
    <input type="text" id="productName" name="productName" required>

    <label for="productCategoryPath">Product Category Path:</label>
    <input type="text" id="productCategoryPath" name="productCategoryPath" required>

    <label for="img">Image:</label>
    <input type="text" id="img" name="img" required>

    <label for="unitPrice">Unit Price:</label>
    <input type="number" id="unitPrice" name="unitPrice" step="0.01" required>

    <label for="oldPrice">Old Price:</label>
    <input type="number" id="oldPrice" name="oldPrice" step="0.01" required>

    <label for="cid">Category ID:</label>
    <input type="text" id="cid" name="cid" required>

    <button type="submit">Add Product</button>
</form>

    <script>
        document.getElementById('add-product-form').addEventListener('submit', (event) => {
            event.preventDefault(); // Ngăn chặn form submit mặc định

            // Lấy thông tin sản phẩm từ form
            const productData = {
                id: document.getElementById('id').value,
                name: document.getElementById('name').value,
                description: document.getElementById('description').value,
                price: parseFloat(document.getElementById('price').value)
            };

            // Gửi yêu cầu AJAX lên server
            fetch('/CreateProduct', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(productData)
            })
            .then(response => response.json())
            .then(data => {
                // Cập nhật lại danh sách sản phẩm trên trang admin.jsp
                updateProductList(data);
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });

        function updateProductList(newProduct) {
            // Code để cập nhật danh sách sản phẩm trên trang admin.jsp
            // Ví dụ: Thêm một dòng mới vào bảng
            const productTableBody = window.parent.document.querySelector('#product-table tbody');
            const newRow = productTableBody.insertRow();
            newRow.insertCell().textContent = newProduct.id;
            newRow.insertCell().textContent = newProduct.name;
            newRow.insertCell().textContent = newProduct.description;
            newRow.insertCell().textContent = newProduct.price.toFixed(2);
        }
    </script>
</body>
</html>