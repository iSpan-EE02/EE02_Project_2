package Logi.bean;

public class LogiOrderBean {
    private int logiId;
    private int orderId;

    // Constructors
    public LogiOrderBean() {}
    
    public LogiOrderBean(int logiId, int orderId) {
        this.logiId = logiId;
        this.orderId = orderId;
    }

    // Getters and Setters
    public int getLogiId() { return logiId; }
    public int getOrderId() { return orderId; }

    public void setLogiId(int logiId) { this.logiId = logiId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
}