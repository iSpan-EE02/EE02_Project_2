package Logi.bean;

import java.sql.Timestamp;

public class LogiOsBean {
    private int orderId;
    private Timestamp orderDate;
    private String recName;
    private String recAdr;
    private String recTel;
    private String senName;
    private String senAdr;
    private Timestamp createdAt;

    // Constructors
    public LogiOsBean() {}
    
    public LogiOsBean(int orderId, Timestamp orderDate, String recName, String recAdr, String recTel, String senName, String senAdr) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.recName = recName;
        this.recAdr = recAdr;
        this.recTel = recTel;
        this.senName = senName;
        this.senAdr = senAdr;
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public Timestamp getOrderDate() { return orderDate; }
    public String getRecName() { return recName; }
    public String getRecAdr() { return recAdr; }
    public String getRecTel() { return recTel; }
    public String getSenName() { return senName; }
    public String getSenAdr() { return senAdr; }
    public Timestamp getCreatedAt() { return createdAt; }

    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }
    public void setRecName(String recName) { this.recName = recName; }
    public void setRecAdr(String recAdr) { this.recAdr = recAdr; }
    public void setRecTel(String recTel) { this.recTel = recTel; }
    public void setSenName(String senName) { this.senName = senName; }
    public void setSenAdr(String senAdr) { this.senAdr = senAdr; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}