package com.validate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.MerchantDao;
import com.dao.RunnerDao;

/**
 * Servlet implementation class RunnerStatus
 */
@WebServlet("/RunnerStatus")
public class RunnerStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String result;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RunnerStatus() {
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
		long runnerId = Long.parseLong(request.getParameter("runnerId"));
		RunnerDao runnerDao = new RunnerDao();
		System.out.println(runnerId);
		String status=runnerDao.getRunnerStatus(runnerId);
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
