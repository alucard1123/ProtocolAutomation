package Business;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import Driver_Comm.CommHttp;
import FuntionBufferMemory.BufferMemory;
import FuntionBufferMemory.GetParameter;
import FuntionBufferMemory.MyStack;
import FuntionCommandResolve.SendAgreement;
import FuntionCommandResolve.SendHttpJson;
import FuntionReport.WriteReport;
import FuntionReport.jxlexcel;
import FuntionTestCaseLib.testcaseLib;

public class AutoTest {

	    
	    
	    
	    /**
	     * @param args
	     */

	public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
		WriteReport LogReport = new WriteReport();
		BufferMemory bufferMemory = new BufferMemory();	
		GetParameter getParameter = new GetParameter();
		SendAgreement sendAgreement = new SendAgreement();
		SendHttpJson sendCwlProApi = new SendHttpJson();
		testcaseLib caseLib = new testcaseLib();
		String TableName="";
		String excelfilePath="";
		String reporttime="";

		//String ExpectResult = "";
		String sendcommdfilePath = "src/Reference/UrlParameter.txt";
		String receivecommdfilePath = "src/Reference/returncommd.txt";
		String consolefilepath="src/Reference/Parament-Console.xml";
		File f = new File(sendcommdfilePath); 
		File receivefile = new File(receivecommdfilePath);
		File consolef = new File(consolefilepath);
		if(f.exists())
		    f.delete();
		if(receivefile.exists())
			receivefile.delete();
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Map <Integer ,String[]> ConsoleMap =null;
		if(consolef.exists())
		{
			 ConsoleMap = getParameter.getConsoleTable();
		}
		else
		{
			 ConsoleMap = caseLib.caselib();
		}
		
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


		String[] TableNameArray = new String[ConsoleMap.size()];
		String[]CommArray = new String[ConsoleMap.size()];
		for (int i=0;i<ConsoleMap.size();i++) 
		{
			CommArray[i] = ConsoleMap.get(i)[1].trim();
			TableNameArray[i]=ConsoleMap.get(i)[0].trim();
		}
		for(int i = 0;i<CommArray.length;i++)
		{
			TableName = TableNameArray[i];
			Date d1 = new Date(); 
			if(i==0)
			{
				Calendar dates = Calendar.getInstance();
				reporttime = new String();
				reporttime = Integer.toString(dates.get(Calendar.YEAR))+
				Integer.toString(dates.get(Calendar.MONTH)+1)+
				Integer.toString(dates.get(Calendar.DATE))+
				Integer.toString(dates.get(Calendar.HOUR_OF_DAY))+
				Integer.toString(dates.get(Calendar.MINUTE))+
				Integer.toString(dates.get(Calendar.SECOND));
			
				String reportNameString =  "./log/"+reporttime+"Report.txt";
				LogReport.setReportNameString(reportNameString);
				excelfilePath= "./log/"+reporttime+"summary.xls";
			}


			System.out.println("Begin%:"+d1);	
			//LogReport.writeLog(LogReport.getReportNameString(), "Begin%:"+d1+ "\r\n");


			
			String inputStr ="input_"+ CommArray[i].trim().toString();	

			
			System.out.println("inputStr:"+inputStr);
			LogReport.writeLog(LogReport.getReportNameString(), "inputStr:"+inputStr+ "\r\n");	
			System.out.println("TableName:"+TableName);	
			LogReport.writeLog(LogReport.getReportNameString(), "TableName:"+TableName+ "\r\n");	

			
			
			
			Map<String, String> ParameterInput = getParameter.getInput(inputStr,TableName);
			//Map<String, String> ParameterOutput = getParameter.getOutput(outputStr,TableName);
			
/////////////////////////////////////////////////run!!!
			
			if(i==0)
			{
				Map<String, MyStack> buffermemoryMap = new LinkedHashMap<String, MyStack>();
			    bufferMemory.setBufferMemory(buffermemoryMap);	
			}
			else 
			{
				Map<String, MyStack> buffermemoryMap = bufferMemory.getBufferMemory();

				for (Entry<String, MyStack> entry: buffermemoryMap.entrySet()) 
				{
					String stackValue = new String();
					if(ParameterInput.containsKey(entry.getKey()))
					{
						MyStack stack = new MyStack();
						stack = entry.getValue();
						for (int j = stack.s_size; j >0;j--)
						{
							stackValue=stack.pop();
							//System.out.println("j::"+j+";K::"+entry.getKey()+";stackvalue::"+stackValue+".");
							if (stackValue.equals(null)||stackValue.equals("XXX"))
							{
								
							}
							else {
								//System.out.println("entry.getKey()::"+entry.getKey()+";stackValue::"+stackValue+".");
								ParameterInput.remove(entry.getKey());
								ParameterInput.put(entry.getKey(), stackValue);
							}
						}
					}
				}
				//////////////////////////////////////////////////////////

			}
			try 
			{
				if(TableName.contains("CwlProApi"))
				{
					sendCwlProApi.sendcommand(ParameterInput, LogReport);
				}
				else 
				{
					sendAgreement.sendcommand(ParameterInput,LogReport);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
		}
		//协议发送 存储收到的协议
		CommHttp commhttp = new  CommHttp();
		Run.readTxtFile(sendcommdfilePath, commhttp, LogReport);
		//返回协议解析 比较
		LinkedHashMap<String,String> receiveCommendMap = new LinkedHashMap<String,String>();
		Run.readreturncommdFile(receivecommdfilePath, receiveCommendMap, 
				bufferMemory, LogReport, TableNameArray, CommArray, getParameter);
		//写汇总结果到excel
		jxlexcel.writeexcel(excelfilePath, LogReport.getReportNameString(),reporttime);
    	
    }

}




