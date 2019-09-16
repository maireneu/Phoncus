CREATE DEFINER=`root`@`localhost` FUNCTION `p_socre`(`use_size` FLOAT, `use_os` VARCHAR(10) CHARSET utf8, `use_grade` INT, `use_ppi` INT, `use_camera` INT, `use_finger` VARCHAR(10) CHARSET utf8, `use_pay` VARCHAR(10) CHARSET utf8, `use_knock` VARCHAR(10) CHARSET utf8, `use_company` VARCHAR(10) CHARSET utf8, `use_batteryin` VARCHAR(10) CHARSET utf8, `use_wrt` VARCHAR(10) CHARSET utf8, `use_water` VARCHAR(10) CHARSET utf8, `use_qcbc` VARCHAR(10) CHARSET utf8, `use_pen` VARCHAR(10) CHARSET utf8, `use_hb` VARCHAR(10) CHARSET utf8, `count` INT) RETURNS int(10)
    DETERMINISTIC
begin
	declare sum int default 0;
	declare tmp int default 0;
	declare data_size float default 0;
	declare data_os varchar(15) default 0;
	declare data_performance int default 0;
	declare data_ppi int default 0;
	declare data_camera int default 0;
	declare data_manufacturer varchar(15) default 0;
	declare data_batterysaperate varchar(15) default 0;
	declare data_wirelesscharge varchar(15) default 0;
	declare data_waterproof varchar(15) default 0;
	declare data_fingerprint varchar(15) default 0;
	declare data_stylus varchar(15) default 0;
	declare data_knockcode varchar(15) default 0;
	declare data_samsungpay varchar(15) default 0;
	declare data_button varchar(15) default 0;

	select SIZE into data_size from PHONE_DATA where NUMBER=count;
	select OS into data_os from PHONE_DATA where NUMBER=count;
	select PERFORMANCE_RATING into data_performance from PHONE_DATA where NUMBER=count;
	select PPI into data_ppi from PHONE_DATA where NUMBER=count;
	select CAMERA_RATING into data_camera from PHONE_DATA where NUMBER=count;
	select MANUFACTURER into data_manufacturer from PHONE_DATA where NUMBER=count;
	select BATTERY_SAPERATE into data_batterysaperate from PHONE_DATA where NUMBER=count;
	select WIRELESS_CHARGE into data_wirelesscharge from PHONE_DATA where NUMBER=count;
	select WATERPROOF into data_waterproof from PHONE_DATA where NUMBER=count;
	select FINGERPRINT into data_fingerprint from PHONE_DATA where NUMBER=count;
	select STYLUS into data_stylus from PHONE_DATA where NUMBER=count;
	select KNOCKCODE into data_knockcode from PHONE_DATA where NUMBER=count;
	select SAMSUNGPAY into data_samsungpay from PHONE_DATA where NUMBER=count;
	select BUTTON into data_button from PHONE_DATA where NUMBER=count;

	set tmp = Curation_score_size(select_size, data_size);
	set sum = sum + tmp;

	set tmp = Curation_score_os(select_os,data_os);
	set sum = sum+tmp;

	set tmp = Curation_score_performance(select_performance,data_performance);
	set sum = sum+tmp;

	set tmp = Curation_score_ppi(select_ppi,data_ppi);
	set sum = sum+tmp;

	set tmp = Curation_score_camera(select_camera,data_camera);
	set sum = sum+tmp;

	set tmp = Curation_score_additionalfunction(select_manufacturer,data_manufacturer,select_batterysaperate,data_batterysaperate,select_wirelesscharge,data_wirelesscharge,select_waterproof,data_waterproof,select_fingerprint,data_fingerprint,select_stylus,data_stylus,select_knockcode,data_knockcode,select_samsungpay,data_samsungpay,select_button,data_button);
	set sum = sum+tmp;

	return sum;
end