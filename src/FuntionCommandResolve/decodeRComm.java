package FuntionCommandResolve;

import java.util.LinkedHashMap;


import FuntionOther.Json;

public class decodeRComm {
	public static LinkedHashMap<String,String> decodeJsonComm(String receiveS)
	{
		Json jsonclass = new Json();
		LinkedHashMap<String, String> receiveMap = new LinkedHashMap<String,String>();
		if(!receiveS.equals(""))
		{
			System.out.print("CHENJIEDEBUG::receiveS:"+receiveS+"\n");
			receiveMap =jsonclass.decodeJsonObj(receiveMap, receiveS);
		}
		return receiveMap ;
		
	}

}
