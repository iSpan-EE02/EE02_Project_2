CREATE TABLE campaigns (--期中
    campaign_id INT PRIMARY KEY IDENTITY(1,1), --  募資活動編號
    campaign_name VARCHAR(20) NOT NULL,         -- 活動名稱
    campaign_category VARCHAR(20) NOT NULL,     -- 類別
    goal_amount DECIMAL(10,2) NOT NULL,-- 目標金額
    current_amount DECIMAL(10,2) DEFAULT 0, -- 目前金額
    start_date DATE NOT NULL,           
    end_date DATE NOT NULL,             
    cover_image VARCHAR(500),         
    status VARCHAR(20) DEFAULT '草稿', -- 草稿、進行中、成功、失敗、已結束
	creator_id INT NOT NULL, -- FK: 對應 members.member_id
	
    description VARCHAR(100)             --募資活動描述
   
);
INSERT INTO campaigns (campaign_name, campaign_category, goal_amount, current_amount, start_date, end_date, status, creator_id, description)
VALUES 
('手作柴犬玩偶', '手作', 50000, 12000, '2025-06-01', '2025-07-15', '草稿', 1, '可愛又溫暖的柴犬手作玩偶，陪伴你的每一天。'),
('環保海灘清潔計畫', '環境', 100000, 75000, '2025-05-20', '2025-06-30', '進行中', 2, '結合社區與志工，清潔沿海垃圾，守護我們的海洋。'),
('獨立音樂人專輯製作', '音樂', 80000, 80000, '2025-04-10', '2025-06-10', '成功', 3, '用音樂訴說心聲，一張充滿情感的創作專輯。'),
('野地書屋移動圖書車', '教育', 120000, 25000, '2025-06-05', '2025-08-15', '草稿', 1, '為偏鄉地區送書送愛，打造閱讀無距離的書屋。'),
('遠距健康追蹤裝置', '科技', 200000, 150000, '2025-05-01', '2025-07-01', '進行中', 4, '一款可以遠端追蹤長者健康狀況的智慧裝置。'),
('智能農業感測器開發', '科技', 150000, 85000, '2025-06-01', '2025-08-31', '草稿', 2, '利用感測器與AI演算法提升農業效率。'),
('社區共食廚房計畫', '社會', 60000, 30000, '2025-05-15', '2025-07-15', '進行中', 3, '打造社區共享廚房，提供獨居長者溫暖餐食。'),
('青年職涯導師媒合平台', '教育', 100000, 20000, '2025-04-20', '2025-06-20', '草稿', 4, '建立一個青年與職涯導師配對的數位平台。'),
('永續服飾品牌起步計畫', '時尚', 90000, 45000, '2025-03-01', '2025-05-01', '已結束', 1, '以環保布料製作簡約設計服飾，響應永續生活。'),
('開放資料應用黑客松', '科技', 50000, 50000, '2025-05-01', '2025-06-01', '成功', 5, '結合開放資料與程式設計，激發社會創新應用。');
UPDATE campaigns
SET cover_image = 'uploads/default.jpg'
WHERE campaign_id between 1 and 11;

select *from campaigns
DROP TABLE campaigns;
truncate table campaigns