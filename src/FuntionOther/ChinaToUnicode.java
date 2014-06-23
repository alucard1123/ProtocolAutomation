package FuntionOther;

public class ChinaToUnicode {
	public static String chinaToUnicode(String str) {  
        StringBuffer result = new StringBuffer();  
        for (int i = 0; i < str.length(); i++) {  
            int chr1 = (char) str.charAt(i);  
            result.append("\\u" + Integer.toHexString(chr1));  
  
        }  
        return result.toString();  
    }

}
