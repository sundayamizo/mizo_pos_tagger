package MizoPOS;
import java.io.*;
import java.util.*;
public class ReadString{
	String token = "";
	Scanner scnr;
	String[] words; 
	public ReadString(String p) throws Exception{
		scnr = new Scanner(new File(p));
	}
	public String[] extractWord(){
		List<String> temps = new ArrayList<String>();
		while(scnr.hasNext()){
			token = scnr.next();
			temps.add(token);
			}
		scnr.close();
		 String[] tempsArray = temps.toArray(new String[0]);
		 int len = tempsArray.length;
		 words = new String[len];
		 int i=0;
		 for(String s: tempsArray){
				System.out.println(s);
				words[i] = s;
				i++;
				
		}
		 return words;
	}
}

