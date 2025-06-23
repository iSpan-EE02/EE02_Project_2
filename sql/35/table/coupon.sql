CREATE Table coupon(
	couponID				INT identity(1,1) primary key not null,
	title					nvarChar(20) not null,
	description				nvarchar(200),
	discountTtype			varchar(20) not null 
	CHECK (discountType IN ('FIXED', 'PERCENT', 'FREE_SHIPPING', 'REWARD')),                
	discountValue			Decimal(10,2) not null,
	maxDiscountAmount		Decimal(10,2),
	minOrderAmount			Decimal(10,2),
	quantity				INT DEFAULT 0,
	usedCount				INT DEFAULT 0,
	oneTimeUse				BIT DEFAULT 1,
	autoAssigned			BIT DEFAULT 0,
	active					BIT DEFAULT 1,
	validityType			ENUM ('FIXED_DATE', 'RELATIVE_DAYS'), 
	validityFrom			DATETIME,
	validityTo				DATETIME,
	validityDays			INT,
	note					nvarchar(200),
	createdAt				DATETIME DEFAULT GETDATE(),
	updatedAt				DATETIME DEFAULT GETDATE(),
	groupsID				INT,
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