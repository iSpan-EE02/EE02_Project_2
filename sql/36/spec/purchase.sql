--���ʳ�
CREATE TABLE PurchaseOrders (
    Purchaseorder_ID INT IDENTITY(1,1) PRIMARY KEY,
    Supplier_ID INT NOT NULL,
    RequiredDate DATETIME,
    Status NVARCHAR(20) NOT NULL, -- �Ҧp�G'�ݳB�z', '�w����', '�w����'
    Creator_ID INT NOT NULL, -- �إߦ����ʳ���u
    Create_date DATETIME DEFAULT GETDATE(),
    Update_date DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (Supplier_ID) REFERENCES Suppliers(Supplier_ID),
	FOREIGN KEY (Creator_ID) REFERENCES EMP(EMPID)
);
--���ʩ���
CREATE TABLE Purchaseorder_details (
    Purchaseorderdetail_ID INT PRIMARY KEY IDENTITY(1,1),
    Purchaseorder_ID INT NOT NULL,
    sku_id INT NOT NULL,
    Quantity INT NOT NULL,
	Unit_price DECIMAL(10,2) NOT NULL,
	FOREIGN KEY (Purchaseorder_ID) REFERENCES Purchaseorders(Purchaseorder_ID),
    FOREIGN KEY (sku_id) REFERENCES product_skus(sku_id)
);
--������
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

