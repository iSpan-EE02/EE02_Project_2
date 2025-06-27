CREATE TABLE Logi_Perf (
    perf_id INT PRIMARY KEY IDENTITY(1,1),
    driver_id INT NOT NULL,
    logi_id INT NOT NULL,

    delivery_count INT NOT NULL,           -- 配送筆數（該物流單內的訂單數）
    total_distance DECIMAL(10,2) NOT NULL, 
    total_fee DECIMAL(10,2) NOT NULL,
    delay_count INT NOT NULL,

    performance_score DECIMAL(5,2),
    grade_id INT, 

    created_at DATETIME DEFAULT GETDATE(),
    
    FOREIGN KEY (driver_id) REFERENCES Logi_EMP(driver_id),
    FOREIGN KEY (logi_id) REFERENCES Logi(logi_id),
    FOREIGN KEY (grade_id) REFERENCES Logi_Perf_Grade(grade_id)
);
-- select * from Logistics_Performance;