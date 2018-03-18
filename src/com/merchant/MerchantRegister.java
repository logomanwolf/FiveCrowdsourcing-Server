package com.merchant;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.MerchantDao;
import com.entity.Merchant;

public class MerchantRegister extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MerchantRegister() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// String phone = request.getParameter("phone");
		// String password=request.getParameter("password");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		Merchant merchant = new Merchant();
		merchant.setPhone(phone);
		merchant.setPassword(password);
		MerchantDao merchantDao = new MerchantDao();
		Integer result = merchantDao.insertAMerchant(merchant);
		// ��ʾ�ɹ����
		String message = null;
		if (result > 0)
			message = "success";
		else 
			message="false";
		response.getWriter().append(message);

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
