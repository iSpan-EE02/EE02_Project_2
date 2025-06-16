CREATE TABLE Logistics_Company (
    comp_id INT IDENTITY(1,1) PRIMARY KEY,
    comp_name NVARCHAR(20) NOT NULL,
	comp_location NVARCHAR(50) NOT NULL,
	comp_tel NVARCHAR(20) NOT NULL,
	driver_name NVARCHAR(20),
	driver_tel NVARCHAR(20)
);
	select * from Logistics_Company;

ALTER TABLE Logistics
ADD estimated_arrival DATETIME;

ALTER TABLE Logistics
ADD actual_arrival DATETIME;

ALTER TABLE Logistics
ADD comp_id INT;

ALTER TABLE Logistics
ADD CONSTRAINT FK_Logistics_Company FOREIGN KEY (comp_id)
    REFERENCES Logistics_Company(comp_id);
