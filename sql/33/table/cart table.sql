CREATE table cart(
	order_id int identity(1,1) primary key not null,
	order_status varchar,
	order_date datetime not null,
	mem_id int,
	total_price DECIMAL(10,2),
	);

	select * from cart;
	TRUNCATE TABLE cart;


CREATE table order_detial(
	detail_id int identity(1,1) primary key not null,
	order_id int,
	prod_id int,
	prod_name varchar,
	prod_price DECIMAL(10,2),
	quantity int,
	subtotal_price DECIMAL(10,2),
	);

	select * from order_detial;
	TRUNCATE TABLE order_detial;
