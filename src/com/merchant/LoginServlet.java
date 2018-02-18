package com.merchant;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.entity.Merchant;
import com.utils.JsonTools;


import com.utils.DatabaseUtil;

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
		String message = null;//相关信息
		String sql = "select password from merchant where phone = '"+phone+"'";
		try {
			List list = DatabaseUtil.query(sql); // 数据库查询操作
//			result.getRow();
			
			if (list.size()>0) {
				merchant = (Merchant)(list.get(0));
				if (merchant.getPassword().equals(password)) {
					result="success";
					message = "登陆成功";
					System.out.println(message);
					JSONObject jsonObject = new JSONObject(merchant);
					jsonObject.put("result", result);
					response.getWriter().append(jsonObject.toString());
				} else {
					result="false";
					message = "登录失败，登录密码错误";
					System.out.println(message);
				}
			} else {
				message = "该登陆账号未注册";
				System.out.println(message);
			}
		} catch (SQLException e) {
			message = "数据库查询错误";
			System.out.println(message);
			e.printStackTrace();
		}
		//封装merchant
		//merchant.setName("张哲铖");
		//merchant.setPhone("15957196907");
		//数据转换
		
		//System.out.println(jsonObject);
		
//		jsonObject
//		String jsonString=null;  
//        try {
//			jsonString = JsonTools.createJsonString("merchant",merchant);
//			System.out.println(jsonString);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
