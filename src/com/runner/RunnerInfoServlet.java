package com.runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;

import com.dao.RunnerDao;

import com.entity.Runner;

/**
 * Servlet implementation class MerchantInfoServlet
 */
@WebServlet("/RunnerInfoServlet")
public class RunnerInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RunnerInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 /**
         * ��ֹ����
         */
        response.setContentType("text/html;charset=utf-8");
        response. setCharacterEncoding("UTF-8");
        request. setCharacterEncoding("UTF-8");
        /**
         * �������json���ݶ�ȡ������
         */
        InputStream is=request.getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(is, "utf-8"));
        String line=null;
        StringBuffer sb=new StringBuffer();
        while((line=reader.readLine())!=null){
            sb.append(line);
        }
        String result;
        System.out.println(sb.toString());
       String RunnerJson=sb.toString();       
        System.out.println(RunnerJson);//����sbΪjson���ݰ�����merchantid,address,latitude,longitude,phone,storename,tofgid
        Runner runner=JSON.parseObject(RunnerJson, Runner.class);
        RunnerDao runnerdao=new RunnerDao();
        boolean message=runnerdao.updateRunner(runner);
        if(message) {
            result ="success";
        }
        else result="false";
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("result", result);
        response.getWriter().append(jsonObject.toString());  
	}

}
