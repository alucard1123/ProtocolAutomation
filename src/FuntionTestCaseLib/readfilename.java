package FuntionTestCaseLib;

import java.io.File;
import java.util.List;

public class readfilename {
	public List<String> ReadConfigFile(List<String> listfilename){
		File f=null;

		String path="src/reference/";      //��ȡ·��
		f=new File(path);                 //�½��ļ�ʵ��
		File[] list=f.listFiles();        /* �˴���ȡ�ļ����µ������ļ� */
		for(int i=0;i<list.length;i++)
		{
			System.out.println(list[i].getAbsolutePath());
			int len = list[i].getAbsolutePath().split("\\\\").length;
			String filename = list[i].getAbsolutePath().split("\\\\")[len-1].trim();
			System.out.println(filename);//��ӡȫ·�������Ը���Ϊ���Լ���Ҫ�ķ���
			listfilename.add(filename);
			
			
		}
		return listfilename;
    }

}
