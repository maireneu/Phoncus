begin
	declare score int default 0; 
			
	if abs(select_size-data_size) <=0.05 then
		set score = 100;
	elseif abs(select_size-data_size) <=0.1 then
		set score = 90;
	elseif abs(select_size-data_size) <=0.15 then
		set score = 80;
	elseif abs(select_size-data_size) <=0.2 then
		set score = 70;
	elseif abs(select_size-data_size) <=0.25 then
		set score = 60;
	elseif abs(select_size-data_size) <=0.3 then
		set score = 50;
	else set score =40;

	end if;
	
	return score;
end