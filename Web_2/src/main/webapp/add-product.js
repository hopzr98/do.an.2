$(document).ready(function() {
    $('#add-product-form').submit(function(event) {
        event.preventDefault();

        if (validateForm()) {
            var formData = {
                name: $('#product-name').val(),
                description: $('#product-description').val(),
                price: $('#product-price').val(),
            };

            $.ajax({
                type: 'POST',
                url: 'CreateProduct',
                data: formData,
                success: function(response) {
                    alert('Product added successfully!');
                    window.location.href = 'products.jsp';
                },
                error: function(xhr, status, error) {
                    alert('Error adding product: ' + xhr.responseText);
                }
            });
        }
    });
});

function validateForm() {
    var isValid = true;
    if ($('#product-name').val().trim() === '') {
        isValid = false;
        alert('Please enter a product name.');
    }
    if ($('#product-description').val().trim() === '') {
        isValid = false;
        alert('Please enter a product description.');
    }
    if ($('#product-price').val().trim() === '' || isNaN($('#product-price').val())) {
        isValid = false;
        alert('Please enter a valid product price.');
    }
    return isValid;
}

$('#save-product').click(function() {
    var name = $('#product-name').val();
    var description = $('#product-description').val();
    var price = $('#product-price').val();

    $.ajax({
        type: 'POST',
        url: 'CategoryControll',
        data: {
            name: name,
            description: description,
            price: price
        },
        success: function(data) {
            updateProductTable(data);
        }
    });
});

function updateProductTable(products) {
    var tableBody = $('#product-table tbody');
    tableBody.empty();

    $.each(products, function(index, product) {
        var row = $('<tr>');
        row.append($('<td>').text(product.name));
        row.append($('<td>').text(product.description));
        row.append($('<td>').text(product.price));
        tableBody.append(row);
    });
}

document.querySelector('form').addEventListener('submit', function(event) {
    event.preventDefault();

    const productName = document.querySelector('#product-name').value;
    const productDescription = document.querySelector('#product-description').value;
    const productPrice = document.querySelector('#product-price').value;

    const productData = {
        name: productName,
        description: productDescription,
        price: productPrice
    };

    sendProductToServer(productData);
});

function sendProductToServer(productData) {
    fetch('/api/products', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(productData)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Sản phẩm mới đã được thêm:', data);
        addProductToList(data);
    })
    .catch(error => {
        console.error('Lỗi khi thêm sản phẩm:', error);
    });
}

function addProductToList(product) {
    const list = document.querySelector('.danh-muc-item.show-item');
    const newItem = document.createElement('div');
    newItem.classList.add('danh-muc-item');
    newItem.innerHTML = `
        <div class="item-image">
            <img src="${product.imageUrl}" alt="${product.name}">
        </div>
        <div class="item-info">
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <p>Giá: ${product.price} VND</p>
        </div>
    `;
    list.appendChild(newItem);
}