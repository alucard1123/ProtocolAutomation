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
	
	/*readTxtFile方法主要用于向服务器发送请求及将接收到的应答写入文件
	 * sendcommdfilePath-UrlParameter.txt文件 存放请求消息的文件路径  CommHttp 发送请求的类  WriteReport写报告的类
	 * 无返回值
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
	                { //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null)
	                    {
	                        System.out.println("commhttp.CommConnect(lineTxt)::"+lineTxt);

	                		/*
	                		 * 此处添加发送命令消息
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

	
	                			
	                			//将返回的服务器应答消息写入returncommd.txt文件
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
	                	System.out.println("找不到指定的UrlParameter.txt文件");//UrlParameter.txt文件存放发送给服务器的请求消息
	                }
	        	} 
	        catch (Exception e) 
	        	{
	        	System.out.print("读取文件内容出错！");	
	            e.printStackTrace();
	        	}
	     
	 	}

	 public static void readreturncommdFile(String filePath,LinkedHashMap<String,String> receiveCommendMap,
			 BufferMemory bufferMemory,WriteReport LogReport,String[] TableNameArray,String[]CommArray,
			 GetParameter getParameter){
	        try {

	                String encoding="GBK";
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
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

	            				ExpectResult = ExpectResult+"\""+key+"\"：\""+ParameterOutput.get(key)+"\"、";

	            			}
	                  		LogReport.writeLog(LogReport.getReportNameString(),
	                				"****************************************************"+"\r\n");	
	                		LogReport.writeLog(LogReport.getReportNameString(),
	                				"用例ID:"+i+";用例名:"+CommArray[i]+";所属模块:"+
	                				TableNameArray[i]+"\r\n");	
	                		LogReport.writeLog(LogReport.getReportNameString(),
	                				"****************************************************"+"\r\n");	
	                		LogReport.writeLog(LogReport.getReportNameString(),
	                				"实际接收消息:"+lineTxt+"\r\n");	
	                		LogReport.writeLog(LogReport.getReportNameString(),
	                				"****************************************************"+"\r\n");	
	             
	                		if(!ExpectResult.equals(""))
	                		{

	            		
	            			LogReport.writeLog(LogReport.getReportNameString(),
	            					"*****期望应答消息为:{"+ExpectResult.substring(0, ExpectResult.length()-1)+"}"+ "\r\n");	
	            			
	                		}
	                		else {
	                			LogReport.writeLog(LogReport.getReportNameString(),
		            					"*****期望应答消息为:Null;请检查脚本！"+ "\r\n");	
							}
	            		
	            			receiveCommendMap=decodeRComm.decodeJsonComm(lineTxt.trim());
	            	        for(Entry<String, String> entry: receiveCommendMap.entrySet())
	            			{ 	
	            				System.out.println("CHENJIE发送Map:K"+entry.getKey()+";CHENJIE:Value:"+entry.getValue());
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
	            System.out.println("找不到指定的returncommd.txt文件");//returncommd.txt文件存放接口返回的协议
	        }
	        } catch (Exception e) {
	            System.out.println("读取returncommd.txt文件内容出错");
	            e.printStackTrace();
	        }
	     
	    }
	 
	 
	
	 

}
