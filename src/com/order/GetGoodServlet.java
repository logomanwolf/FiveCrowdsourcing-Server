package com.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.OrderDao;
import com.entity.Deliveryorder;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetGoodServlet
 */
@WebServlet("/GetGoodServlet")
public class GetGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGoodServlet() {
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
		String delorderid = request.getParameter("delorderid");
		String runnerid = request.getParameter("runnerid");
		System.out.println(delorderid);
		OrderDao orderdao = new OrderDao();
	    orderdao.updateOrderAfterGoodGetted(Long.parseLong(delorderid),Long.parseLong(runnerid));
		List<Deliveryorder> delOrderList = new ArrayList<Deliveryorder>();
		delOrderList = orderdao.getOrdersByStatusRunner(3,Long.parseLong(runnerid));
		//转换成JSON格式，写给APP	
		JSONArray jsonArray = JSONArray.fromObject(delOrderList);
		response.getWriter().append(jsonArray.toString());
	
//		response.getWriter().append(json.toString());
	}

}
