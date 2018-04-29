package dev.ross.rossgame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//Utility class used to help read world files
public class Utils {
	
	//Loads in worlds (menu, word1 etc)
	public static String loadFileAsString(String path) {
		
		//allow adding chars to string
		StringBuilder builder = new StringBuilder(); 
		
		try {
			//Create a new BufferedReader to read the file
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			String line;
			
			//Go through each line and store it
			while( (line = br.readLine()) != null)
				builder.append(line + "\n");
			
			//Close the file
			br.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	//Get integer from string and return it
	public static int parseInt(String num) {
		try {
			return Integer.parseInt(num);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
