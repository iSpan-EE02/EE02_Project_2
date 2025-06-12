CREATE TABLE Logistics_Extension (
    exte_id INT IDENTITY(1,1) PRIMARY KEY,
    log_id INT NOT NULL,
    estimated_arrival DATETIME,   
    actual_arrival DATETIME, 
    dynamic_fee DECIMAL(10,2), 
    
    CONSTRAINT FK_Extension_Logistics FOREIGN KEY (log_id) REFERENCES Logistics(log_id)
);
	select * from Logistics_Extension; 