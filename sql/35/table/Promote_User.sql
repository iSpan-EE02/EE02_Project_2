CREATE TABLE Promote_User (
    Promote_user_id	INT IDENTITY(1,1) PRIMARY KEY NOT NULL,       
    Promote_id		INT NOT NULL, 		     
    User_id			INT NOT NULL,
	Gift_to_user_id	INT ,
    Status			NVARCHAR(20) DEFAULT '可使用',
	CHECK (Status IN ('可使用', '已使用', '已過期', '已轉贈')),    		     
    Use_date		DATETIME ,			     
    Assigned_date	DATETIME DEFAULT GETDATE(),	    
    Created_at 		DATETIME DEFAULT GETDATE(),	    

    FOREIGN KEY (User_id) REFERENCES MEM(MEMid),
    FOREIGN KEY (Promote_id) REFERENCES Promote(Promote_id),
    FOREIGN KEY (Gift_to_user_id) REFERENCES MEM(MEMid)
	);