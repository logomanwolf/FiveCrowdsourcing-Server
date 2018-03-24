package com.validate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.MerchantDao;

/**
 * Servlet implementation class MerchantStatus
 */
@WebServlet("/MerchantStatus")
public class MerchantStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String result;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MerchantStatus() {
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

		JSONObject jsonObject = null;
		long merchantId = Long.parseLong(request.getParameter("merchantId"));
		MerchantDao merchantDao = new MerchantDao();
		System.out.println(merchantId);
		String status=merchantDao.getMerchantStatus(merchantId);
	
		if(status!=null)
		{
			result = "success";
			// 数据转换
			jsonObject = new JSONObject();
			jsonObject.put("status", status);
			jsonObject.put("result", result);
			System.out.println(jsonObject);
		}
		else{
			result = "failed";
			// 数据转换
			jsonObject = new JSONObject();
			jsonObject.put("result", result);
			System.out.println(jsonObject);
		}
		response.getWriter().append(jsonObject.toString());
	}

}
