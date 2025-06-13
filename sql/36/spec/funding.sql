CREATE TABLE fundraising_campaigns (
    campaign_id INT PRIMARY KEY IDENTITY(1,1), --  �Ҹꬡ�ʽs��
    campaign_name VARCHAR(200) NOT NULL,         -- ���ʦW��
    campaign_category VARCHAR(100) NOT NULL,     -- ���O
    goal_amount DECIMAL(10,2) NOT NULL,-- �ؼЪ��B
    current_amount DECIMAL(10,2) DEFAULT 0, -- �ثe���B
    start_date DATE NOT NULL,           
    end_date DATE NOT NULL,             
    cover_image VARCHAR(255),           
    status VARCHAR(20) DEFAULT '��Z', -- ��Z�B�i�椤�B���\�B���ѡB�w����
	creator_id INT NOT NULL, -- FK: ���� members.member_id
	approval_status VARCHAR(20) DEFAULT '�ݼf��',       -- �f�֪��A�]�ݼf�֡B�q�L�B���q�L�^
    description VARCHAR(100)             --�Ҹꬡ�ʴy�z
   -- FOREIGN KEY (creator_id) REFERENCES mem(memid)
);

DROP TABLE fundraising_campaigns;

CREATE TABLE funding_orders (
    order_id INT PRIMARY KEY IDENTITY(1,1), -- PK: �q��s��

    campaign_id INT NOT NULL,               -- FK: ���ݶҸꬡ��
    member_id INT NOT NULL,                 -- FK: �٧U�H�|��ID

    amount DECIMAL(10,2) NOT NULL,          -- �٧U���B
    order_date DATETIME DEFAULT GETDATE(),  -- �q��ɶ�

    payment_status VARCHAR(20) DEFAULT '���I��', -- �I�ڪ��A�G���I�ڡB�w�I�ڡB�h��

   -- FOREIGN KEY (campaign_id) REFERENCES fundraising_campaigns(campaign_id),
    --FOREIGN KEY (member_id) REFERENCES mem(memid)
);

CREATE TABLE funding_reward (
    reward_id INT PRIMARY KEY IDENTITY(1,1), -- ���y��װߤ@�ѧO�X�A�۰ʻ��W
    campaign_id INT NOT NULL,                  -- �~��A�s���� funding_campaigns
    reward_name NVARCHAR(20) NOT NULL,        -- ��צW��
    amount_required INT NOT NULL,              -- ����שһݪ��̧C�٧U���B
    reward_description NVARCHAR(100),          -- ��׸ԲӴy�z
	
	-- FOREIGN KEY (campaign_id) REFERENCES fundraising_campaigns(campaign_id),
};

CREATE TABLE funding_follow (
    follow_id INT PRIMARY KEY IDENTITY(1,1),   -- �l�ܰO��
    member_id INT NOT NULL,                    -- �~��A�s���� mem (�|��)
    campaign_id INT NOT NULL,                  -- �~��A�s���� funding_campaigns (�Ҹ�M��)
	-- FOREIGN KEY (campaign_id) REFERENCES fundraising_campaigns(campaign_id),
    --FOREIGN KEY (member_id) REFERENCES mem(memid)
};