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
select * from Logi_Os;