begin
	declare score int default 0;

	if abs(select_performance-data_performance)=0 then
		set score = 100;
	elseif abs(select_performance-data_performance)=1 then
		set score = 80;
	elseif abs(select_performance-data_performance)=2 then
		set score = 60;
	elseif abs(select_performance-data_performance)=3 then
		set score = 40;
	else set score = 20;
	end if;
	
	return score;

end