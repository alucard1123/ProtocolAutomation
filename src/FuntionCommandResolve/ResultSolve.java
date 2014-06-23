package FuntionCommandResolve;



import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
//import java.util.Map.Entry;

import FuntionReport.WriteReport;




public class ResultSolve 
{
	public static void resultSolve(Map<String,String> getoutMap ,LinkedHashMap<String,String> receiveMap,
			WriteReport LogReport)
	{
		Map<String, Boolean> mapResult = new LinkedHashMap<String, Boolean>();
		Set<String> getoutkeySet = getoutMap.keySet();
		//int stepID=0;

		for(String key:getoutkeySet)
		{
			if(receiveMap.containsKey(key.trim()))
			{
				System.out.println("CHENJIE:key:"+key);
				System.out.println("CHENJIE:receiveMap.containsKey(key.trim()):"+receiveMap.containsKey(key.trim()));
				if(receiveMap.get(key).trim()!=null&&
						getoutMap.get(key).trim().equals(receiveMap.get(key).trim())
						)
				{
					mapResult.put(key.trim(), true);
					LogReport.writeLog(LogReport.getReportNameString(),
							"****************************************************"+"\r\n");	
					System.out.println("CHENJIE::"+"步骤ID:"+mapResult.size()+"|步骤名:"+key.trim()+
							"|结果:pass|详细信息：期望值:"+getoutMap.get(key).trim()+";实际值:"+receiveMap.get(key).trim());
					
					LogReport.writeLog(LogReport.getReportNameString(),
							"步骤ID:"+mapResult.size()+"|步骤名:"+key.trim()+
							"|结果:pass|详细信息：期望值:"+getoutMap.get(key).trim()+";实际值:"+receiveMap.get(key).trim()+"\r\n");

					LogReport.writeLog(LogReport.getReportNameString(),
							"****************************************************"+"\r\n");	
					

				}
				else if(getoutMap.get(key).trim().equals("XXX"))
				{
					mapResult.put(key.trim(), true);
					LogReport.writeLog(LogReport.getReportNameString(),
							"****************************************************"+"\r\n");	
					
					LogReport.writeLog(LogReport.getReportNameString(),
							"步骤ID:"+mapResult.size()+"|步骤名:"+key.trim()+
							"|结果:pass|详细信息：期望值:XXX;实际值:"+receiveMap.get(key).trim()+"\r\n");

					LogReport.writeLog(LogReport.getReportNameString(),
							"****************************************************"+"\r\n");	

				}
				else
				{
					mapResult.put(key.trim(), false);


					LogReport.writeLog(LogReport.getReportNameString(),
							"****************************************************"+"\r\n");	
					
					LogReport.writeLog(LogReport.getReportNameString(),
							"步骤ID:"+mapResult.size()+"|步骤名:"+key.trim()+
							"|结果:fail|详细信息：有相同的K:"+key+";期望Value:"+getoutMap.get(key)+
							";实际Value:"+receiveMap.get(key)+"\r\n");

					LogReport.writeLog(LogReport.getReportNameString(),
							"****************************************************"+"\r\n");	
				}
			}
			else
			{
				mapResult.put(key.trim(), false);

	
				LogReport.writeLog(LogReport.getReportNameString(),
						"****************************************************"+"\r\n");	

				LogReport.writeLog(LogReport.getReportNameString(),
						"步骤ID:"+mapResult.size()+"|步骤名:"+key.trim()+"|结果:fail|详细信息:没有相同的K:期望K:"+key+"\r\n");	

				LogReport.writeLog(LogReport.getReportNameString(),
						"****************************************************"+"\r\n");	
			}
			 
			
		}
		/*
		
		for(Entry<String, Boolean> entry: mapResult.entrySet())
		{
			if(entry.getValue()==false)
			{

				LogReport.writeLog(LogReport.getReportNameString(),
						"****************************************************"+"\r\n");	

				LogReport.writeLog(LogReport.getReportNameString(),
						"&&步骤ID："+(stepID+1)+";&&失败步骤名："
						+entry.getKey()+";&&步骤结果："+entry.getValue()+"\r\n");	

				LogReport.writeLog(LogReport.getReportNameString(),
						"****************************************************"+"\r\n");	
			}
			else
			{

				LogReport.writeLog(LogReport.getReportNameString(),
						"****************************************************"+"\r\n");	
	
				LogReport.writeLog(LogReport.getReportNameString(),
						"&&步骤ID："+(stepID+1)+";&&步骤名："+entry.getKey()+
						";&&步骤结果："+entry.getValue()+"\r\n");	

				LogReport.writeLog(LogReport.getReportNameString(),
						"****************************************************"+"\r\n");	
			}
			stepID++;
		}
		*/
		if(mapResult.containsValue(false)||mapResult.containsValue(null)||mapResult.size()==0)
		{

			LogReport.writeLog(LogReport.getReportNameString(),
					"****************************************************"+"\r\n");	

			LogReport.writeLog(LogReport.getReportNameString(),
					"/***用例执行状态:Fail***/"+"\r\n");	

			LogReport.writeLog(LogReport.getReportNameString(),
					"****************************************************"+"\r\n");	
		}
		else
		{
			LogReport.writeLog(LogReport.getReportNameString(),
					"****************************************************"+"\r\n");	
	
			LogReport.writeLog(LogReport.getReportNameString(),
					"/***用例执行状态:Pass***/"+"\r\n");	

			LogReport.writeLog(LogReport.getReportNameString(),
					"****************************************************"+"\r\n");	
		}
		

	}
}
