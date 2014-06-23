package Driver_Comm;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class CommHttp implements ICommHttp{

	public String CommReadLine(String regex,String retuenRequest){
		// TODO Auto-generated method stub

	        if (retuenRequest != null) 
	        {

	           // do something useful with the response
	        	System.out.println("0:"+retuenRequest.split(":")[0].trim());
	        	System.out.println("0regex:"+regex);
	           if(retuenRequest.split(":")[0].trim().equals(regex))
	           {
	              System.out.println("服务器返回应答正确："+retuenRequest);
	               return retuenRequest;
	           }
	           else
	           {
	              System.out.println("****用例状态：失败；失败结果：服务器返回应答错误："+retuenRequest);
	              return retuenRequest;
	           }
	                
	                
	             
	        }
	        return null;
 
        
	}

	@Override
	public String CommSend(String request) throws Exception  {
		// TODO Auto-generated method stub
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(request); 
         String cmpStr = "HTTP/1.1 200 OK";
    	//"http://localhost:8080/TestLogin/testLogin"
         System.out.println("executing request " + httpget.getURI());
        	// Pass local context as a parameter
      try
      {
        	HttpResponse response = httpclient.execute(httpget);
        	StatusLine toCmpStr = response.getStatusLine();
        	if(!toCmpStr.toString().trim().equals(cmpStr))
        	{
        		throw new Exception("通信连接失败！:"+toCmpStr.toString());
        	}
        	

        	HttpEntity entity = response.getEntity();
        	String returnRequest="";
        
        	System.out.println("----------------------------------------");
        if (entity != null) 
        {
        		
        	 BufferedReader reader = new BufferedReader(
	                    new InputStreamReader(entity.getContent(),"UTF-8"));
  
        	
        	System.out.println("start send another request");

        	
        	
        	

        	System.out.println("sent over");
        	System.out.println("Debug:: send request");
        	System.out.println("----------------------------------------");   
        	// When HttpClient instance is no longer needed, 
        	// shut down the connection manager to ensure
        	// immediate deallocation of all system resources
        	try{
        	
        	//returnRequest = new String(reader.readLine().getBytes("ISO-8859-1"),"UTF-8");
        	returnRequest = reader.readLine();
        	System.out.println("*****服务器返回应答："+returnRequest);
        	//httpclient.getConnectionManager().shutdown();  		
        	return returnRequest;
        	
        	}catch (IOException ex) {

                // In case of an IOException the connection will be released
                // back to the connection manager automatically
                throw ex;
                
            } catch (RuntimeException ex) {

                // In case of an unexpected exception you may want to abort
                // the HTTP request in order to shut down the underlying 
                // connection and release it back to the connection manager.
                httpget.abort();
                throw ex;
                
            } finally {

                // Closing the input stream will trigger connection release
                reader.close();
                
            }
        	
        }
        else
        {
        	
        	System.out.println("*****服务器返回应答：Null");
        	return null;
        }
      }
      catch (IOException e) 
      {
		// TODO: handle exception
    	  throw e;
    	  
      }
      catch(RuntimeException e)
      {
    	  throw e;
      }
      finally 
      {
    	  httpclient.getConnectionManager().shutdown();  
      }

        // Create a local instance of cookie store
            
	}

	

	public boolean CommConnect(String URI)throws Exception{
		
		HttpClient httpclient = new DefaultHttpClient();
		System.out.println("url:"+URI);
        HttpGet httpget = new HttpGet(URI); 
        // Execute HTTP request
        //System.out.println("executing request " + httpget.getURI());
      try 
      {
			HttpResponse response = httpclient.execute(httpget);

        
        System.out.println("ccccc response::"+response);

        System.out.println("----------------------------------------");
        //System.out.println(response.getStatusLine());
        String cmpStr = "HTTP/1.1 200 OK";
        StatusLine toCmpStr = response.getStatusLine();
        if(toCmpStr.toString().equals(cmpStr)){
        	System.out.println("连接成功！！！");
        	httpclient.getConnectionManager().shutdown();
        	System.out.println("cccccccccccccccjjjjjjjjjjjjjjjOK");	
        	return true;
        }
        else{
        	
        	System.out.println("ohk");
        	System.out.println("HTTP/1.1 200 OK");
        	System.out.println("****用例状态：失败！;失败结果：通信连接失败！！返回状态信息：："+response.getStatusLine());
        	httpclient.getConnectionManager().shutdown();
        	System.out.println("cccccccccccccccjjjjjjjjjjjjjjjfail");	
        	return false;
        }
	}
	 catch (Exception e) {
		// TODO: handle exception
		 e.printStackTrace(); 
	}
	return false;


}
	

}
