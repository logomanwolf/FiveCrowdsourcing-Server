package com.ToolsClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class LocationUtils {    
	public static String SendGET(String url,String param){
		   String result="";//���ʷ��ؽ��
		   BufferedReader read=null;//��ȡ���ʽ��
		   
		   try {
		    //����url
		    URL realurl=new URL(url+"?"+param);
		    //������
		    URLConnection connection=realurl.openConnection();
		     // ����ͨ�õ���������
		             connection.setRequestProperty("accept", "*/*");
		             connection.setRequestProperty("connection", "Keep-Alive");
		             //��������
		             connection.connect();
		             // ���� BufferedReader����������ȡURL����Ӧ
		             read = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
		             String line;//ѭ����ȡ
		             while ((line = read.readLine()) != null) {
		                 result += line;
		             }
		   } catch (IOException e) {
		    e.printStackTrace();
		   }finally{
		    if(read!=null){//�ر���
		     try {
		      read.close();
		     } catch (IOException e) {
		      e.printStackTrace();
		     }
		    }
		   }
		   return result; 
		 }
	
	/*public static void main(String[] args){
		String url = "http://api.map.baidu.com/routematrix/v2/driving";
		String param = "output=json&origins=40.45,116.34&destinations=40.34,116.45&ak=Gsj9D1Ih7RV00jypSLk8osnircS4NRPA";
		System.out.println(SendGET(url,param));
	} */   
}
