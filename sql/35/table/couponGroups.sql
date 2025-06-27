CREATE TABLE couponGroups (
    groupsID			INT IDENTITY(1,1)PRIMARY KEY NOT NULL,	     
    groupsName			NVARCHAR(100) NOT NULL,		     
    description			NVARCHAR(200)
	dateStart		    DATETIME DEFAULT GETDATE(),                 
    dateEnd				DATETIME,                                   
    active				BIT DEFAULT 1,                          
	);