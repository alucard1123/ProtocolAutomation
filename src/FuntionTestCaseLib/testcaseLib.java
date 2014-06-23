package FuntionTestCaseLib;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import FuntionBufferMemory.GetParameter;

public class testcaseLib {
	/**
	 * @return
	 */
	public Map <Integer ,String[]> caselib()
	{
		Map <Integer ,String[]> consoleMap= new LinkedHashMap<Integer, String[]>();
		GetParameter getparameters = new GetParameter();
		readfilename Rfilename = new readfilename();
		List<String> listtableName = new LinkedList<String>();
		listtableName = Rfilename.ReadConfigFile(listtableName);
		for(int i=0;i<listtableName.size();i++)
		{
			if(listtableName.get(i).matches(".*.xml"))
			{
				System.out.print("i"+i+"/listtableName.get(i)£º"+listtableName.get(i)+"\r\n");
				
				consoleMap = getparameters.getConsoleTable(listtableName.get(i), consoleMap);
			}
		}
		return consoleMap;
		
	}

}
