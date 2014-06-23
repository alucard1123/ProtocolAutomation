package FuntionTestCaseLib;

import java.io.File;
import java.util.List;

public class readfilename {
	public List<String> ReadConfigFile(List<String> listfilename){
		File f=null;

		String path="src/reference/";      //获取路径
		f=new File(path);                 //新建文件实例
		File[] list=f.listFiles();        /* 此处获取文件夹下的所有文件 */
		for(int i=0;i<list.length;i++)
		{
			System.out.println(list[i].getAbsolutePath());
			int len = list[i].getAbsolutePath().split("\\\\").length;
			String filename = list[i].getAbsolutePath().split("\\\\")[len-1].trim();
			System.out.println(filename);//打印全路径，可以更改为你自己需要的方法
			listfilename.add(filename);
			
			
		}
		return listfilename;
    }

}
