CREATE TABLE Logi_EMP (
    driver_id INT PRIMARY KEY,
    driver_name NVARCHAR(50) NOT NULL,
    driver_phone VARCHAR(20) NOT NULL
    -- license_type NVARCHAR(20)  -- 預留：駕照類型（期末使用）
);
select * from Logi_EMP;