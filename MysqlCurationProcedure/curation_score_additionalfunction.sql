begin
	declare score int default 0;
	
	if select_manufacturer=data_manufacturer then
		set score = score + 20;
	end if;

	if select_batterysaperate=data_batterysaperate then
		set score = score + 20;
	end if;

	if select_wirelesscharge=data_wirelesscharge then
		set score = score + 20;
	end if;
	
	if select_waterproof=data_waterproof then
	set score = score + 20;
	end if;

	if select_fingerprint=data_fingerprint then
		set score = score + 20;
	end if;

	if select_stylus=data_stylus then
		set score = score + 20;
	end if;

	if select_knockcode=data_knockcode then
		set score = score + 20;
	end if;

	if select_samsungpay=data_samsungpay then
		set score = score + 20;
	end if;

	if select_button=data_button then
		set score = score + 20;
	end if;

	return score;

end