package Logi.bean;

public class LogiEmpBean {
    private int driverId;
    private String driverName;
    private String driverPhone;

    // Constructors
    public LogiEmpBean() {}
    
    public LogiEmpBean(int driverId, String driverName, String driverPhone) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
    }

    // Getters and Setters
    public int getDriverId() { return driverId; }
    public String getDriverName() { return driverName; }
    public String getDriverPhone() { return driverPhone; }

    public void setDriverId(int driverId) { this.driverId = driverId; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
    public void setDriverPhone(String driverPhone) { this.driverPhone = driverPhone; }
}