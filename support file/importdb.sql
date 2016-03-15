DROP SCHEMA TRANSPONY;


CREATE SCHEMA TRANSPONY CHARACTER SET UTF8mb4 COLLATE UTF8MB4_UNICODE_CI;

use TRANSPONY;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `CAR` CASCADE
;

DROP TABLE IF EXISTS `CAR_TYPE` CASCADE
;

DROP TABLE IF EXISTS `CARGO` CASCADE
;

DROP TABLE IF EXISTS `CARGO_TYPE` CASCADE
;

DROP TABLE IF EXISTS `CHECK_POINT` CASCADE
;

DROP TABLE IF EXISTS `CHECK_POINT_TYPE` CASCADE
;

DROP TABLE IF EXISTS `DELIVERY_POINT` CASCADE
;

DROP TABLE IF EXISTS `EMPLOYEE` CASCADE
;

DROP TABLE IF EXISTS `M2M_CHECK_POINT_ROUTE` CASCADE
;

DROP TABLE IF EXISTS `M2M_RECIEVER_DELIVERY_POINT` CASCADE
;

DROP TABLE IF EXISTS `MODEL_CAR` CASCADE
;

DROP TABLE IF EXISTS `PROVIDER` CASCADE
;

DROP TABLE IF EXISTS `RECIEVER` CASCADE
;

DROP TABLE IF EXISTS `ROUTE` CASCADE
;

DROP TABLE IF EXISTS `TRIP` CASCADE
;

DROP TABLE IF EXISTS `TRIP_STATUS` CASCADE
;

DROP TABLE IF EXISTS `USER_CREDANTIALS` CASCADE
;

DROP TABLE IF EXISTS `USER_POSITION` CASCADE
;

DROP TABLE IF EXISTS `VENDOR_CAR` CASCADE
;

DROP TABLE IF EXISTS `WAYBILL` CASCADE
;

CREATE TABLE `CAR`
(
	`id_car` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`license_plate` VARCHAR(128) NOT NULL,
	`id_employee` INT UNSIGNED,
	`id_car_type` INT UNSIGNED NOT NULL,
	`id_vendor_car` INT UNSIGNED NOT NULL,
	`fuel_consumption` DECIMAL(6,3) NOT NULL,
	`id_model_car` INT UNSIGNED NOT NULL,
	CONSTRAINT `PK_CAR` PRIMARY KEY (`id_car`)
) ENGINE=INNODB
;

CREATE TABLE `CAR_TYPE`
(
	`id_car_type` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`name` VARCHAR(128) NOT NULL,
	CONSTRAINT `PK_CAR_TYPE` PRIMARY KEY (`id_car_type`)
) ENGINE=INNODB
;

CREATE TABLE `CARGO`
(
	`id_cargo` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`name` VARCHAR(128) NOT NULL,
	`weight` INT NOT NULL,
	`volume` INT NOT NULL,
	`id_cargo_type` INT UNSIGNED,
	`id_provider` INT UNSIGNED,
	`price` DECIMAL(10,2) NOT NULL,
	CONSTRAINT `PK_CARGO` PRIMARY KEY (`id_cargo`)
) ENGINE=INNODB
;

CREATE TABLE `CARGO_TYPE`
(
	`id_cargo_type` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`name` VARCHAR(128) NOT NULL,
	CONSTRAINT `PK_CARGO_TYPE` PRIMARY KEY (`id_cargo_type`)
) ENGINE=INNODB
;

CREATE TABLE `CHECK_POINT`
(
	`id_check_point` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`x` FLOAT(6,3) NOT NULL,
	`y` FLOAT(6,3) NOT NULL,
	`name` VARCHAR(50),
	`id_check_point_type` INT UNSIGNED,
	CONSTRAINT `PK_CHECK_POINT` PRIMARY KEY (`id_check_point`)
) ENGINE=INNODB
;

CREATE TABLE `CHECK_POINT_TYPE`
(
	`id_check_point_type` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`name` VARCHAR(50),
	CONSTRAINT `PK_CHECK_POINT_TYPE` PRIMARY KEY (`id_check_point_type`)
) ENGINE=INNODB
;

CREATE TABLE `DELIVERY_POINT`
(
	`id_delivery_point` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`address` VARCHAR(255) NOT NULL,
	CONSTRAINT `PK_DELIVERY_POINT` PRIMARY KEY (`id_delivery_point`)
) ENGINE=INNODB
;

