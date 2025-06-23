CREATE TABLE couponUser (
    couponUserID	INT IDENTITY(1,1) PRIMARY KEY NOT NULL,       
    couponID		INT NOT NULL, 		     
    userID			INT NOT NULL,
	giftToUserID	INT ,
    status			NVARCHAR(20) DEFAULT '可使用',
	CHECK (Status IN ('可使用', '已使用', '已過期', '已轉贈')),    		     
    useDate 		DATETIME ,			     
    assignedDate	DATETIME DEFAULT GETDATE(),	    
    createdAt 		DATETIME DEFAULT GETDATE(),	    

    FOREIGN KEY (userID) REFERENCES MEM(MEMid),
    FOREIGN KEY (couponID) REFERENCES coupon(couponID),
    FOREIGN KEY (giftToUserID) REFERENCES MEM(MEMid)
	);