package Driver_Comm;

public interface ICommHttp {
	public String CommReadLine(String regex,String retuenRequest) ;
	public String CommSend(String request)throws Exception;
	public boolean CommConnect(String URI)throws Exception;

}
