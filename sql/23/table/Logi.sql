CREATE TABLE Logi (
    logi_id INT PRIMARY KEY IDENTITY(1,1),
    driver_id INT NOT NULL,
    estimated_arrival DATETIME,
    actual_arrival DATETIME,
    total_fee DECIMAL(10,2),
    total_distance DECIMAL(10,2),
    created_at DATETIME DEFAULT GETDATE(), -- 指派司機、物流單生成的時間（預設訂單時間 + X 小時）
    -- updated_at DATETIME DEFAULT GETDATE(),

    FOREIGN KEY (driver_id) REFERENCES Logi_EMP(driver_id)
);
select * from Logi;