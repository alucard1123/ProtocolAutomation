package Business;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import Driver_Comm.CommHttp;
import FuntionBufferMemory.BufferMemory;
import FuntionBufferMemory.GetParameter;
import FuntionCommandResolve.ResultSolve;
import FuntionCommandResolve.decodeRComm;
import FuntionReport.WriteReport;


public class Run {
	
	/*readTxtFile?????????????????????????????????????д?????
	 * sendcommdfilePath-UrlParameter.txt??? ???????????????·??  CommHttp ???????????  WriteReportд???????
	 * ??????
	 */
	
	 public static void readTxtFile(String sendcommdfilePath,CommHttp commhttp,
			 WriteReport LogReport)
	 	{
	        try {
	        		boolean Debug = false;
	                String encoding="GBK";
	                File file=new File(sendcommdfilePath);
	                WriteReport receivefile = new WriteReport();
	                if(file.isFile() && file.exists())
	                { //?ж??????????
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//???????????
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null)
	                    {
	                        System.out.println("commhttp.CommConnect(lineTxt)::"+lineTxt);

	                		/*
	                		 * ????????????????
	                		 */	
	                			String returnComme="";
      
	                			try 
	                			{
									returnComme = commhttp.CommSend(lineTxt);
									
								}
	                			catch (Exception ehttp)
	                			{
									// TODO: handle exception
	                				ehttp.printStackTrace();;
								}

	
	                			
	                			//??????????????????д??returncommd.txt???
	                			if(Debug)
	                			{
	                				returnComme="{\"Command\":\"QueryBetDetail\",\"ReturnCode\":\"00000\",\"ReturnErrorMsg\":\"XXXX\",\"TotalCount\":\"128\",\"ResData\":\"{\"BetTime\":\"2012-04-05\",\"LotteryType\":\"2\",\"Agencyid\":\"1234\",\"OrderNumber\":\"12345\",\"BetData\":\"1;1;2;1;01|02|03|04|05|06|-12|-\"}\"}";
	                			}
	                			receivefile.writeLog("src/Reference/returncommd.txt",returnComme+ "\r\n");

	                    }
	                    read.close();
	                }
	                else
	                {
	                	System.out.println("??????????UrlParameter.txt???");//UrlParameter.txt????????????????????????
	                }
	        	} 
	        catch (Exception e) 
	        	{
	        	System.out.print("?????????????");	
	            e.printStackTrace();
	        	}
	     
	 	}

	 public static void readreturncommdFile(String filePath,LinkedHashMap<String,String> receiveCommendMap,
			 BufferMemory bufferMemory,WriteReport LogReport,String[] TableNameArray,String[]CommArray,
			 GetParameter getParameter){
	        try {

	                String encoding="GBK";
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ //?ж??????????
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//???????????
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    int i = 0;
	                    while((lineTxt = bufferedReader.readLine()) != null)
	                    {
	                        System.out.println(lineTxt);

	                        if(lineTxt!=null)
	            			{
	                        	
	                        String TableName = TableNameArray[i];	
	                        String outputStr = "output_"+CommArray[i].trim().toString();
	                        Map<String, String> ParameterOutput = getParameter.getOutput(outputStr,TableName);
	                    	Set<String> getkeySet = ParameterOutput.keySet();
	                    	String ExpectResult="";
	            			for(String key:getkeySet)
	            			{

	            				ExpectResult = ExpectResult+"\""+key+"\"??\""+ParameterOutput.get(key)+"\"??";

	            			}
	                  		LogReport.writeLog(LogReport.getReportNameString(),
	                				"****************************************************"+"\r\n");	
	                		LogReport.writeLog(LogReport.getReportNameString(),
	                				"????ID:"+i+";??????:"+CommArray[i]+";???????:"+
	                				TableNameArray[i]+"\r\n");	
	                		LogReport.writeLog(LogReport.getReportNameString(),
	                				"****************************************************"+"\r\n");	
	                		LogReport.writeLog(LogReport.getReportNameString(),
	                				"?????????:"+lineTxt+"\r\n");	
	                		LogReport.writeLog(LogReport.getReportNameString(),
	                				"****************************************************"+"\r\n");	
	             
	                		if(!ExpectResult.equals(""))
	                		{

	            		
	            			LogReport.writeLog(LogReport.getReportNameString(),
	            					"*****???????????:{"+ExpectResult.substring(0, ExpectResult.length()-1)+"}"+ "\r\n");	
	            			
	                		}
	                		else {
	                			LogReport.writeLog(LogReport.getReportNameString(),
		            					"*****???????????:Null;????????"+ "\r\n");	
							}
	            		
	            			receiveCommendMap=decodeRComm.decodeJsonComm(lineTxt.trim());
	            	        for(Entry<String, String> entry: receiveCommendMap.entrySet())
	            			{ 	
	            				System.out.println("CHENJIE????Map:K"+entry.getKey()+";CHENJIE:Value:"+entry.getValue());
	            			}
	            			bufferMemory.BufferMemoryMap(receiveCommendMap, ParameterOutput);
	            			ResultSolve.resultSolve(ParameterOutput,receiveCommendMap,LogReport);
	            			}

	            			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	            			LogReport.writeLog(LogReport.getReportNameString(),"%%%%%%%%%%%%%%%%%%%%%%%"+ "\r\n");
	            			Date d = new Date(); 
	            			System.out.println("End%:"+d);
	            			//LogReport.writeLog(LogReport.getReportNameString(),"End%:"+d+ "\r\n");
	            			Thread.sleep(100);
	            			i++;
	                    }
	                    read.close();
	        }else{
	            System.out.println("??????????returncommd.txt???");//returncommd.txt????????????Э??
	        }
	        } catch (Exception e) {
	            System.out.println("???returncommd.txt??????????");
	            e.printStackTrace();
	        }
	     
	    }
	 
	 
	
	 

}
