CREATE TABLE Promote_Groups (
    Groups_id			INT IDENTITY(1,1)PRIMARY KEY NOT NULL,	     
    Groups_name			NVARCHAR(100) NOT NULL,		     
    Description			NVARCHAR(200)
	Date_start		    DATETIME DEFAULT GETDATE(),                 
    Date_end			DATETIME,                                   
    Active				BIT DEFAULT 1,                          
	);