package com.revature.models;

public class AbilityScore {
		private int id;
		private int fk;
		private int str;
		private int dex;
		private int con;
		private int inte;
		private int wis;
		private int cha;
		
		public AbilityScore(int id, int fk, int str, int dex, int con, int inte, int wis, int cha) {
			super();
			this.id = id;
			this.fk = fk;
			this.str = str;
			this.dex = dex;
			this.con = con;
			this.inte = inte;
			this.wis = wis;
			this.cha = cha;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + cha;
			result = prime * result + con;
			result = prime * result + dex;
			result = prime * result + fk;
			result = prime * result + id;
			result = prime * result + inte;
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
			AbilityScore other = (AbilityScore) obj;
			if (cha != other.cha)
				return false;
			if (con != other.con)
				return false;
			if (dex != other.dex)
				return false;
			if (fk != other.fk)
				return false;
			if (id != other.id)
				return false;
			if (inte != other.inte)
				return false;
			if (str != other.str)
				return false;
			if (wis != other.wis)
				return false;
			return true;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getFk() {
			return fk;
		}

		public void setFk(int fk) {
			this.fk = fk;
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
}
