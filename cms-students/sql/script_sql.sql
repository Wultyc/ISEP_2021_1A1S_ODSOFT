DROP TABLE BATCH;
DROP TABLE CONTACT;
DROP TABLE PRODUCT;
DROP TABLE SHIPPING_LOCATION;
DROP TABLE WAREHOUSE;

CREATE TABLE BATCH (
    id int auto_increment,
	name varchar(60),
	description varchar(255),
	mandDate DATE,
	wareId int,
    PRIMARY KEY (id),
	FOREIGN KEY (wareId) REFERENCES WAREHOUSE(id)
                   );
CREATE TABLE CONTACT (
    id int auto_increment,
firstName varchar(40),
lastName varchar(40),
emailAddress varchar(255),
    PRIMARY KEY (id)
                     );
CREATE TABLE PRODUCT (
    id int auto_increment,
name varchar (100),
description varchar(255),
price varchar(20),
batchId int,
    PRIMARY KEY (id),
	FOREIGN KEY (batchId) REFERENCES BATCH(id)

                     );
CREATE TABLE SHIPPING_LOCATION (
    id int auto_increment,
name varchar(100),
    PRIMARY KEY (id)
                               );
CREATE TABLE WAREHOUSE (
    id int auto_increment,
name varchar(100),
totalCapacity int,
    PRIMARY KEY (id)
                       );
					   
CREATE TABLE WAREHOUSE_LOCATION(
id int,
warehouseId int,
locationId int,
	FOREIGN KEY (warehouseId) REFERENCES WAREHOUSE(id),
	FOREIGN KEY (locationId) REFERENCES SHIPPING_LOCATION(id),
	PRIMARY KEY (id)
);