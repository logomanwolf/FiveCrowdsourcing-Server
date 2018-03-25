package com.ToolsClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

import com.dao.DelMethodDao;

public class EstimateUtils {
	public static String getEstimatedData(String url,String param){
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
	
	public static Long getEstimatedTime(String url,String param){
		String result = getEstimatedData(url,param);
		JSONObject json = new JSONObject(result);
		String result2 = ((json.getJSONArray("result")).opt(0)).toString();
		JSONObject json2 = new JSONObject(result2);
		Long estimatedtime = (long) ((json2.getJSONObject("duration")).getInt("value"));
		return estimatedtime;
	}
	
	public static int getEstimatedDistance(String url,String param){
		String result = getEstimatedData(url,param);
		JSONObject json = new JSONObject(result);
		String result2 = ((json.getJSONArray("result")).opt(0)).toString();
		JSONObject json2 = new JSONObject(result2);
		int estimateddistance =  ((json2.getJSONObject("distance")).getInt("value"));
		return estimateddistance;
	}

	public static double getEstimatedPrice(int estimateddistance, String ordertime, Long delmethodid) {
		DelMethodDao delmethoddao = new DelMethodDao();
		double price = 0;
		if(delmethoddao.getBasePrice(delmethodid)!=-1){
			price += delmethoddao.getBasePrice(delmethodid);
		}
		
		if(estimateddistance<=2000)
			price += 0;
		else if(estimateddistance<=10000)
			price = price + 2*(estimateddistance-2000)/1000;
		else 
			price = price +  16 + 6*(estimateddistance-10000)/5000;
		
		double time = Double.parseDouble(ordertime.substring(11, 13));
		if(time<=6)
			price += 4;
		else if(time>=22)
			price += 0;
		
		return price;
	}
	
}
