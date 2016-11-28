const upimage = () => {
    var file_data = $("#inputProductImage").prop("files")[0];   // Getting the properties of file from file field
    var form_data = new FormData();                  // Creating object of FormData class
    form_data.append("file", file_data);            // Appending parameter named file with properties of file_field to form_data
    $.ajax({
        url: "productImage",
        dataType: 'script',
        cache: false,
        contentType: false,
        processData: false,
        data: form_data,                         // Setting the data attribute of ajax with file_data
        type: 'POST'
    })
};
const getAllProduct = () => {
    let HTML = "";
    $("#menuList").html("");
    $.get("product?action=viewAll", data => {
        data.products.forEach(product => {
            HTML += `<div class="menuItem">
                        <div class="productImage"><img src="img/product/${product.imageUrl}"/></div>
                        <div class="productName">${product.name}</div>
                        <div class="productDescription">${product.description}</div>
                        <div class="productPrice">${product.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")} VNĐ</div>
                        <div class="productOperation">
                            <img class="removeButton" onclick="removeItem('${product.id}')" src="img/removeicon.png"/>
                            <img class="editButton" onclick="editItem('${product.id}')" src="img/editicon.png"/>
                        </div>
                    </div>`;
        });
        HTML += `<div class="menuItem">
                    <div class="addIconButton"><img id="addMenuItemButton" class="addButton" src="img/addicon.png"/></div>
                 </div>`;
        $("#menuList").html(HTML);
        modal();
    });
};
const modal = () => {
    var modal = document.getElementById('addMenuItem');

    var btn = document.getElementById('addMenuItemButton');

// Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
    btn.onclick = function () {
        modal.style.display = "block";
    };

    span.onclick = function () {
        modal.style.display = "none";
    };

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
    getAllProductType();
};
const getAllProductType = () =>{
    let HTML = "";
    $("#inputProductTypeSelector").html("");
    $.get("productType?action=getAll", data => {
        data.producttypes.forEach(producttype => {
            HTML += `<option value="${producttype.id}">${producttype.name}`;
        });

        $("#inputProductTypeSelector").html(HTML);
    });
};


getAllProduct();