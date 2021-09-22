package com.revature.dao;

import java.util.List;

import com.revature.models.Race;

public interface RaceDaoI {
	public List<Race> getRaces();
	public Race getRaceByName(String race);
	public List<Race> getRaceByStat(String stat);
}
