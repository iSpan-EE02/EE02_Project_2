CREATE table cart(
	order_id int identity(1,1) primary key not null,
	order_status varchar,
	order_date datetime,
	order_count int,
	order_price int,
	prod_id int,
	prod_name varchar(20),
	prod_price DECIMAL(10,2),
	prod_img_url varchar(2083),
	mem_id int,
	mem_name varchar(20),
	mem_pn varchar(20),
	mem_adr varchar(50),
	mem_mail varchar(50)
	);

	select * from cart;

	TRUNCATE TABLE cart;