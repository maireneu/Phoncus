CREATE DEFINER=`root`@`localhost` PROCEDURE `Curation_main`(IN `select_size` FLOAT, IN `select_os` VARCHAR(15) CHARSET utf8, IN `select_performance` INT, IN `select_ppi` INT, IN `select_camera` INT, IN `select_manufacturer` VARCHAR(15) CHARSET utf8, IN `select_batterysaperate` VARCHAR(15) CHARSET utf8, IN `select_wirelesscharge` VARCHAR(15) CHARSET utf8, IN `select_waterproof` VARCHAR(15) CHARSET utf8, IN `select_fingerprint` VARCHAR(15) CHARSET utf8, IN `select_stylus` VARCHAR(15) CHARSET utf8, IN `select_knockcode` VARCHAR(15) CHARSET utf8, IN `select_samsungpay` VARCHAR(15) CHARSET utf8, IN `select_button` VARCHAR(15) CHARSET utf8) NOT DETERMINISTIC CONTAINS SQL SQL SECURITY DEFINER begin
	declare sum int default 0;
	declare count int default 0;
	declare rank_1 int default 0;
	declare rank_2 int default 0;
	declare rank_3 int default 0;
	declare score_1 int default 0;
	declare score_2 int default 0;
	declare score_3 int default 0;
	declare box_s int default 0;
	declare box_r int default 0;
	set count = 1;

	loop_rank:loop
	
        if (select max(NUMBER) from PHONE_DATA)<count then 
        	leave loop_rank;
        end if;
        
		set sum = Curation_score(select_size,select_os,select_performance,select_ppi,select_camera,select_manufacturer,select_batterysaperate,select_wirelesscharge,select_waterproof,select_fingerprint,select_stylus,select_knockcode,select_samsungpay,select_button,count);	

		update PHONE_DATA set SCORE_SUM=sum where NUMBER=count;

		if score_1<sum then
			set score_3 = score_2;
			set rank_3 = rank_2;
			set score_2 = score_1;
			set rank_2 = rank_1;
			set score_1 = sum;
			set rank_1 = count;
		elseif score_2<sum then
			set score_3 = score_2;
			set rank_3 = rank_2;
			set score_2 = sum;
			set rank_2 = count;
			
		elseif score_3<sum then
			set score_3 = sum;
			set rank_3 = count;
		end if;

		set count = count+1;

	end loop;

	select 
	(select PETNAME from PHONE_DATA where NUMBER = rank_1),
	(select SIZE from PHONE_DATA where NUMBER = rank_1),
	(select OS from PHONE_DATA where NUMBER = rank_1),
	(select OS_VERSION from PHONE_DATA where NUMBER = rank_1),
	(select PERFORMANCE_RATING from PHONE_DATA where NUMBER = rank_1),
	(select PPI from PHONE_DATA where NUMBER = rank_1),
	(select CAMERA_RATING from PHONE_DATA where NUMBER = rank_1),
	(select MANUFACTURER from PHONE_DATA where NUMBER = rank_1),
	(select BATTERY_SAPERATE from PHONE_DATA where NUMBER = rank_1),
	(select WIRELESS_CHARGE from PHONE_DATA where NUMBER = rank_1),
	(select WATERPROOF from PHONE_DATA where NUMBER = rank_1),
	(select FINGERPRINT from PHONE_DATA where NUMBER = rank_1),
	(select STYLUS from PHONE_DATA where NUMBER = rank_1),
	(select KNOCKCODE from PHONE_DATA where NUMBER = rank_1),
	(select SAMSUNGPAY from PHONE_DATA where NUMBER = rank_1),
	(select BUTTON from PHONE_DATA where NUMBER = rank_1),
	
	(select PETNAME from PHONE_DATA where NUMBER = rank_2),
	(select SIZE from PHONE_DATA where NUMBER = rank_2),
	(select OS from PHONE_DATA where NUMBER = rank_2),
	(select OS_VERSION from PHONE_DATA where NUMBER = rank_2),
	(select PERFORMANCE_RATING from PHONE_DATA where NUMBER = rank_2),
	(select PPI from PHONE_DATA where NUMBER = rank_2),
	(select CAMERA_RATING from PHONE_DATA where NUMBER = rank_2),
	(select MANUFACTURER from PHONE_DATA where NUMBER = rank_2),
	(select BATTERY_SAPERATE from PHONE_DATA where NUMBER = rank_2),
	(select WIRELESS_CHARGE from PHONE_DATA where NUMBER = rank_2),
	(select WATERPROOF from PHONE_DATA where NUMBER = rank_2),
	(select FINGERPRINT from PHONE_DATA where NUMBER = rank_2),
	(select STYLUS from PHONE_DATA where NUMBER = rank_2),
	(select KNOCKCODE from PHONE_DATA where NUMBER = rank_2),
	(select SAMSUNGPAY from PHONE_DATA where NUMBER = rank_2),
	(select BUTTON from PHONE_DATA where NUMBER = rank_2),
	
	(select PETNAME from PHONE_DATA where NUMBER = rank_3),
	(select SIZE from PHONE_DATA where NUMBER = rank_3),
	(select OS from PHONE_DATA where NUMBER = rank_3),
	(select OS_VERSION from PHONE_DATA where NUMBER = rank_3),
	(select PERFORMANCE_RATING from PHONE_DATA where NUMBER = rank_3),
	(select PPI from PHONE_DATA where NUMBER = rank_3),
	(select CAMERA_RATING from PHONE_DATA where NUMBER = rank_3),
	(select MANUFACTURER from PHONE_DATA where NUMBER = rank_3),
	(select BATTERY_SAPERATE from PHONE_DATA where NUMBER = rank_3),
	(select WIRELESS_CHARGE from PHONE_DATA where NUMBER = rank_3),
	(select WATERPROOF from PHONE_DATA where NUMBER = rank_3),
	(select FINGERPRINT from PHONE_DATA where NUMBER = rank_3),
	(select STYLUS from PHONE_DATA where NUMBER = rank_3),
	(select KNOCKCODE from PHONE_DATA where NUMBER = rank_3),
	(select SAMSUNGPAY from PHONE_DATA where NUMBER = rank_3),
	(select BUTTON from PHONE_DATA where NUMBER = rank_3);

	commit;
end