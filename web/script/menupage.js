const getAllProduct = () => {
    let HTML = "";
    $("#menuList").html("");
    $.get("product?action=viewAll", data => {
        data.products.forEach(product => {
            HTML += `<div class="menuItem">
                        <div class="productImage"><img src="img/product/${product.imageUrl}"/></div>
                        <div class="productName">${product.name}</div>
                        <div class="productPrice">${product.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")} VNĐ</div>
                        <div class="productOperation">
                            <img class="removeButton" onclick="removeItem('${product.id}')" src="img/removeicon.png"/>
                            <img class="editButton" onclick="editItem('${product.id}')" src="img/editicon.png"/>
                        </div>
                    </div>`;
        });
        HTML += `<div class="menuItem">
                    <div class="addIconButton"><img class="classButton" src="img/addicon.png"/></div>
                 </div>`;
        $("#menuList").html(HTML);
    });
};

getAllProduct();