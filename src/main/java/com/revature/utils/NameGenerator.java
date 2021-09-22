package com.revature.utils;

import java.util.ArrayList;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


public class NameGenerator{

	public static ArrayList<String> generateName() {
		String apiKey = System.getenv("BtNkey");
		String host = "https://www.behindthename.com/api/random.json?usage=whim&gender=both&key=" + apiKey;
		try {
			  HttpResponse<JsonNode> response = Unirest.get(host).asJson();
		      JsonParser jp = new JsonParser();
		      JsonElement je = jp.parse(response.getBody().toString());
		      String[] arr = je.toString().split("\"");
		      ArrayList<String> al = new ArrayList<>();
		      al.add(arr[3]);
		      al.add(arr[5]);
		      return al;
		}
		catch(UnirestException e){
			System.out.println("Error in generateName");
			e.printStackTrace();
		}
		return null;
		
		
	}
}
