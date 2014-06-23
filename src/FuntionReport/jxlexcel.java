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
	    	  
	            // 构建Workbook对象  
	            os = new FileOutputStream(excelfilePath);  
	            WritableWorkbook wwb = Workbook.createWorkbook(os);  
	  
	            // 构建Excel sheet  
	            WritableSheet sheet = wwb.createSheet("summary sheet", 0);  
	  
	            // 设置标题格式 （ARIAL字体 18号字 加粗 斜体） 
	            WritableFont wfTitle = new jxl.write.WritableFont(  
	                    WritableFont.ARIAL, 18, WritableFont.BOLD, true);  
	            WritableCellFormat wcfTitle = new WritableCellFormat(wfTitle);  
	            // 设置水平对齐方式 （居中） 
	            wcfTitle.setAlignment(Alignment.CENTRE);  
	            // 设置垂直对齐方式 （居中） 
	            wcfTitle.setVerticalAlignment(VerticalAlignment.CENTRE);  
	            // 设置是否自动换行  
	            wcfTitle.setWrap(true);  
	  
	            // 合并A1->C2 (第一列的第一行到第三列的第二行) 
	            sheet.mergeCells(0, 0, 3, 1);  
	            Label titleCell = new Label(0, 0, "Test Summary", wcfTitle);  
	            sheet.addCell(titleCell);  
	            // 设置标题格式 （ARIAL字体 18号字 加粗 斜体） 
	    
	            
	            WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,  
	                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                    Colour.BLUE);  
	            WritableCellFormat wcf = new WritableCellFormat(wf);  
	  
	            // A3  （第1列第3行）
	            Label labelCell = new Label(0, 2, "用例总数",wcf);  
	            sheet.addCell(labelCell);  
	            // B3  
	            Label labelCellFmt1 = new Label(1, 2,  
	                    "通过", wcf);  
	            sheet.addCell(labelCellFmt1); 
	            //C3
	            Label labelCellFmt2 = new Label(2, 2,  
	                    "失败", wcf);  
	            sheet.addCell(labelCellFmt2); 
	            //D3
	            Label labelCellFmt3 = new Label(3, 2,  
	                    "未完成", wcf);  
	            sheet.addCell(labelCellFmt3); 
	            //A5
	            Label labelCellTitle1 = new Label(0, 4, "用例ID",wcf);  
	            sheet.addCell(labelCellTitle1);              
	            //B5
	            Label labelCellTitle2 = new Label(1, 4, "用例名",wcf);  
	            sheet.addCell(labelCellTitle2);     
	            //C5
	            Label labelCellTitle3 = new Label(2, 4, "所属模块",wcf);  
	            sheet.addCell(labelCellTitle3);    
	            //D5
	            Label labelCellTitle4 = new Label(3, 4, "状态",wcf);  
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

	            //先调用write();再调用close();  
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
             { //判断文件是否存在
                 InputStreamReader read = new InputStreamReader(
                 new FileInputStream(file),encoding);//考虑到编码格式
                 BufferedReader bufferedReader = new BufferedReader(read);
                 String lineTxt = null;
                 int j=0;
                 while((lineTxt = bufferedReader.readLine()) != null)
                 {
                     System.out.println(lineTxt);
                     if(lineTxt.contains("用例ID")&&lineTxt.contains("用例名")&&lineTxt.contains("所属模块"))
                     { 
                    	 linkedList = new LinkedList<String>();
                    	 casecount++;
                    	 for(int i=0;i<lineTxt.split(";").length;i++)
                    	 {
                    		 linkedList.add(lineTxt.split(";")[i].split(":")[1].trim());
                    		
                    		
                    	 }

                     }
                     else if(lineTxt.contains("用例执行状态"))
                     {
                    	 String strs=lineTxt.split(":")[1];
                    	 System.out.print("CHENJIEDEBUG::用例执行状态:"+strs+"\n");
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
            	 System.out.println("找不到指定的文件");
     
             }
		 } 
		 catch (Exception e) 
		 {
			 System.out.println("读取文件内容出错");
			 e.printStackTrace();
		 }
		return linkedMap;
		
	}


}
