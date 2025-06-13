CREATE TABLE Promote_Products(
Promote_id 	INT NOT NULL ,
Product_id	INT NOT NULL ,
PRIMARY KEY (Promote_id, Product_id),
FOREIGN KEY (Promote_id) REFERENCES Promote(Promote_ID),
FOREIGN KEY (Product_id) REFERENCES PRODUCT(PROD_ID)
	);