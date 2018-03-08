package com.merchant;

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
import com.dao.MerchantDao;
import com.entity.Merchant;

/**主要用于将商户入住的信息存储到数据库
 * Servlet implementation class Step1Servlet
 */
@WebServlet("/Step1Servlet")
public class Step1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Step1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 /**
         * 防止乱码
         */
        response.setContentType("text/html;charset=utf-8");
        response. setCharacterEncoding("UTF-8");
        request. setCharacterEncoding("UTF-8");
        /**
         * 把请求的json数据读取出来。
         */
        InputStream is=request.getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(is, "utf-8"));
        String line=null;
        StringBuffer sb=new StringBuffer();
        while((line=reader.readLine())!=null){
            sb.append(line);
        }
       String MerchantJson=sb.toString();       
        System.out.println(MerchantJson);//其中sb为json数据包含了merchantid,address,latitude,longitude,phone,storename,tofgid
        Merchant merchant=JSON.parseObject(MerchantJson, Merchant.class);
    	MerchantDao merchantDao=new MerchantDao();
        String result ="failed";
        if(merchantDao.updateMerchant(merchant)){               
        		result="success";
        }
        	
        /**
         * 将address,latitude,longitude,phone,storename,tofgid存入数据库中（待实现），成功后返回一条成功信息
         */
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("result", result);
        response.getWriter().append(jsonObject.toString());       
	}

}
