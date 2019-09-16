begin
	declare score int default 0;

	if select_os=data_os then 
		set score = 50;
	else set score = 0;
	
	end if;
		return score;
end