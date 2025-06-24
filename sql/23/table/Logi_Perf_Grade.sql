CREATE TABLE Logi_Perf_Grade (
    grade_id INT PRIMARY KEY IDENTITY(1,1),
    grade_name NVARCHAR(10) NOT NULL,
    min_score DECIMAL(5,2) NOT NULL, 
    max_score DECIMAL(5,2) NOT NULL, 
    description NVARCHAR(100)  
);
-- select * from Logi_Perf_Grade;