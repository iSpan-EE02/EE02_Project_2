--採購單
CREATE TABLE PurchaseOrders (
    Purchaseorder_ID INT IDENTITY(1,1) PRIMARY KEY,
    Supplier_ID INT NOT NULL,
    RequiredDate DATETIME,
    Status NVARCHAR(20) NOT NULL, -- 例如：'待處理', '已完成', '已取消'
    Creator_ID INT NOT NULL, -- 建立此採購單員工
    Create_date DATETIME DEFAULT GETDATE(),
    Update_date DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (Supplier_ID) REFERENCES Suppliers(Supplier_ID),
	FOREIGN KEY (Creator_ID) REFERENCES EMP(EMPID)
);
--採購明細
CREATE TABLE Purchaseorder_details (
    Purchaseorderdetail_ID INT PRIMARY KEY IDENTITY(1,1),
    Purchaseorder_ID INT NOT NULL,
    sku_id INT NOT NULL,
    Quantity INT NOT NULL,
	Unit_price DECIMAL(10,2) NOT NULL,
	FOREIGN KEY (Purchaseorder_ID) REFERENCES Purchaseorders(Purchaseorder_ID),
    FOREIGN KEY (sku_id) REFERENCES product_skus(sku_id)
);
--供應商
CREATE TABLE Suppliers (
    Supplier_ID INT IDENTITY(1,1) PRIMARY KEY,
    Supplier_Name NVARCHAR(20) NOT NULL,
    Contact_Person NVARCHAR(20),
    Contact_Email NVARCHAR(100),
    Contact_Phone NVARCHAR(20),
    Create_date DATETIME DEFAULT GETDATE(),
    Update_date DATETIME DEFAULT GETDATE()
);

CREATE TABLE Supplierproducts (
    Supplierproduct_ID INT PRIMARY KEY IDENTITY(1,1),
    Supplier_ID INT NOT NULL,
    sku_id INT NOT NULL,
    Unit_price DECIMAL(10,2) NOT NULL,     
    FOREIGN KEY (Supplier_ID) REFERENCES Suppliers(Supplier_ID),
    FOREIGN KEY (sku_id) REFERENCES product_skus(sku_id)
);

