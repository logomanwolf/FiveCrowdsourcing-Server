package com.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.OrderDao;
import com.entity.Deliveryorder;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class SendDelOrderServlet
 */
@WebServlet("/SendDelOrderServlet")
public class SendDelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendDelOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ģ��APP���͹����ľ�γ����Ϣ
		OrderDao orderdao = new OrderDao();
		double lat1 = 30.228719;
		double lng1 = 119.713279;
		List<Deliveryorder> delOrderList = new ArrayList<Deliveryorder>();
		
		delOrderList = orderdao.getNearByDelOrder(lat1, lng1);
		//ת����JSON��ʽ��д��APP��
		JSONArray jsonArray = JSONArray.fromObject(delOrderList);
		System.out.println(jsonArray.toString());
		response.getWriter().append(jsonArray.toString());
	}

}
