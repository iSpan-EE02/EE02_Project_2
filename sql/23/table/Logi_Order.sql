CREATE TABLE Logi_Order (
    logi_id INT NOT NULL,
    order_id INT NOT NULL,
    PRIMARY KEY (logi_id, order_id),

    FOREIGN KEY (logi_id) REFERENCES Logi(logi_id),
    FOREIGN KEY (order_id) REFERENCES Logi_Os(order_id)

);
select * from Logi_Order;