CREATE TABLE Logistics_Tracking (
    trak_id INT IDENTITY(1,1) PRIMARY KEY,
    log_id INT NOT NULL,
    location_name NVARCHAR(20) NOT NULL,   
    status NVARCHAR(10) NOT NULL,    
    timestamp DATETIME NOT NULL DEFAULT GETDATE(), 
    sequence INT NOT NULL,  

    CONSTRAINT FK_Tracking_Logistics FOREIGN KEY (log_id) REFERENCES Logistics(log_id)
);
	select * from Logistics_Tracking;