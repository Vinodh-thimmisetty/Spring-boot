

INSERT INTO CUSTOMER(CUST_ID,FIRST_NAME,LAST_NAME,STREET,APPT,CITY,ZIP_CODE,EMAIL,LAST_UPDATED_TIME) 
	VALUES (100,'Jupiter','Jone','10 Downing Street','123','London','12345', 'j@nobody.com', current_timestamp);


INSERT INTO CUSTOMER(CUST_ID,FIRST_NAME,LAST_NAME,STREET,APPT,CITY,ZIP_CODE,EMAIL,LAST_UPDATED_TIME) 
	VALUES (110,'Mike','Peterson','20 Downing Street','456','New York','12345', 'm@nobody.com' ,current_timestamp);

INSERT INTO PRODUCT(PROD_ID,PROD_NAME,PROD_DESC,REGULAR_PRICE,LAST_UPDATED_TIME) 
	VALUES (2000,'A','Product A',60, current_timestamp);

INSERT INTO PRODUCT(PROD_ID,PROD_NAME,PROD_DESC,REGULAR_PRICE,LAST_UPDATED_TIME) 
	VALUES (2010,'B','Product B',30, current_timestamp);

INSERT INTO PRODUCT(PROD_ID,PROD_NAME,PROD_DESC,REGULAR_PRICE,LAST_UPDATED_TIME) 
	VALUES (2020,'C','Product C',40, current_timestamp);

INSERT INTO PRODUCT(PROD_ID,PROD_NAME,PROD_DESC,REGULAR_PRICE,LAST_UPDATED_TIME) 
	VALUES (2030,'D','Product D',50, current_timestamp);

INSERT INTO PRODUCT(PROD_ID,PROD_NAME,PROD_DESC,REGULAR_PRICE,LAST_UPDATED_TIME) 
	VALUES (2040,'E','Product E',70, current_timestamp);


INSERT INTO ORDERS(ORDER_ID,CUST_ID,TOTAL_PRICE,OREDER_DESC,ORDER_DATE,LAST_UPDATED_TIME) 
	VALUES (111,100,100,'Internet and phone',current_timestamp,current_timestamp);

INSERT INTO ORDERS(ORDER_ID,CUST_ID,TOTAL_PRICE,OREDER_DESC,ORDER_DATE,LAST_UPDATED_TIME) 
	VALUES (222,100,15,'Cable at discounted price',current_timestamp,current_timestamp);


INSERT INTO ORDERS(ORDER_ID,CUST_ID,TOTAL_PRICE,OREDER_DESC,ORDER_DATE,LAST_UPDATED_TIME) 
	VALUES (333,110,99,'3 in one offer',current_timestamp,current_timestamp);


INSERT INTO ORDER_DETAIL(ORDER_ID,PROD_ID,PRICE,LAST_UPDATED_TIME) 
	VALUES (111,2000,60,current_timestamp);

INSERT INTO ORDER_DETAIL(ORDER_ID,PROD_ID,PRICE,LAST_UPDATED_TIME) 
	VALUES (111,2020,40,current_timestamp);

INSERT INTO ORDER_DETAIL(ORDER_ID,PROD_ID,PRICE,LAST_UPDATED_TIME) 
	VALUES (222,2010,15,current_timestamp);

INSERT INTO ORDER_DETAIL(ORDER_ID,PROD_ID,PRICE,LAST_UPDATED_TIME) 
	VALUES (333,2000,33,current_timestamp);

INSERT INTO ORDER_DETAIL(ORDER_ID,PROD_ID,PRICE,LAST_UPDATED_TIME) 
	VALUES (333,2010,33,current_timestamp);

INSERT INTO ORDER_DETAIL(ORDER_ID,PROD_ID,PRICE,LAST_UPDATED_TIME) 
	VALUES (333,2020,33,current_timestamp);

INSERT INTO ORDER_INVOICE(INVOICE_ID,ORDER_ID,DATE_RAISED,AMOUNT_DUE,DATE_SETTLED,DATE_CANCELLED,LAST_UPDATED_TIME) 
	VALUES (1,111,current_timestamp,100,current_timestamp,null,current_timestamp);


INSERT INTO ORDER_INVOICE(INVOICE_ID,ORDER_ID,DATE_RAISED,AMOUNT_DUE,DATE_SETTLED,DATE_CANCELLED,LAST_UPDATED_TIME) 
	VALUES (2,222,current_timestamp,15,current_timestamp,current_timestamp,current_timestamp);


INSERT INTO ORDER_INVOICE(INVOICE_ID,ORDER_ID,DATE_RAISED,AMOUNT_DUE,DATE_SETTLED,DATE_CANCELLED,LAST_UPDATED_TIME) 
	VALUES (3,333,current_timestamp,99,current_timestamp,null,current_timestamp);