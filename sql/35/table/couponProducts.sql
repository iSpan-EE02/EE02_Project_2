CREATE TABLE couponProducts(
couponID 	INT NOT NULL ,
productCateID	INT NOT NULL ,
PRIMARY KEY (couponID, productCateID),
FOREIGN KEY (couponID) REFERENCES coupon(couponID),
FOREIGN KEY (productCateID) REFERENCES PRODUCT(PROD_CATE_ID)
	);