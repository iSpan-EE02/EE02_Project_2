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

INSERT INTO Promotion(title,description,type,validityType,validityFrom,validityTo,validityDays,active,note,image)
VALUES
(N'生日禮專屬', N'專屬會員生日禮券，祝您生日快樂！', N'折價券', 'RELATIVE_DAYS', NULL, NULL, 30, 1, N'限壽星本人當月1號自動發放使用，每年限一次', 'birth.jpg'),
(N'新會員歡迎禮', N'歡迎加入會員，首購享折扣', N'滿額折扣', 'RELATIVE_DAYS', NULL, NULL, 14, 1, N'僅限新註冊會員首次下單使用', 'mem.jpg'),
(N'會員等級禮遇', N'不同會員等級，享專屬折價券', N'折價券', 'RELATIVE_DAYS', NULL, NULL, 30, 1, N'須登入會員領取，每月更新一次','formem.jpg'),
(N'天天簽到送券', N'每日簽到領折價券，天天都有好康', N'折價券', 'RELATIVE_DAYS', NULL, NULL, 3, 1, N'累積簽到天數可領更多優惠券','sign.jpg'),
(N'首購滿額折', N'首次購買滿額現折NT$100', N'滿額折扣', 'RELATIVE_DAYS', NULL, NULL, 14, 1, N'限新客首次下單使用','first.jpg'),
(N'滿件優惠（3件8折）', N'任選3件商品享8折優惠', N'滿件優惠', 'FIXED_DATE', '2025-07-01', '2025-07-31', NULL, 1, N'可混搭指定商品，無需輸入折扣碼','piece.jpg'),
(N'免運活動', N'單筆訂單滿NT$95享免運優惠', N'免運', 'FIXED_DATE', '2025-08-01', '2025-08-31', NULL, 1, N'活動期間不限次數使用','freeshop.jpg'),
(N'瘋幣10%回饋', N'消費回饋10%瘋幣', N'點數回饋', 'FIXED_DATE', '2025-06-30', '2025-07-12', NULL, 0, N'僅限指定商品、回饋可用於下次消費','count.jpg'),
(N'指定品項加碼折', N'熱門品類專屬優惠券', N'折價券', 'RELATIVE_DAYS', NULL, NULL, 14, 1, N'限分類如：3C、美妝、日用','forproduct.jpg'),
(N'905限時優惠狂灑', N'就愛自己，神券狂撒天天搶', N'組合活動', 'FIXED_DATE', '2025-09-05', '2025-09-05', NULL, 0, N'包含9折、9.5折、5%等優惠券，數量有限','loveuself.jpg'),
(N'七夕浪漫一下', N'情人節浪漫放送', N'限時優惠', 'FIXED_DATE', '2025-08-29', '2025-08-29', NULL, 0, N'8/29限定，折扣限時限量','love.jpg'),
(N'教師節感恩回饋', N'為感謝師長們的辛勞，凡於教師節期間購物，即享限時折價優惠。', N'組合活動', 'FIXED_DATE', '2025-09-25', '2025-09-30', NULL, 0, N'限教師節期間使用，折價券限量發放，先搶先贏。部分商品不適用，部分品類（書籍、文具、辦公用品）再享加碼折扣。','teacher.jpg'),
(N'雙11購物節', N'年度最大購物盛典，全站限時折扣與回饋', N'限時優惠', 'FIXED_DATE', '2025-11-11', '2025-11-11', NULL, 0, N'活動僅限11/11當日，指定品項下殺5折起','1111.jpg'),
(N'聖誕狂歡月', N'聖誕老公公來了～驚喜連環送，暖心優惠過聖誕', N'限時優惠', 'FIXED_DATE', '2025-12-01', '2025-12-31', NULL, 0, N'消費滿額抽聖誕大禮包，每日簽到還能加碼獲得驚喜券','merry.jpg'),
(N'夏日涼一夏', N'炎炎夏日最適合來點清爽好物，清涼優惠等你拿', N'滿額折扣', 'FIXED_DATE', '2025-07-01', '2025-08-31', NULL,0, N'精選清涼品滿千折百，加碼消暑小禮乙份（數量有限），飲品類、冷藏食品享有專屬折扣','summer.jpg'),
(N'開學季', N'開學不憂鬱，文具3C通通幫你準備好，學習無負擔', N'組合活動', 'FIXED_DATE', '2025-08-15', '2025-09-15', NULL, 0, N'學生專屬折扣85折，憑學生證可享額外折抵$50，書包、文具類滿件折、滿額贈','school.jpg');