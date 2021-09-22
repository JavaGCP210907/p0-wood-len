package com.revature.utils;

import java.util.ArrayList;
import java.util.Random;

public class Dice {
	
	public int roll(int die) {
		int min =1;
		Random random = new Random();
		return random.nextInt(die - min + 1) + min;
	}
	
	public ArrayList<Integer> rollMultiple(int num, int die) {
		ArrayList<Integer> rolls = new ArrayList<>();
		for(int i = 0; i < num; i++) {
			rolls.add(roll(die));
		}
		return rolls;
	}
	
}
