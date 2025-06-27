-- 司機
INSERT INTO Logi_EMP (driver_id, driver_name, driver_phone) VALUES
(101, N'張偉', '0912345678'),
(102, N'李明', '0923456789'),
(103, N'王芳', '0934567890');

-- 訂單
INSERT INTO Logi_Os (order_id, order_date, rec_name, rec_adr, rec_tel, sen_name, sen_adr, created_at) VALUES
(1001, '2025-06-20 09:00', N'陳怡君', N'台北市中山區南京東路一段100號', '0921234567', N'假公司', N'台北市松山區敦化南路二段100號', '2025-06-20 09:00'),
(1002, '2025-06-21 10:30', N'黃子瑜', N'高雄市鼓山區美術東二路50號', '0932345678', N'假公司', N'台北市松山區敦化南路二段100號', '2025-06-21 10:30'),
(1003, '2025-06-22 14:00', N'劉冠廷', N'台南市安平區安平路300號', '0943456789', N'假公司', N'台北市松山區敦化南路二段100號', '2025-06-22 14:00');

-- 物流表
INSERT INTO Logi (driver_id, estimated_arrival, actual_arrival, total_fee, total_distance, created_at) VALUES
(101, '2025-06-21 12:00', NULL, 600.50, 120.75, '2025-06-20 10:00'),
(102, '2025-06-22 15:00', '2025-06-22 14:45', 450.00, 80.25, '2025-06-21 11:30'),
(103, '2025-06-23 09:30', NULL, 750.25, 200.50, '2025-06-22 15:00');

-- 多筆訂單
INSERT INTO Logi_Order (logi_id, order_id) VALUES
(1, 1001),
(1, 1002),
(2, 1002),
(2, 1003),
(3, 1003);

-- 節點
INSERT INTO Logi_Tracking (logi_id, sequence, location_name, status, timestamp) VALUES
(1, 1, N'假公司倉儲', N'待配送', '2025-06-20 10:30'),
(1, 2, N'新北市物流中心', N'配送中', '2025-06-20 15:00'),
(2, 1, N'假公司倉儲', N'待配送', '2025-06-21 12:00'),
(2, 2, N'高雄市物流中心', N'配送中', '2025-06-22 14:30'),
(3, 1, N'假公司倉儲', N'待配送', '2025-06-22 15:30'),
(3, 2, N'台南市物流中心', N'配送中', '2025-06-23 08:00');


select * from Logi_Tracking; 
select * from Logi_Order; 
select * from Logi;  
select * from Logi_Os;  
select * from Logi_EMP; 

