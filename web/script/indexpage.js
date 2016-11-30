const getCurrentOrder = () => {
    let HTML = "";
    $("#orderList").html("");
    $.get("order?action=getCurrentOrder", data => {
        data.orders.forEach(order => {
            let state;
            let cssClass = "";
            switch (order.stateCode) {
                case "-1":
                    state = "Đang phục vụ";
                    break;
                case "0":
                    state = "Đang tạm tính";
                    cssClass = "stateChecking"
                    break;
            }
            HTML += `<div class="orderItem ${cssClass}">
            <div class="orderIcon"><img class="icon" src="img/order.png"/></div>
            <div class="orderNo">Bill số: ${order.no}</div>
            <div class="orderInfoContainer">
                <table>
                    <tr>
                        <td>Giờ vào:</td>
                        <td>${order.inTime}</td>
                    </tr>
                    <tr>
                        <td>Trạng thái:</td>
                        <td>${state}</td>
                    </tr>
                    <tr>
                        <td colspan="2"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><button class="button" onclick="getOrderDetail('${order.id}')">Xem chi tiết</button></td>
                    </tr>
                    <tr>
                        <td colspan="2"><button class="button">In phiếu bếp</button></td>
                    </tr>
                    <tr>
                        <td colspan="2"><button class="button">In tạm tính</button></td>
                    </tr>
                    <tr>
                        <td colspan="2"><button class="button">In bill</button></td>
                    </tr>
                </table>
            </div>
        </div>`
        });
        HTML += `<div class="orderItem">
                    <img class="addOrderItemButton" onclick="addOrderItem()" src="img/addicon.png"/>
                 </div>`;
        $("#orderList").html(HTML);
        modal();
    });
};
const getOrderDetail = (orderID) => {
    $("#orderDetail").show();
    $.get(`orderDetail?action=getOrderDetail&orderID=${orderID}`, data => {
        let HTML;
        $("#orderDetailList").html(`<tr>
                                        <td class="header">Món</td>
                                        <td class="header">Số lượng</td>
                                        <td class="header">Thành tiền</td>
                                        <td class="header" colspan="3">Thao tác</td>
                                    </tr>`);
        data.orderdetails.forEach(orderdetail => {
            HTML += `<tr>
                        <td>${orderdetail.productName}</td>
                        <td>${orderdetail.quantity}</td>
                        <td>${parseInt(orderdetail.quantity) * parseInt(orderdetail.productPrice)}</td>
                        <td>Thêm</td>
                        <td>Sửa</td>
                        <td>Xóa</td>
                    </tr>`;
        });
        $("#orderDetailList").append(HTML);
    });
};
const addOrderItem = () =>{
    let orderNo = prompt("Vui lòng nhập số bill");
    if(orderNo != null){
        $.ajax({
            async: false,
            url: `order?action=addOrderItem&orderNo=${orderNo}`,
            cache: false,
            contentType: false,
            processData: false,
            type: 'GET'
        });
        getCurrentOrder();
    }
};
const modal = () => {
    var orderDetailModal = document.getElementById('orderDetail');
    var orderDetailButton = document.getElementsByName('orderDetailButton');
// Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];
// When the user clicks on the button, open the modal
    span.onclick = function () {
        orderDetailModal.style.display = "none";
    };
    window.onclick = function (event) {
        if (event.target == orderDetailModal) {
            orderDetailModal.style.display = "none";
        }
    };
};
getCurrentOrder();

g