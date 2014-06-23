package FuntionReport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;



import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class jxlexcel
{
	private static int casecount;
	private static int passcount;
	private static int failcount;
	private static int NotCompletecount; 
		
	public static int getNotCompletecount() {
		return NotCompletecount;
	}

	public static void setNotCompletecount(int notCompletecount) {
		NotCompletecount = notCompletecount;
	}

	public int getCasecount() {
		return casecount;
	}

	public void setCasecount(int casecount) {
		jxlexcel.casecount = casecount;
	}

	public int getPasscount() {
		return passcount;
	}

	public void setPasscount(int passcount) {
		jxlexcel.passcount = passcount;
	}

	public int getFailcount() {
		return failcount;
	}

	public void setFailcount(int failcount) {
		jxlexcel.failcount = failcount;
	}

	
	public static void writeexcel(String excelfilePath,String logfilePath,String reporttime) throws IOException
	{
		casecount=0;
		passcount=0;
		failcount=0;
		NotCompletecount=0;
		OutputStream os = null;  
	     try {  
	    	  
	            // ����Workbook����  
	            os = new FileOutputStream(excelfilePath);  
	            WritableWorkbook wwb = Workbook.createWorkbook(os);  
	  
	            // ����Excel sheet  
	            WritableSheet sheet = wwb.createSheet("summary sheet", 0);  
	  
	            // ���ñ����ʽ ��ARIAL���� 18���� �Ӵ� б�壩 
	            WritableFont wfTitle = new jxl.write.WritableFont(  
	                    WritableFont.ARIAL, 18, WritableFont.BOLD, true);  
	            WritableCellFormat wcfTitle = new WritableCellFormat(wfTitle);  
	            // ����ˮƽ���뷽ʽ �����У� 
	            wcfTitle.setAlignment(Alignment.CENTRE);  
	            // ���ô�ֱ���뷽ʽ �����У� 
	            wcfTitle.setVerticalAlignment(VerticalAlignment.CENTRE);  
	            // �����Ƿ��Զ�����  
	            wcfTitle.setWrap(true);  
	  
	            // �ϲ�A1->C2 (��һ�еĵ�һ�е������еĵڶ���) 
	            sheet.mergeCells(0, 0, 3, 1);  
	            Label titleCell = new Label(0, 0, "Test Summary", wcfTitle);  
	            sheet.addCell(titleCell);  
	            // ���ñ����ʽ ��ARIAL���� 18���� �Ӵ� б�壩 
	    
	            
	            WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,  
	                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                    Colour.BLUE);  
	            WritableCellFormat wcf = new WritableCellFormat(wf);  
	  
	            // A3  ����1�е�3�У�
	            Label labelCell = new Label(0, 2, "��������",wcf);  
	            sheet.addCell(labelCell);  
	            // B3  
	            Label labelCellFmt1 = new Label(1, 2,  
	                    "ͨ��", wcf);  
	            sheet.addCell(labelCellFmt1); 
	            //C3
	            Label labelCellFmt2 = new Label(2, 2,  
	                    "ʧ��", wcf);  
	            sheet.addCell(labelCellFmt2); 
	            //D3
	            Label labelCellFmt3 = new Label(3, 2,  
	                    "δ���", wcf);  
	            sheet.addCell(labelCellFmt3); 
	            //A5
	            Label labelCellTitle1 = new Label(0, 4, "����ID",wcf);  
	            sheet.addCell(labelCellTitle1);              
	            //B5
	            Label labelCellTitle2 = new Label(1, 4, "������",wcf);  
	            sheet.addCell(labelCellTitle2);     
	            //C5
	            Label labelCellTitle3 = new Label(2, 4, "����ģ��",wcf);  
	            sheet.addCell(labelCellTitle3);    
	            //D5
	            Label labelCellTitle4 = new Label(3, 4, "״̬",wcf);  
	            sheet.addCell(labelCellTitle4);  	            
	            LinkedList<String> linkedList =new LinkedList<String>();

				LinkedHashMap<Integer,LinkedList<String>> linkedHashMap= new LinkedHashMap<Integer,LinkedList<String>>();
				linkedHashMap = readLog(linkedList,linkedHashMap,logfilePath);
	            NumberFormat nf = new NumberFormat("0");  
	            WritableCellFormat wcfN = new WritableCellFormat(nf);  
				//A4
	            jxl.write.Number labelNF1 = new jxl.write.Number(0, 3, casecount,  
	                    wcfN); 
	            sheet.addCell(labelNF1);
				//B4
	            jxl.write.Number labelNF2 = new jxl.write.Number(1, 3, passcount,  
	                    wcfN); 
	            sheet.addCell(labelNF2);           
				//C4
	            jxl.write.Number labelNF3 = new jxl.write.Number(2, 3, failcount,  
	                    wcfN); 
	            sheet.addCell(labelNF3);                
				//D4
	            jxl.write.Number labelNF4 = new jxl.write.Number(3, 3, NotCompletecount,  
	                    wcfN); 
	            sheet.addCell(labelNF4);   
	            WritableFont wfcell = new WritableFont(WritableFont.ARIAL, 10,  
	                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                    Colour.RED);  
	            WritableCellFormat wcfcell = new WritableCellFormat(wfcell);              
	            
	            for(int row=0;row<linkedHashMap.size();row++)
	            {
	            	for(int clo=0;clo<linkedHashMap.get(row).size();clo++)
	            	{
	            		Label labelCells=null;
	            		if(linkedHashMap.get(row).get(clo).equals("Fail"))
	            		{
	            		 labelCells = new Label(clo, row+5, linkedHashMap.get(row).get(clo),wcfcell); 
	            		 System.out.print("CHENJIEDEBUG:logfilePath:"+reporttime+"Report.txt"+"\n");
	                     WritableHyperlink link = new WritableHyperlink(clo, row+5, new File(reporttime+"Report.txt"));
	                     link.setDescription(linkedHashMap.get(row).get(clo));
	                     sheet.addHyperlink(link);
	            		}
	            		else
	            		{
	            		 labelCells = new Label(clo, row+5, linkedHashMap.get(row).get(clo)); 
	            		}
	            		
	            		sheet.addCell(labelCells);  
	            	}
	               
	            }

	            //�ȵ���write();�ٵ���close();  
	            wwb.write();  
	            wwb.close();  
	            os.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (null != os) {  
	                os.close();  
	            }  
	        }  
	}

	private static LinkedHashMap<Integer,LinkedList<String>> readLog(LinkedList<String> linkedList,
			LinkedHashMap<Integer,LinkedList<String>>linkedMap, String filePath) 
	{
		 try 
		 {
             String encoding="GBK";
             File file=new File(filePath);
             if(file.isFile() && file.exists())
             { //�ж��ļ��Ƿ����
                 InputStreamReader read = new InputStreamReader(
                 new FileInputStream(file),encoding);//���ǵ������ʽ
                 BufferedReader bufferedReader = new BufferedReader(read);
                 String lineTxt = null;
                 int j=0;
                 while((lineTxt = bufferedReader.readLine()) != null)
                 {
                     System.out.println(lineTxt);
                     if(lineTxt.contains("����ID")&&lineTxt.contains("������")&&lineTxt.contains("����ģ��"))
                     { 
                    	 linkedList = new LinkedList<String>();
                    	 casecount++;
                    	 for(int i=0;i<lineTxt.split(";").length;i++)
                    	 {
                    		 linkedList.add(lineTxt.split(";")[i].split(":")[1].trim());
                    		
                    		
                    	 }

                     }
                     else if(lineTxt.contains("����ִ��״̬"))
                     {
                    	 String strs=lineTxt.split(":")[1];
                    	 System.out.print("CHENJIEDEBUG::����ִ��״̬:"+strs+"\n");
                    	 String str1 = strs.substring(0, strs.length()-4);
                    	 linkedList.add(str1);
                    	 linkedMap.put(j, linkedList);
                    	 j++;   
                    	 if(str1.equals("Pass"))
                    	 {
                    		 passcount++;
                    	 }
                    	 else if(str1.equals("Fail"))
                    	 {
							failcount++;
                    	 }
                    	 else 
                    	 {
                    		 NotCompletecount++;
                    	 }
                     }
                     else 
                     {
                    	 System.out.print("Log"+"\n");
                     }
                     
                     System.out.print("CHENJIEDEBUG::linkedList:"+linkedList+"\n");
                 }
                 read.close();
                 
             }
             else
             {
            	 System.out.println("�Ҳ���ָ�����ļ�");
     
             }
		 } 
		 catch (Exception e) 
		 {
			 System.out.println("��ȡ�ļ����ݳ���");
			 e.printStackTrace();
		 }
		return linkedMap;
		
	}


}