CREATE TABLE `EMPLOYEE`
(
	`id_employee` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`first_name` VARCHAR(128) NOT NULL,
	`second_name` VARCHAR(128) NOT NULL,
	`middle_name` VARCHAR(128),
	`initials` VARCHAR(128),
	`id_user_credantials` INT UNSIGNED NOT NULL,
	`id_user_position` INT UNSIGNED,
	CONSTRAINT `PK_EMPLOYEE` PRIMARY KEY (`id_employee`)
) ENGINE=INNODB
;

CREATE TABLE `M2M_CHECK_POINT_ROUTE`
(
	`id_route` INT UNSIGNED NOT NULL,
	`id_check_point` INT UNSIGNED NOT NULL,
	`number` INT NOT NULL,
	CONSTRAINT `PK_M2M_CHECK_POINT_ROUTE` PRIMARY KEY (`id_route`,`id_check_point`)
) ENGINE=INNODB
;

CREATE TABLE `M2M_RECIEVER_DELIVERY_POINT`
(
	`id_reciever` INT UNSIGNED NOT NULL,
	`id_delivery_point` INT UNSIGNED NOT NULL,
	CONSTRAINT `PK_M2M_RECIEVER_DELIVERY_POINT` PRIMARY KEY (`id_reciever`,`id_delivery_point`)
) ENGINE=INNODB
;

CREATE TABLE `MODEL_CAR`
(
	`id_model_car` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`name` VARCHAR(128) NOT NULL,
	CONSTRAINT `PK_MODEL_CAR` PRIMARY KEY (`id_model_car`)
) ENGINE=INNODB
;

CREATE TABLE `PROVIDER`
(
	`id_provider` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`name` VARCHAR(128) NOT NULL,
	`phone` VARCHAR(13) NOT NULL,
	`address` VARCHAR(255) NOT NULL,
	`email` VARCHAR(255),
	CONSTRAINT `PK_PROVIDER` PRIMARY KEY (`id_provider`)
) ENGINE=INNODB
;

CREATE TABLE `RECIEVER`
(
	`id_reciever` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`name` VARCHAR(255) NOT NULL,
	`address` VARCHAR(255) NOT NULL,
	`phone` VARCHAR(13) NOT NULL,
	`email` VARCHAR(50),
	CONSTRAINT `PK_RECIEVER` PRIMARY KEY (`id_reciever`)
) ENGINE=INNODB
;

CREATE TABLE `ROUTE`
(
	`id_route` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`total_length` DECIMAL(10,2) NOT NULL,
	`count` INT DEFAULT 0,
	`id_employee` INT UNSIGNED,
	CONSTRAINT `PK_ROUTE` PRIMARY KEY (`id_route`)
) ENGINE=INNODB
;

CREATE TABLE `TRIP`
(
	`id_trip` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`start_date` DATE NOT NULL,
	`expected_finish_date` DATE NOT NULL,
	`real_finish_date` DATE,
	`real_fuel_consumption` DECIMAL(6,3),
	`driver_profit` DECIMAL(10,2) NOT NULL,
	`expenses` DECIMAL(10,2) NOT NULL,
	`id_trip_status` INT UNSIGNED NOT NULL,
	`id_waybill` INT UNSIGNED NOT NULL,
	`id_route` INT UNSIGNED NOT NULL,
	`expected_fuel_consuption` DECIMAL(6,3) NOT NULL,
	`id_car` INT UNSIGNED NOT NULL,
	CONSTRAINT `PK_TRIP` PRIMARY KEY (`id_trip`)
) ENGINE=INNODB
;

CREATE TABLE `TRIP_STATUS`
(
	`id_trip_status` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`name` INT NOT NULL,
	CONSTRAINT `PK_TRIP_STATUS` PRIMARY KEY (`id_trip_status`)
) ENGINE=INNODB
;

CREATE TABLE `USER_CREDANTIALS`
(
	`id_user_credantials` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`login` VARCHAR(50) NOT NULL,
	`password` VARCHAR(50) NOT NULL,
	CONSTRAINT `PK_USER_CREDANTIALS` PRIMARY KEY (`id_user_credantials`)
) ENGINE=INNODB
;

CREATE TABLE `USER_POSITION`
(
	`id_user_position` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`name` VARCHAR(128) NOT NULL,
	CONSTRAINT `PK_USER_POSITION` PRIMARY KEY (`id_user_position`)
) ENGINE=INNODB
;

CREATE TABLE `VENDOR_CAR`
(
	`id_vendor_car` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`name` VARCHAR(128) NOT NULL,
	CONSTRAINT `PK_VENDOR_CAR` PRIMARY KEY (`id_vendor_car`)
) ENGINE=INNODB
;

