CREATE TABLE Logistics (
    log_id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    order_id INT NOT NULL,
    is_island BIT DEFAULT 0 NOT NULL,
    shipping_fee DECIMAL(10,2) NOT NULL,
    current_status NVARCHAR(10) NOT NULL,
    current_location NVARCHAR(50),
	updated_at DATETIME NOT NULL DEFAULT GETDATE(),

    CONSTRAINT FK_Logistics_cart FOREIGN KEY (order_id) REFERENCES cart(order_id),

	estimated_arrival DATETIME,
	actual_arrival DATETIME,
	comp_id INT NOT NULL,

	CONSTRAINT FK_Logistics_cart FOREIGN KEY (comp_id) REFERENCES Logistics_Company(comp_id)
);
	select * from Logistics;
