package FuntionReport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


public class WriteReport {
	private String reportNameString;
	
	public String getReportNameString() {
		return reportNameString;
	}

	public void setReportNameString(String reportNameString) {
		this.reportNameString = reportNameString;
	}

	public void writeLog(String logFile,String content){
        try{
                File f= new File(logFile);
                if(!f.exists()){
                        if(f.createNewFile()){
                                System.out.println("create logFile");
                        }
                        else{
                                System.out.println("failed to create logFile");
                                return;
                        }
                }
                /*
                FileWriter writer = new FileWriter(logFile,true);
                writer.write(content);
                writer.close();
                */
                
    		    FileOutputStream o = new FileOutputStream(logFile,true);
    		    o.write(content.getBytes("GBK"));
    		    o.close();
    		    
        }catch(IOException e){
                e.printStackTrace();
        }
    } 


}
