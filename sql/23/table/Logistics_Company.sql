CREATE TABLE Logistics_Company (
    comp_id INT IDENTITY(1,1) PRIMARY KEY,
    comp_name NVARCHAR(20) NOT NULL,
);
	select * from Logistics_Company;

ALTER TABLE Logistics
ADD comp_id INT;

ALTER TABLE Logistics
ADD CONSTRAINT FK_Logistics_Company FOREIGN KEY (comp_id)
    REFERENCES Logistics_Company(comp_id);