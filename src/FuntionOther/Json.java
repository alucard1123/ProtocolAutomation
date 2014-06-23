package FuntionOther;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
public class Json {
	//生成Json数组
	@SuppressWarnings("unchecked")
	public static String encodeJsonArray(List<String> ListtoJson)
	{
		JSONArray new_ja = new JSONArray();
		for(int i=0;i<ListtoJson.size();i++)
		{
			new_ja.add(ListtoJson.get(i));
		}
		System.out.println(new_ja.toString());   
		return new_ja.toString();
	}
	//生成Json对象
	 @SuppressWarnings("unchecked")
	public static String encodeJsonObj(Map<String, String> MaptoJson) 
	 {
		  	//生成JSONObject对象  
		   	JSONObject new_jo = new JSONObject();
			for(Entry<String, String> entry: MaptoJson.entrySet())
			{
				new_jo.put(entry.getKey(), entry.getValue());
			}
			System.out.println(new_jo.toString());
			return new_jo.toString();
	 }
		
	 /*解析JSON数组字符串
	  * jsonstr = "[\"129653\",\"129664\",\"129660\",\"129665\"]"
	  */
	 public LinkedList<String> decodeJsonArry(LinkedList<String> jsontolist,String jsonstr)
	 {
		  JSONArray js = (JSONArray) JSONValue.parse(jsonstr);   
		  for (int i = 0; i < js.size(); i++)
		  {
	
		   System.out.println(js.get(i)+":::"); 
		   jsontolist.add(js.get(i).toString());
		  }
		return jsontolist;
		 
	 }
	 
	 /*解析JSON对象
	  * jsonStr = "{\"aa\":1,\"cc\":2,\"bb\":3}";
	  */
	 @SuppressWarnings("rawtypes")
	public LinkedHashMap<String,String> decodeJsonObj(LinkedHashMap<String,String> jsonMap,String jsonStr)
	 {
	        LinkedHashMap<String, String>  jsonMap1 = JSON.parseObject(jsonStr,new TypeReference<LinkedHashMap<String, String>>(){}) ;
	        jsonMap=jsonMap1;
		 /*
		 LinkedList<String> jsonStrlist = new LinkedList<String>();
		 String jsonStr1="";
		 String jsonStr2="";
		 if(jsonStr.matches("\\{.*\\{.*\\}.*\\}"))
		 {
			 String str1= jsonStr.split("\\{")[jsonStr.split("\\{").length-1];
			 jsonStr1="{"+str1.split("\\}")[0]+"}";

			 System.out.print("CHENJIEdebug::jsonStr1:"+jsonStr1+"\n");
			 jsonStrlist.add(jsonStr1);
			 jsonStr2 = jsonStr.replace(jsonStr1, "");
			 System.out.print("CHENJIEdebug::jsonStr2:"+jsonStr2+"\n");
			 jsonStrlist.add(jsonStr2);
		 }
		 else 
		 {
			 jsonStrlist.add(jsonStr);
			 System.out.print("CHENJIEDEBUG::jsonStr:"+jsonStr+"\n");
		 }
		 //TEST!!!
		 for(int i=0;i<jsonStrlist.size();i++)
		 {
			 System.out.println("TESTCHENJIE!!!jsonStrlist"+i+"::"+jsonStrlist.get(i));
		 }
		 for(int i=0;i<jsonStrlist.size();i++)
		 {
			 JSONObject jo = (JSONObject) JSONValue.parse(jsonStrlist.get(i));
			  System.out.print("TestCHENJIE!!!JO"+jo+"\r\n");
			  Iterator iter = jo.keySet().iterator();
			  while(iter.hasNext())
			  {
			   String key = iter.next().toString();

			   System.out.println("[key=" + key + ", val=" + jo.get(key) + "]");     
			   jsonMap.put(key, jo.get(key).toString());
			 }
		 }
		 */
		 /*
		  JSONObject jo1 = (JSONObject) JSONValue.parse(jsonStr1);
		  JSONObject jo2 = (JSONObject) JSONValue.parse(jsonStr2);
		  System.out.print(jo1+"\r\n");
		  System.out.print(jo2+"\r\n");		 
		  Iterator iter1 = jo1.keySet().iterator();
		  Iterator iter2 = jo2.keySet().iterator();
		  while(iter1.hasNext())
		  {
		   String key = iter1.next().toString();

		   System.out.println("[key=" + key + ", val=" + jo1.get(key) + "]");     
		   jsonMap.put(key, jo1.get(key).toString());
		 }
		  while(iter2.hasNext())
		  {
		   String key = iter2.next().toString();

		   System.out.println("[key=" + key + ", val=" + jo2.get(key) + "]");     
		   jsonMap.put(key, jo2.get(key).toString());
		 }		
		 */  
		return jsonMap;
		 
	 }

}
