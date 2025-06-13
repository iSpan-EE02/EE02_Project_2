CREATE TABLE fundraising_campaigns (
    campaign_id INT PRIMARY KEY IDENTITY(1,1), --  募資活動編號
    campaign_name VARCHAR(200) NOT NULL,         -- 活動名稱
    campaign_category VARCHAR(100) NOT NULL,     -- 類別
    goal_amount DECIMAL(10,2) NOT NULL,-- 目標金額
    current_amount DECIMAL(10,2) DEFAULT 0, -- 目前金額
    start_date DATE NOT NULL,           
    end_date DATE NOT NULL,             
    cover_image VARCHAR(255),           
    status VARCHAR(20) DEFAULT '草稿', -- 草稿、進行中、成功、失敗、已結束
	creator_id INT NOT NULL, -- FK: 對應 members.member_id
	approval_status VARCHAR(20) DEFAULT '待審核',       -- 審核狀態（待審核、通過、未通過）
    description VARCHAR(100)             --募資活動描述
   -- FOREIGN KEY (creator_id) REFERENCES mem(memid)
);

DROP TABLE fundraising_campaigns;

CREATE TABLE funding_orders (
    order_id INT PRIMARY KEY IDENTITY(1,1), -- PK: 訂單編號

    campaign_id INT NOT NULL,               -- FK: 所屬募資活動
    member_id INT NOT NULL,                 -- FK: 贊助人會員ID

    amount DECIMAL(10,2) NOT NULL,          -- 贊助金額
    order_date DATETIME DEFAULT GETDATE(),  -- 訂單時間

    payment_status VARCHAR(20) DEFAULT '未付款', -- 付款狀態：未付款、已付款、退款

   -- FOREIGN KEY (campaign_id) REFERENCES fundraising_campaigns(campaign_id),
    --FOREIGN KEY (member_id) REFERENCES mem(memid)
);

CREATE TABLE funding_reward (
    reward_id INT PRIMARY KEY IDENTITY(1,1), -- 獎勵方案唯一識別碼，自動遞增
    campaign_id INT NOT NULL,                  -- 外鍵，連結到 funding_campaigns
    reward_name NVARCHAR(20) NOT NULL,        -- 方案名稱
    amount_required INT NOT NULL,              -- 此方案所需的最低贊助金額
    reward_description NVARCHAR(100),          -- 方案詳細描述
	
	-- FOREIGN KEY (campaign_id) REFERENCES fundraising_campaigns(campaign_id),
};

CREATE TABLE funding_follow (
    follow_id INT PRIMARY KEY IDENTITY(1,1),   -- 追蹤記錄
    member_id INT NOT NULL,                    -- 外鍵，連結到 mem (會員)
    campaign_id INT NOT NULL,                  -- 外鍵，連結到 funding_campaigns (募資專案)
	-- FOREIGN KEY (campaign_id) REFERENCES fundraising_campaigns(campaign_id),
    --FOREIGN KEY (member_id) REFERENCES mem(memid)
};