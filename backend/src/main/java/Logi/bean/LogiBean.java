package Logi.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class LogiBean {
    private int logiId;
    private int driverId;
    private Timestamp estimatedArrival;
    private Timestamp actualArrival;
    private BigDecimal totalFee;
    private BigDecimal totalDistance;
    private Timestamp createdAt;

    // Constructors
    public LogiBean() {}
    
    public LogiBean(int driverId, Timestamp estimatedArrival, BigDecimal totalFee, BigDecimal totalDistance) {
        this.driverId = driverId;
        this.estimatedArrival = estimatedArrival;
        this.totalFee = totalFee;
        this.totalDistance = totalDistance;
    }

    // Getters and Setters
    public int getLogiId() { return logiId; }
    public int getDriverId() { return driverId; }
    public Timestamp getEstimatedArrival() { return estimatedArrival; }
    public Timestamp getActualArrival() { return actualArrival; }
    public BigDecimal getTotalFee() { return totalFee; }
    public BigDecimal getTotalDistance() { return totalDistance; }
    public Timestamp getCreatedAt() { return createdAt; }

    public void setLogiId(int logiId) { this.logiId = logiId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }
    public void setEstimatedArrival(Timestamp estimatedArrival) { this.estimatedArrival = estimatedArrival; }
    public void setActualArrival(Timestamp actualArrival) { this.actualArrival = actualArrival; }
    public void setTotalFee(BigDecimal totalFee) { this.totalFee = totalFee; }
    public void setTotalDistance(BigDecimal totalDistance) { this.totalDistance = totalDistance; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}