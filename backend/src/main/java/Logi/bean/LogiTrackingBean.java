package Logi.bean;

import java.sql.Timestamp;

public class LogiTrackingBean {
    private int trakId;
    private int logiId;
    private int sequence;
    private String locationName;
    private String status;
    private Timestamp timestamp;

    // Constructors
    public LogiTrackingBean() {}
    
    public LogiTrackingBean(int logiId, int sequence, String locationName, String status, Timestamp timestamp) {
        this.logiId = logiId;
        this.sequence = sequence;
        this.locationName = locationName;
        this.status = status;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public int getTrakId() { return trakId; }
    public int getLogiId() { return logiId; }
    public int getSequence() { return sequence; }
    public String getLocationName() { return locationName; }
    public String getStatus() { return status; }
    public Timestamp getTimestamp() { return timestamp; }

    public void setTrakId(int trakId) { this.trakId = trakId; }
    public void setLogiId(int logiId) { this.logiId = logiId; }
    public void setSequence(int sequence) { this.sequence = sequence; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
    public void setStatus(String status) { this.status = status; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
}