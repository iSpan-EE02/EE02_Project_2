CREATE TABLE couponUser (
    couponUserID	INT IDENTITY(1,1) PRIMARY KEY NOT NULL,       
    couponID		INT NOT NULL, 		     
    userID			INT NOT NULL,
	giftToUserID	INT ,
    status			NVARCHAR(20) DEFAULT '�i�ϥ�',
	CHECK (Status IN ('�i�ϥ�', '�w�ϥ�', '�w�L��', '�w����')),    		     
    useDate 		DATETIME ,			     
    assignedDate	DATETIME DEFAULT GETDATE(),	    
    createdAt 		DATETIME DEFAULT GETDATE(),	    

    FOREIGN KEY (userID) REFERENCES MEM(MEMid),
    FOREIGN KEY (couponID) REFERENCES coupon(couponID),
    FOREIGN KEY (giftToUserID) REFERENCES MEM(MEMid)
	);