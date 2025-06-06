CREATE TABLE fundraising_campaigns (
    campaign_id INT PRIMARY KEY IDENTITY(1,1), -- PK: 募資活動編號
    
    name VARCHAR(200) NOT NULL,         -- 活動名稱
    category VARCHAR(100) NOT NULL,     -- 類別
    goal_amount DECIMAL(10, 2) NOT NULL,-- 目標金額
    current_amount DECIMAL(10, 2) DEFAULT 0, -- 目前金額
    start_date DATE NOT NULL,           
    end_date DATE NOT NULL,             
    cover_image VARCHAR(255),           
    status VARCHAR(20) DEFAULT '草稿', -- 草稿、進行中、成功、失敗、已結束

    creator_id INT NOT NULL, -- FK: 對應 members.member_id

   -- FOREIGN KEY (creator_id) REFERENCES members(member_id)
);


CREATE TABLE funding_orders (
    order_id INT PRIMARY KEY IDENTITY(1,1), -- PK: 訂單編號

    campaign_id INT NOT NULL,               -- FK: 所屬募資活動
    member_id INT NOT NULL,                 -- FK: 贊助人會員ID

    amount DECIMAL(10,2) NOT NULL,          -- 贊助金額
    order_date DATETIME DEFAULT GETDATE(),  -- 訂單時間

    payment_status VARCHAR(20) DEFAULT '未付款', -- 付款狀態：未付款、已付款、退款

   -- FOREIGN KEY (campaign_id) REFERENCES fundraising_campaigns(campaign_id),
    --FOREIGN KEY (member_id) REFERENCES members(member_id)
);