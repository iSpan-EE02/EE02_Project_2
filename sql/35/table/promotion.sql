CREATE Table Promotion(
	promotionID				INT identity(1,1) primary key not null,
	title					nvarchar(20) not null,
	description				nvarchar(200),
	type					nvarchar(20) not null 
	CHECK (type IN ('滿額折扣', '滿件優惠', '折價券', '限時優惠',
	'免運','點數回饋','組合活動')),                
	validityType			varchar(20)
	CHECK (validityType IN('FIXED_DATE', 'RELATIVE_DAYS')), 
	validityFrom			DATETIME,
	validityTo				DATETIME,
	validityDays			INT,
	active					BIT DEFAULT 1,
	createdAt				DATETIME DEFAULT GETDATE(),
	updatedAt				DATETIME DEFAULT GETDATE(),	
	note					nvarchar(200),
	image					nvarchar(255),
	);

-- 建立觸發器：每次更新該表時，自動更新 Updated_at
CREATE TRIGGER trg_Promotion_UpdateTimestamp
ON Promotion
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    
    UPDATE Promotion
    SET updatedAt = GETDATE()
    FROM Promotion p INNER JOIN inserted i ON p.promotionID = i.promotionID;
END;
GO

select * 
from Promotion;

ALTER TABLE Promotion ADD imagePath NVARCHAR(255);
EXEC sp_rename 'Promotion.imagePath' , 'image', 'COLUMN';
DROP TABLE Promotion;