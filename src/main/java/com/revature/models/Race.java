package com.revature.models;

import java.util.ArrayList;
import java.util.List;

public class Race {
	private int race_id;
	private String name;
	private int str;
	private int dex;
	private int con;
	private int inte;
	private int wis;
	private int cha;
	private List<String> traits;
	
	public Race() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Race(int race_id, String name, int str, int dex, int con, int inte, int wis,
			int cha) {
		super();
		this.race_id = race_id;
		this.name = name;
		this.str = str;
		this.dex = dex;
		this.con = con;
		this.inte = inte;
		this.wis = wis;
		this.cha = cha;
		this.traits= new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Race: " + name +
		        "\n Stats: {str=" + str + ", dex=" + dex + ", con=" + con + ", inte=" + inte + ", wis=" + wis + ", cha=" + cha + "}" +
		        "\n Traits: " + traits;
		}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cha;
		result = prime * result + con;
		result = prime * result + dex;
		result = prime * result + inte;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + race_id;
		result = prime * result + str;
		result = prime * result + wis;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Race other = (Race) obj;
		if (cha != other.cha)
			return false;
		if (con != other.con)
			return false;
		if (dex != other.dex)
			return false;
		if (inte != other.inte)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (race_id != other.race_id)
			return false;
		if (str != other.str)
			return false;
		if (wis != other.wis)
			return false;
		return true;
	}

	public int getRace_id() {
		return race_id;
	}

	public void setRace_id(int race_id) {
		this.race_id = race_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getDex() {
		return dex;
	}

	public void setDex(int dex) {
		this.dex = dex;
	}

	public int getCon() {
		return con;
	}

	public void setCon(int con) {
		this.con = con;
	}

	public int getInte() {
		return inte;
	}

	public void setInte(int inte) {
		this.inte = inte;
	}

	public int getWis() {
		return wis;
	}

	public void setWis(int wis) {
		this.wis = wis;
	}

	public int getCha() {
		return cha;
	}

	public void setCha(int cha) {
		this.cha = cha;
	}

	public List<String> getTraits() {
		return traits;
	}

	public void setTraits(String t) {
		traits.add(t);
	}	
	
}