CREATE TABLE `WAYBILL`
(
	`id_waybill` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`id_cargo` INT UNSIGNED,
	`profit` DECIMAL(10,2) NOT NULL,
	`id_reciever` INT UNSIGNED,
	`id_delivery_point` INT UNSIGNED,
	CONSTRAINT `PK_WAYBILL` PRIMARY KEY (`id_waybill`)
) ENGINE=INNODB
;

ALTER TABLE `CAR`
 ADD INDEX `IXFK_CAR_CAR_TYPE` (`id_car_type` ASC)
;

ALTER TABLE `CAR`
 ADD INDEX `IXFK_CAR_EMPLOYEE` (`id_employee` ASC)
;

ALTER TABLE `CAR`
 ADD INDEX `IXFK_CAR_MODEL_CAR` (`id_model_car` ASC)
;

ALTER TABLE `CAR`
 ADD INDEX `IXFK_CAR_VENDOR_CAR` (`id_vendor_car` ASC)
;

ALTER TABLE `CARGO`
 ADD INDEX `IXFK_CARGO_CARGO_TYPE` (`id_cargo_type` ASC)
;

ALTER TABLE `CARGO`
 ADD INDEX `IXFK_CARGO_PROVIDER` (`id_provider` ASC)
;

ALTER TABLE `CHECK_POINT`
 ADD INDEX `IXFK_CHECK_POINT_CHECK_POINT_TYPE` (`id_check_point_type` ASC)
;

ALTER TABLE `EMPLOYEE`
 ADD INDEX `IXFK_EMPLOYEE_USER_CREDANTIALS` (`id_user_credantials` ASC)
;

ALTER TABLE `EMPLOYEE`
 ADD INDEX `IXFK_EMPLOYEE_USER_POSITION` (`id_user_position` ASC)
;

ALTER TABLE `M2M_CHECK_POINT_ROUTE`
 ADD INDEX `IXFK_M2M_CHECK_POINT_ROUTE_CHECK_POINT` (`id_check_point` ASC)
;

ALTER TABLE `M2M_CHECK_POINT_ROUTE`
 ADD INDEX `IXFK_M2M_CHECK_POINT_ROUTE_ROUTE` (`id_route` ASC)
;

ALTER TABLE `M2M_RECIEVER_DELIVERY_POINT`
 ADD INDEX `IXFK_M2M_RECIEVER_DELIVERY_POINT_DELIVERY_POINT` (`id_delivery_point` ASC)
;

ALTER TABLE `M2M_RECIEVER_DELIVERY_POINT`
 ADD INDEX `IXFK_M2M_RECIEVER_DELIVERY_POINT_RECIEVER` (`id_reciever` ASC)
;

ALTER TABLE `ROUTE`
 ADD INDEX `IXFK_ROUTE_EMPOYEE` (`id_employee` ASC)
;

ALTER TABLE `TRIP`
 ADD INDEX `IXFK_TRIP_CAR` (`id_car` ASC)
;

ALTER TABLE `TRIP`
 ADD INDEX `IXFK_TRIP_ROUTE` (`id_route` ASC)
;

ALTER TABLE `TRIP`
 ADD INDEX `IXFK_TRIP_TRIP_STATUS` (`id_trip_status` ASC)
;

ALTER TABLE `TRIP`
 ADD INDEX `IXFK_TRIP_WAYBILL` (`id_waybill` ASC)
;

ALTER TABLE `WAYBILL`
 ADD INDEX `IXFK_WAYBILL_CARGO` (`id_cargo` ASC)
;

ALTER TABLE `WAYBILL`
 ADD INDEX `IXFK_WAYBILL_DELIVERY_POINT` (`id_delivery_point` ASC)
;

ALTER TABLE `WAYBILL`
 ADD INDEX `IXFK_WAYBILL_RECIEVER` (`id_reciever` ASC)
;

ALTER TABLE `CAR`
 ADD CONSTRAINT `FK_CAR_CAR_TYPE`
	FOREIGN KEY (`id_car_type`) REFERENCES `CAR_TYPE` (`id_car_type`) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE `CAR`
 ADD CONSTRAINT `FK_CAR_EMPLOYEE`
	FOREIGN KEY (`id_employee`) REFERENCES `EMPLOYEE` (`id_employee`) ON DELETE Set Null ON UPDATE No Action
;

