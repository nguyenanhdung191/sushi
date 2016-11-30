package Model;

import java.util.Date;

public class Order {
    int orderID;
    int orderNo;
    Date orderInTime;
    Date orderOutTime;
    int orderStateCode;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderInTime() {
        return orderInTime;
    }

    public void setOrderInTime(Date orderInTime) {
        this.orderInTime = orderInTime;
    }

    public Date getOrderOutTime() {
        return orderOutTime;
    }

    public void setOrderOutTime(Date orderOutTime) {
        this.orderOutTime = orderOutTime;
    }

    public int getOrderStateCode() {
        return orderStateCode;
    }

    public void setOrderStateCode(int orderStateCode) {
        this.orderStateCode = orderStateCode;
    }

}
