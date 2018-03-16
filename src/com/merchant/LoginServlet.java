package com.merchant;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.MerchantDao;
import com.entity.Merchant;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");

		JSONObject jsonObject = null;
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		System.out.println(phone + " " + password);
		String result = null;
		MerchantDao merchantDao = new MerchantDao();
		try {
			Merchant merchant = merchantDao.checkMerchant(phone, password);
			// �ж������Ƿ���ȷ
			if (merchant != null) {
				result = "success";
				// ����ת��
				jsonObject = new JSONObject(merchant);
				jsonObject.put("result", result);
				System.out.println(jsonObject);
			} else {
				// ����ת��
				result = "false";
				jsonObject = new JSONObject();
				jsonObject.put("result", result);
				
			}
		}catch(Exception e) {
			// ����ת��
			result="false";
			jsonObject = new JSONObject();
			jsonObject.put("result", result);		
		}
		
		response.getWriter().append(jsonObject.toString());
	}

}