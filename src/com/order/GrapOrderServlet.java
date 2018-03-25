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
 * Servlet implementation class GrapOrderServlet
 */
@WebServlet(description = "抢单", urlPatterns = { "/GrapOrderServlet" })
public class GrapOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrapOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
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
		int ans = orderdao.updateOrderAfterOrderGrabbed(Long.parseLong(delorderid),Long.parseLong(runnerid));
		String result = "";
		if(ans==-1)
			result="单子已经被抢走了！";
		else if(ans!=0)
			result = "抢单成功！";
		else
			result = "抢单失败~";
		ServletContext application = request.getServletContext();
		Double[] location = new Double[2];
		location=(Double[]) application.getAttribute(runnerid);
		double lat1 = location[0];
		double lng1 = location[1];
		List<Deliveryorder> delOrderList = new ArrayList<Deliveryorder>();
		try {
			delOrderList = orderdao.getNearByDelOrder(lat1, lng1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//转换成JSON格式，写给APP	
		JSONArray jsonArray = JSONArray.fromObject(delOrderList);
		response.getWriter().append(jsonArray.toString());
	
//		response.getWriter().append(json.toString());
	}

}
