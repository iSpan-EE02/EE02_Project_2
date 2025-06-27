-- 司機
CREATE TABLE Logi_EMP (
    driver_id INT PRIMARY KEY,
    driver_name NVARCHAR(50) NOT NULL,
    driver_phone VARCHAR(20) NOT NULL
    -- license_type NVARCHAR(20)  -- 預留：駕照類型（期末使用）
);

-- 訂單
CREATE TABLE Logi_Os (
    order_id INT PRIMARY KEY,
    order_date DATETIME NOT NULL,
    rec_name NVARCHAR(50) NOT NULL,
    rec_adr NVARCHAR(100) NOT NULL,
    rec_tel VARCHAR(20) NOT NULL,
    sen_name NVARCHAR(50) NOT NULL,
    sen_adr NVARCHAR(100) NOT NULL,
    created_at DATETIME DEFAULT GETDATE() -- 訂單產生時間
);

-- 物流表
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

-- 多筆訂單
CREATE TABLE Logi_Order (
    logi_id INT NOT NULL,
    order_id INT NOT NULL,
    PRIMARY KEY (logi_id, order_id),

    FOREIGN KEY (logi_id) REFERENCES Logi(logi_id),
    FOREIGN KEY (order_id) REFERENCES Logi_Os(order_id)
);

-- 節點
CREATE TABLE Logi_Tracking (
	trak_id INT IDENTITY(1,1) PRIMARY KEY,
	logi_id INT NOT NULL,
	sequence INT NOT NULL, 
	location_name NVARCHAR(50),
	status NVARCHAR(10),
	timestamp DATETIME DEFAULT GETDATE(),

	FOREIGN KEY (logi_id) REFERENCES Logi(logi_id)
);
