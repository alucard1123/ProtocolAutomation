package FuntionCommandResolve;

import java.net.URLEncoder;
import java.util.Map;

import FuntionOther.Json;
import FuntionReport.WriteReport;

public class SendHttpJson extends SendAgreement {
	private String access_token="";

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public  void  sendcommand(Map<String,String> ParameterIntput,
			WriteReport LogReport) throws Exception
			{
				setAccess_token(ParameterIntput.get("access_token"));
				String URL = ParameterIntput.get("URL");
				String Address = ParameterIntput.get("Address");
				String mapremoveStr1 = ParameterIntput.remove("URL");
				System.out.print("mapremoveStr1:"+mapremoveStr1);
				String mapremoveStr2 =ParameterIntput.remove("Address");
				System.out.print("mapremoveStr2:"+mapremoveStr2);
				String mapremoveStr3 =ParameterIntput.remove("access_token");
				System.out.print("mapremoveStr3:"+mapremoveStr3);
				String encodejson=Json.encodeJsonObj(ParameterIntput);
				String sendcommand = URL+Address+"?access_token="
				+getAccess_token()+"&requestParam="+URLEncoder.encode(encodejson,"UTF-8");
				System.out.println("实际发送命令!!!："+sendcommand);	
				LogReport.writeLog(LogReport.getReportNameString(), "实际发送命令!!!："+sendcommand+ "\r\n");	
				WriteReport UrlParament = new WriteReport();
				UrlParament.writeLog("src/Reference/UrlParameter.txt",sendcommand+"\r\n");
				
			}
}
