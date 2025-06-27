CREATE TABLE Logi_Tracking (
	trak_id INT IDENTITY(1,1) PRIMARY KEY,
	logi_id INT NOT NULL,
	sequence INT NOT NULL, 
	location_name NVARCHAR(50),
	status NVARCHAR(10),
	timestamp DATETIME DEFAULT GETDATE(),

	FOREIGN KEY (logi_id) REFERENCES Logi(logi_id)
);
select * from Logi_Tracking;