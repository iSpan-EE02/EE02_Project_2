CREATE TABLE Logistics (
    log_id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    order_id INT NOT NULL,
    mem_id INT NOT NULL,
    prod_id INT NOT NULL,
    sender_name NVARCHAR(20) NOT NULL,
    sender_address NVARCHAR(50) NOT NULL,
    receiver_name NVARCHAR(20) NOT NULL,
    receiver_address NVARCHAR(50) NOT NULL,
    is_island BIT DEFAULT 0 NOT NULL,
    shipping_fee DECIMAL(10,2) NOT NULL,
    current_status NVARCHAR(10) NOT NULL,
    current_location NVARCHAR(50) NOT NULL,

	created_at DATETIME NOT NULL DEFAULT GETDATE(),
	updated_at DATETIME NOT NULL DEFAULT GETDATE(),

    CONSTRAINT FK_Logistics_cart FOREIGN KEY (order_id) REFERENCES cart(order_id),
    CONSTRAINT FK_Logistics_MEM FOREIGN KEY (mem_id) REFERENCES MEM(MEMid),
    CONSTRAINT FK_Logistics_PRODUCT FOREIGN KEY (prod_id) REFERENCES PRODUCT(PROD_ID),
);

	select * from Logistics;
