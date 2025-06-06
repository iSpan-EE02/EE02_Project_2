CREATE TABLE fundraising_campaigns (
    campaign_id INT PRIMARY KEY IDENTITY(1,1), -- PK: �Ҹꬡ�ʽs��
    
    name VARCHAR(200) NOT NULL,         -- ���ʦW��
    category VARCHAR(100) NOT NULL,     -- ���O
    goal_amount DECIMAL(10, 2) NOT NULL,-- �ؼЪ��B
    current_amount DECIMAL(10, 2) DEFAULT 0, -- �ثe���B
    start_date DATE NOT NULL,           
    end_date DATE NOT NULL,             
    cover_image VARCHAR(255),           
    status VARCHAR(20) DEFAULT '��Z', -- ��Z�B�i�椤�B���\�B���ѡB�w����

    creator_id INT NOT NULL, -- FK: ���� members.member_id

   -- FOREIGN KEY (creator_id) REFERENCES members(member_id)
);


CREATE TABLE funding_orders (
    order_id INT PRIMARY KEY IDENTITY(1,1), -- PK: �q��s��

    campaign_id INT NOT NULL,               -- FK: ���ݶҸꬡ��
    member_id INT NOT NULL,                 -- FK: �٧U�H�|��ID

    amount DECIMAL(10,2) NOT NULL,          -- �٧U���B
    order_date DATETIME DEFAULT GETDATE(),  -- �q��ɶ�

    payment_status VARCHAR(20) DEFAULT '���I��', -- �I�ڪ��A�G���I�ڡB�w�I�ڡB�h��

   -- FOREIGN KEY (campaign_id) REFERENCES fundraising_campaigns(campaign_id),
    --FOREIGN KEY (member_id) REFERENCES members(member_id)
);