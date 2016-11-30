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
                        <td colspan="2"><button class="button">Xem chi tiết</button></td>
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
        $("#orderList").html(HTML);
    });
}
getCurrentOrder();