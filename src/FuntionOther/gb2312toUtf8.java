package FuntionOther;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class gb2312toUtf8 {
    public String gb2312ToUtf8(String str) {   
    	  
        String urlEncode = "" ;   
  
        try {   
  
            urlEncode = URLEncoder.encode (str, "UTF-8" );   
  
        } catch (UnsupportedEncodingException e) {   
  
            e.printStackTrace();   
  
        }   
  
        return urlEncode;   
  
    }  

}
