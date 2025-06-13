CREATE TABLE Promote_User (
    Promote_user_id	INT IDENTITY(1,1) PRIMARY KEY NOT NULL,       
    Promote_id		INT NOT NULL, 		     
    User_id			INT NOT NULL,
	Gift_to_user_id	INT ,
    Status			NVARCHAR(20) DEFAULT '�i�ϥ�',
	CHECK (Status IN ('�i�ϥ�', '�w�ϥ�', '�w�L��', '�w����')),    		     
    Use_date		DATETIME ,			     
    Assigned_date	DATETIME DEFAULT GETDATE(),	    
    Created_at 		DATETIME DEFAULT GETDATE(),	    

    FOREIGN KEY (User_id) REFERENCES MEM(MEMid),
    FOREIGN KEY (Promote_id) REFERENCES Promote(Promote_id),
    FOREIGN KEY (Gift_to_user_id) REFERENCES MEM(MEMid)
	);