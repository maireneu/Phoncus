begin
	declare score int default 0;

	if abs(select_ppi-data_ppi) <=75 then
		set score = 100;
	elseif abs(select_ppi-data_ppi) <=150 then
		set score = 80;
	elseif abs(select_ppi-data_ppi) <=225 then
		set score = 60;
	else set score = 40;

	end if;
		return score;

end