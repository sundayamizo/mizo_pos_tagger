package MizoPOS;

import java.io.*;
public class Append{
	String a = "";
	String [] linearr;
	FileReader fr;
	BufferedReader br;
	String line;
	
	public Append(String p)throws Exception{
  	fr = new FileReader(p);
	}
	public String[] Read() throws Exception{
		br = new BufferedReader(fr);
			line = br.readLine();
	        String m=line.replaceAll(",", "");
	       // m=line.replaceAll("?","");
		 linearr = m.split("\\.");
		 
		
		return linearr;
	}
	
}
