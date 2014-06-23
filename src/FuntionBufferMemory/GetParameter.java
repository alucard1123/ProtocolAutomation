package FuntionBufferMemory;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import FuntionOther.Json;

public class GetParameter implements IGetParameter
{


	@Override
	public Map<String, String> getInput(String CommStr,String TableName) {
		// TODO Auto-generated method stub
		
		try
		{
			Map<String, String> mapInput = new LinkedHashMap<String, String>();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//����DocumentBuilderFactoryһ���½ӿ�
			DocumentBuilder db = factory.newDocumentBuilder();//����DocumentBuilder�½ӿ�
			Document doc =  db.parse(new File("src/Reference/"+TableName+".xml"));
			Element elmtInfo =doc.getDocumentElement();
			NodeList nodes = elmtInfo.getChildNodes();//�����ļ��а����������ӽڵ㲢�����νṹ���
			 String valueString="";
			
			for(int i=0;i<nodes.getLength();i++)
			{
				 Node result = nodes.item(i);//���нڵ�
				 //�ҵ��ļ��еġ�txtbook���ڵ�
	 
				 
				 if(result.getNodeType()==Node.ELEMENT_NODE&&result.getNodeName().equals(CommStr))
				 {
					 //System.out.println(i+":"+result.getTextContent());
					 
					 NodeList ns = result.getChildNodes();//�����ӽڵ�
					 for(int j=0;j<ns.getLength();j++)
					 {
						 Node record = ns.item(j);//�ӽڵ�record
						 if(record.getNodeType()==Node.ELEMENT_NODE)
						 {
							 /*
							  * ����ӽڵ����滹�нڵ����
							  */
							 NodeList nodeList = doc.getElementsByTagName(record.getNodeName().trim());
							 int count = nodeList.getLength();
							 if(record.getTextContent().trim().equals(null)&&count>0)
							 {
								 
								Map<String, String> MaptoJson = new LinkedHashMap<String, String>();
								 NodeList nss = record.getChildNodes();//�������ӽڵ�
								 for(int k=0;k<nss.getLength();k++)
								 {
									 Node nssrecord = nss.item(k);//���ӽڵ�
									 if(nssrecord.getNodeType()==Node.ELEMENT_NODE)
									 {
										 MaptoJson.put(nssrecord.getNodeName().trim(), nssrecord.getTextContent().trim());
									 }
								 }
								 valueString=Json.encodeJsonObj(MaptoJson);
								 System.out.print("ChenjieDebug::valueString:"+valueString+"\n");
							 }
							 /*
							  *�ӽڵ�������û����һ���Ľڵ� 
							  */
							 else
							 {
								 valueString=record.getTextContent().trim();
							 }
							 mapInput.put(record.getNodeName().trim(), valueString);
							 System.out.print("Input::K:"+record.getNodeName().trim()+";value:"+valueString+"\n");


							 
	
						 }
						 
					 }
				 }
				 
				 if(i==nodes.getLength()-1&&mapInput.containsKey(null))
				 {
					 System.out.println("******����״̬��ʧ�ܣ�ʧ��ԭ�������ļ���δ�ҵ���Ӧ�ı�����Ϣ���������ԣ�"+CommStr+";");
				 }

				
			}

			 return  mapInput;
			
		}
	// TODO Auto-generated catch block
		catch( ParserConfigurationException  e)
		{
			e.printStackTrace();
		}
		catch(SAXException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{

			e.printStackTrace();
		}

			return null;
				
	}

	@Override
	public Map<String, String> getOutput(String CommStr,String TableName) {
		// TODO Auto-generated method stub
		try
		{
			Map<String, String> mapOutput = new LinkedHashMap<String, String>();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//����DocumentBuilderFactoryһ���½ӿ�
			DocumentBuilder db = factory.newDocumentBuilder();//����DocumentBuilder�½ӿ�
			Document doc =  db.parse(new File("src/Reference/"+TableName+".xml"));
			Element elmtInfo =doc.getDocumentElement();
			NodeList nodes = elmtInfo.getChildNodes();//�����ļ��а����������ӽڵ㲢�����νṹ���
			
			for(int i=0;i<nodes.getLength();i++)
			{
				 Node result = nodes.item(i);//�������е��ӽڵ�
				
				 //�ҵ��ļ��еġ�txtbook���ڵ�
				 if(result.getNodeType()==Node.ELEMENT_NODE&&result.getNodeName().equals(CommStr))
				 {
					 //System.out.println(i+":"+result.getTextContent());
					 
					 NodeList ns = result.getChildNodes();
					 for(int j=0;j<ns.getLength();j++)
					 {
						 Node record = ns.item(j);
						 if(record.getNodeType()==Node.ELEMENT_NODE)
						 {
							 mapOutput.put(record.getNodeName().trim(), record.getTextContent().trim());
							 System.out.print("Output::K:"+record.getNodeName().trim()+";value:"+record.getTextContent().trim()+"\n");
						 }
						 
					 }
				 }
				
			}

			return mapOutput;
		}
	// TODO Auto-generated catch block
		catch( ParserConfigurationException  e)
		{
			e.printStackTrace();
		}
		catch(SAXException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{

			e.printStackTrace();
		}

			return null;
	}

	@Override
	public Map<Integer, String[]> getConsoleTable() {
		// TODO Auto-generated method stub
		try
		{

			Map<Integer, String[]> mapConsole = new LinkedHashMap<Integer, String[]>();
			String[] Array = new String[2];
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//����DocumentBuilderFactoryһ���½ӿ�
			DocumentBuilder db = factory.newDocumentBuilder();//����DocumentBuilder�½ӿ�
			Document doc =  db.parse(new File("src/Reference/Parament-Console.xml"));
			Element elmtInfo =doc.getDocumentElement();
			NodeList nodes = elmtInfo.getChildNodes();//�����ļ��а����������ӽڵ㲢�����νṹ���
			for(int i=0;i<nodes.getLength();i++)
			{
				Node result = nodes.item(i);//�������е��ӽڵ�
				System.out.println("i::"+i+";"+result);
				if(result.getNodeType()==Node.ELEMENT_NODE&&
						result.getNodeName().equals("List"))
				{
					NodeList ns = result.getChildNodes();
					int count = 0;
					int k = 0;

					 for(int j=0;j<ns.getLength()-1;j=j+2)
					 {

						 if(count%2==0)
						 {
							 Node record2 = ns.item(j+1);

							 Array[1]=record2.getTextContent().trim();
		
							 
						 }
						 else
						 {
							 Node record1=ns.item(j+1);

							 Array[0]=record1.getTextContent().trim();
							 
							 
						 }			
						 if( Array[0]!=null&&Array[1]!=null)
						 {
							  mapConsole.put(k, Array);
							  Array = new String[2];
							  k++;
						 }
							 
						count++; 
					 }
				}
			}
		
		return mapConsole;
		}
		// TODO Auto-generated catch block
		catch( ParserConfigurationException  e)
		{
			e.printStackTrace();
		}
		catch(SAXException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{

			e.printStackTrace();
		}

			return null;
	}

	public Map<Integer, String[]> getConsoleTable(String tablename,Map<Integer, String[]>consoleMap)
	{
		try 
		{
			System.out.print(" j=consoleMap.size():"+consoleMap.size()+"\r\n");
			Integer j=consoleMap.size();
			String[] Array = new String[2];
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//����DocumentBuilderFactoryһ���½ӿ�
			DocumentBuilder db = factory.newDocumentBuilder();//����DocumentBuilder�½ӿ�
			Document doc =  db.parse(new File("src/Reference/"+tablename));
			Element elmtInfo =doc.getDocumentElement();
			NodeList nodes = elmtInfo.getChildNodes();//�����ļ��а����������ӽڵ㲢�����νṹ���
			for(int i=0;i<nodes.getLength();i++)
			{
				Node result = nodes.item(i);//�������е��ӽڵ�
				System.out.print("i:"+i+"/nodes.item(i).getNodeName:"+result.getNodeName()+"\r\n");
				if(result.getNodeType()==Node.ELEMENT_NODE&&
						result.getNodeName().matches("input_.*"))
				{
					Array = new String[2];
					System.out.print("@if i:"+i+"/nodes.item(i)��getNodeName:"+result.getNodeName()+"\r\n");	
					Array[0]=tablename.replace(".xml", "").trim();
					
					Array[1]=result.getNodeName().split("_")[1].trim();
					System.out.print("j:"+j+"/Array[1]:"+Array[1]+"\r\n");
					consoleMap.put(j, Array);
					j++;
				}

			}
			
		} 

		catch (ParserConfigurationException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//����DocumentBuilder�½ӿ�
	
		catch (SAXException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		catch(IOException e3)
		{
			e3.printStackTrace();
		}

		return consoleMap;
		
	}
}
