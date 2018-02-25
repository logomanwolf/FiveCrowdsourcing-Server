package com.merchant;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.*;

import com.dao.MerchantDao;
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
		MerchantDao merchantDao = new MerchantDao();
		response.setCharacterEncoding("UTF-8");
		Merchant merchant=new Merchant();
		String phone=request.getParameter("phone");
		String password=request.getParameter("password");
		System.out.println(phone+" "+password);
		String result = "false";
		try {
			merchant = merchantDao.Login(phone, password);
			if(merchant!=null)
				result = "true";
			JSONObject jsonObject = new JSONObject(merchant);
			jsonObject.put("result", result);
			response.getWriter().append(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
