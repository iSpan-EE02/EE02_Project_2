CREATE Table Promote(
	Promote_id int identity(1,1) primary key not null,
	Code  nvarchar(50) UNIQUE,
	Title nvarChar(20) not null,
	Description nvarchar(200),
	Discount_type varchar(20) not null 
	CHECK (Discount_type IN ('FIXED', 'PERCENT', 'FREE_SHIPPING', 'REWARD')),                
	Discount_value Decimal(10,2) not null,
	Max_discount_amount Decimal(10,2),
	Min_order_amount Decimal(10,2),
	Quantity int DEFAULT 1,
	Used_count int DEFAULT 0,
	One_time_use BIT DEFAULT 0,
	Auto_assigned BIT DEFAULT 0,
	Active BIT DEFAULT 1,
	Date_start DATETIME DEFAULT GETDATE() , 
	Date_end   DATETIME,
	Note	   nvarchar(200),
	Created_at DATETIME DEFAULT GETDATE(),
	Updated_at DATETIME DEFAULT GETDATE(),
	);
	GO

-- 建立觸發器：每次更新該表時，自動更新 Updated_at
CREATE TRIGGER trg_Promote_UpdateTimestamp
ON Promote
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    
    UPDATE Promote
    SET Updated_at = GETDATE()
    FROM Promote p INNER JOIN inserted i ON p.promote_id = i.promote_id;
END;
GO