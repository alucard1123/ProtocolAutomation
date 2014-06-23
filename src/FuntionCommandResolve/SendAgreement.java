package FuntionCommandResolve;

//import java.util.HashMap;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import FuntionOther.gb2312toUtf8;
import FuntionReport.WriteReport;



public class SendAgreement implements ISendAgreement
{
	private String sendcommand ;

	public String getSendcommand() {
		return sendcommand;
	}

	public void setSendcommand(String sendcommand) {
		this.sendcommand = sendcommand;
	}

	private String URL = new String();
	private String url = new String();
	private String Server = new String();
	private String Method = new String();
	@Override

	public  void  sendcommand(Map<String,String> ParameterIntput,
			WriteReport LogReport) throws Exception
	{
		final boolean DEBUG=false;
		String regex = "(\\%)";	
		int i = 0;
		sendcommand = "";
		gb2312toUtf8 gb2312toUtf8 = new gb2312toUtf8();
		URL = ParameterIntput.get("URL");

		Server = ParameterIntput.get("Server")+".fcg?";

		Method = "Method="+ParameterIntput.get("Method")+"&";

		for(Entry<String, String> entry: ParameterIntput.entrySet())
		{
			if(DEBUG)
			{
				System.out.println("ʵ�ʷ�������ADD��"+i+":K:"+entry.getKey()+";VALUE:"+entry.getValue());
			}		
			if(entry.getKey()=="URL")
			{
					
				url = "http://"+entry.getValue().split("//")[1].trim().split("/")[0].trim();
				
				if(DEBUG)
				{
					System.out.println("ʵ�ʷ�������url��"+url);
						System.out.println("ʵ�ʷ�������ADD��"+i+":"+sendcommand);
				}
			}
			if(entry.getKey()!="URL"&&entry.getKey()!="Server"
				&&entry.getKey()!="Method")
			{
				Matcher m = Pattern.compile(regex).matcher(entry.getValue());
				if(m.find())
				{
					System.out.println("####################$$$$$$$$$$$$$$%%%%%%%%%%%%%%^");
					String s= entry.getValue().split("%")[0]+"&nbsp"+
					entry.getValue().split("%")[1];
					entry.getValue().replace(entry.getValue(), s);
				}
				if(entry.getValue().length()<entry.getValue().getBytes().length)
				{
		        	String str=gb2312toUtf8.gb2312ToUtf8(entry.getValue());  
		              
		        	System.out.println(str);  
					entry.setValue(str);
					System.out.println("תGB2312��"+entry.setValue(str));  
				}
				sendcommand = sendcommand+entry.getKey()+"="+entry.getValue()+"&";


				
			}
	

			i++;
		}

		sendcommand = URL+Server+Method+sendcommand;
		sendcommand = sendcommand.substring(0, sendcommand.length()-1);
		System.out.println("ʵ�ʷ�������!!!��"+sendcommand);	
		LogReport.writeLog(LogReport.getReportNameString(), "ʵ�ʷ�������!!!��"+sendcommand+ "\r\n");	
/*		
		if(DEBUG)
		{
			url = "http://192.168.3.91/fcgi-bin/";
			String aa = "�½�";
			aa=java.net.URLDecoder.decode(aa,"GB2312");    
			aa=new String(aa.getBytes("GB2312"), "ISO-8859-1");
			sendcommand = "http://192.168.3.91/fcgi-bin/UserSystem.fcg?" +
					"Method=Register&Password=12346&MobilePhone=18706194425" +
					"CertType=1&IDNumber=620502198604012066&TrueName="+aa;
			
			System.out.println("TEST��������!!!��"+sendcommand);	
		}
*/
		WriteReport UrlParament = new WriteReport();
		UrlParament.writeLog("src/Reference/UrlParameter.txt",sendcommand+"\r\n");

		

		
	}


}
