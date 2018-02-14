package com.merchant;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.entity.Merchant;
import com.utils.JsonTools;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		response.setCharacterEncoding("UTF-8");
		Merchant merchant=new Merchant();
		String phone=request.getParameter("phone");
		String password=request.getParameter("password");
		System.out.println(phone+" "+password);
		String result=null;
		//判断密码是否正确
		if(true)
			result="success";
		else
			result="false";
		//封装merchant
		merchant.setName("张哲铖");
		merchant.setPhone("15957196907");
		//数据转换
		JSONObject jsonObject = new JSONObject(merchant);
		jsonObject.put("result", result);
		System.out.println(jsonObject);
		
//		jsonObject
//		String jsonString=null;  
//        try {
//			jsonString = JsonTools.createJsonString("merchant",merchant);
//			System.out.println(jsonString);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		response.getWriter().append(jsonObject.toString());
	}

}
