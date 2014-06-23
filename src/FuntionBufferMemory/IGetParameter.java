package FuntionBufferMemory;
import java.util.Map;


public interface IGetParameter 
{
	public Map<String,String> getOutput(String CommStr,String TableName); 
	
	public Map<String,String> getInput(String CommStr,String TableName);
	
	public 	Map<Integer , String[]>getConsoleTable();

}
