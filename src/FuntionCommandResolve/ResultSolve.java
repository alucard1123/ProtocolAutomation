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
					System.out.println("CHENJIE::"+"����ID:"+mapResult.size()+"|������:"+key.trim()+
							"|���:pass|��ϸ��Ϣ������ֵ:"+getoutMap.get(key).trim()+";ʵ��ֵ:"+receiveMap.get(key).trim());
					
					LogReport.writeLog(LogReport.getReportNameString(),
							"����ID:"+mapResult.size()+"|������:"+key.trim()+
							"|���:pass|��ϸ��Ϣ������ֵ:"+getoutMap.get(key).trim()+";ʵ��ֵ:"+receiveMap.get(key).trim()+"\r\n");

					LogReport.writeLog(LogReport.getReportNameString(),
							"****************************************************"+"\r\n");	
					

				}
				else if(getoutMap.get(key).trim().equals("XXX"))
				{
					mapResult.put(key.trim(), true);
					LogReport.writeLog(LogReport.getReportNameString(),
							"****************************************************"+"\r\n");	
					
					LogReport.writeLog(LogReport.getReportNameString(),
							"����ID:"+mapResult.size()+"|������:"+key.trim()+
							"|���:pass|��ϸ��Ϣ������ֵ:XXX;ʵ��ֵ:"+receiveMap.get(key).trim()+"\r\n");

					LogReport.writeLog(LogReport.getReportNameString(),
							"****************************************************"+"\r\n");	

				}
				else
				{
					mapResult.put(key.trim(), false);


					LogReport.writeLog(LogReport.getReportNameString(),
							"****************************************************"+"\r\n");	
					
					LogReport.writeLog(LogReport.getReportNameString(),
							"����ID:"+mapResult.size()+"|������:"+key.trim()+
							"|���:fail|��ϸ��Ϣ������ͬ��K:"+key+";����Value:"+getoutMap.get(key)+
							";ʵ��Value:"+receiveMap.get(key)+"\r\n");

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
						"����ID:"+mapResult.size()+"|������:"+key.trim()+"|���:fail|��ϸ��Ϣ:û����ͬ��K:����K:"+key+"\r\n");	

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
						"&&����ID��"+(stepID+1)+";&&ʧ�ܲ�������"
						+entry.getKey()+";&&��������"+entry.getValue()+"\r\n");	

				LogReport.writeLog(LogReport.getReportNameString(),
						"****************************************************"+"\r\n");	
			}
			else
			{

				LogReport.writeLog(LogReport.getReportNameString(),
						"****************************************************"+"\r\n");	
	
				LogReport.writeLog(LogReport.getReportNameString(),
						"&&����ID��"+(stepID+1)+";&&��������"+entry.getKey()+
						";&&��������"+entry.getValue()+"\r\n");	

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
					"/***����ִ��״̬:Fail***/"+"\r\n");	

			LogReport.writeLog(LogReport.getReportNameString(),
					"****************************************************"+"\r\n");	
		}
		else
		{
			LogReport.writeLog(LogReport.getReportNameString(),
					"****************************************************"+"\r\n");	
	
			LogReport.writeLog(LogReport.getReportNameString(),
					"/***����ִ��״̬:Pass***/"+"\r\n");	

			LogReport.writeLog(LogReport.getReportNameString(),
					"****************************************************"+"\r\n");	
		}
		

	}
}