ALTER TABLE `CAR`
 ADD CONSTRAINT `FK_CAR_MODEL_CAR`
	FOREIGN KEY (`id_model_car`) REFERENCES `MODEL_CAR` (`id_model_car`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `CAR`
 ADD CONSTRAINT `FK_CAR_VENDOR_CAR`
	FOREIGN KEY (`id_vendor_car`) REFERENCES `VENDOR_CAR` (`id_vendor_car`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `CARGO`
 ADD CONSTRAINT `FK_CARGO_CARGO_TYPE`
	FOREIGN KEY (`id_cargo_type`) REFERENCES `CARGO_TYPE` (`id_cargo_type`) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE `CARGO`
 ADD CONSTRAINT `FK_CARGO_PROVIDER`
	FOREIGN KEY (`id_provider`) REFERENCES `PROVIDER` (`id_provider`) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE `CHECK_POINT`
 ADD CONSTRAINT `FK_CHECK_POINT_CHECK_POINT_TYPE`
	FOREIGN KEY (`id_check_point_type`) REFERENCES `CHECK_POINT_TYPE` (`id_check_point_type`) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE `EMPLOYEE`
 ADD CONSTRAINT `FK_EMPLOYEE_USER_CREDANTIALS`
	FOREIGN KEY (`id_user_credantials`) REFERENCES `USER_CREDANTIALS` (`id_user_credantials`) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE `EMPLOYEE`
 ADD CONSTRAINT `FK_EMPLOYEE_USER_POSITION`
	FOREIGN KEY (`id_user_position`) REFERENCES `USER_POSITION` (`id_user_position`) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE `M2M_CHECK_POINT_ROUTE`
 ADD CONSTRAINT `FK_M2M_CHECK_POINT_ROUTE_CHECK_POINT`
	FOREIGN KEY (`id_check_point`) REFERENCES `CHECK_POINT` (`id_check_point`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `M2M_CHECK_POINT_ROUTE`
 ADD CONSTRAINT `FK_M2M_CHECK_POINT_ROUTE_ROUTE`
	FOREIGN KEY (`id_route`) REFERENCES `ROUTE` (`id_route`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `M2M_RECIEVER_DELIVERY_POINT`
 ADD CONSTRAINT `FK_M2M_RECIEVER_DELIVERY_POINT_DELIVERY_POINT`
	FOREIGN KEY (`id_delivery_point`) REFERENCES `DELIVERY_POINT` (`id_delivery_point`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `M2M_RECIEVER_DELIVERY_POINT`
 ADD CONSTRAINT `FK_M2M_RECIEVER_DELIVERY_POINT_RECIEVER`
	FOREIGN KEY (`id_reciever`) REFERENCES `RECIEVER` (`id_reciever`) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE `ROUTE`
 ADD CONSTRAINT `FK_ROUTE_EMPOYEE`
	FOREIGN KEY (`id_employee`) REFERENCES `EMPLOYEE` (`id_employee`) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE `TRIP`
 ADD CONSTRAINT `FK_TRIP_CAR`
	FOREIGN KEY (`id_car`) REFERENCES `CAR` (`id_car`) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE `TRIP`
 ADD CONSTRAINT `FK_TRIP_ROUTE`
	FOREIGN KEY (`id_route`) REFERENCES `ROUTE` (`id_route`) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE `TRIP`
 ADD CONSTRAINT `FK_TRIP_TRIP_STATUS`
	FOREIGN KEY (`id_trip_status`) REFERENCES `TRIP_STATUS` (`id_trip_status`) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE `TRIP`
 ADD CONSTRAINT `FK_TRIP_WAYBILL`
	FOREIGN KEY (`id_waybill`) REFERENCES `WAYBILL` (`id_waybill`) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE `WAYBILL`
 ADD CONSTRAINT `FK_WAYBILL_CARGO`
	FOREIGN KEY (`id_cargo`) REFERENCES `CARGO` (`id_cargo`) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE `WAYBILL`
 ADD CONSTRAINT `FK_WAYBILL_DELIVERY_POINT`
	FOREIGN KEY (`id_delivery_point`) REFERENCES `DELIVERY_POINT` (`id_delivery_point`) ON DELETE Restrict ON UPDATE No Action
;

ALTER TABLE `WAYBILL`
 ADD CONSTRAINT `FK_WAYBILL_RECIEVER`
	FOREIGN KEY (`id_reciever`) REFERENCES `RECIEVER` (`id_reciever`) ON DELETE Restrict ON UPDATE Restrict
;

SET FOREIGN_KEY_CHECKS=1;
