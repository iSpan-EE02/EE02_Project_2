CREATE Table Promotion_Rule(
	ruleID					INT identity(1,1) primary key not null,
	promotionID				INT not null,
	ruleType				nvarchar(20) not null 
	CHECK (ruleType IN (N'滿額折扣', N'滿件優惠', N'指定商品優惠', N'折價券'
	,N'點數回饋', N'免運', N'限時優惠', N'限新戶',N'會員等級限定', N'自動派券')),  
	thresholdAmount			DECIMAL(10,2) ,
	discountAmount			INT ,
	itemCount				INT ,
	productCateID			INT ,
	couponID				INT ,
	pointRewardRate			DECIMAL(5,2),
	isFreeShipping			BIT DEFAULT 0 ,
	isLimitedTime			BIT DEFAULT 0 ,
	isNewUserOnly			BIT DEFAULT 0 ,
	memberTier				VARCHAR(20) ,

	FOREIGN KEY (promotionID) REFERENCES promotion(promotionID),
    FOREIGN KEY (productCateID) REFERENCES PRODUCT(PROD_CATE_ID),
    FOREIGN KEY (couponID) REFERENCES coupon(couponID),
	);