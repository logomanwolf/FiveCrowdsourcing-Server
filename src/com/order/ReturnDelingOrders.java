package com.order;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.OrderDao;
import com.entity.Deliveryorder;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class ReturnDelingOrders
 */
@WebServlet(description = "���������ж����б�", urlPatterns = { "/ReturnDelingOrders" })
public class ReturnDelingOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ReturnDelingOrders() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ģ��APP�������̼�id
		long merchantid = 1;
		
		OrderDao orderdao = new OrderDao();
		List<Deliveryorder> delingorders = new ArrayList<Deliveryorder>();
		delingorders = orderdao.getOrdersByStatus(4, merchantid);
		
		//ת����JSON��ʽ��д��APP��
		JSONArray jsonArray = JSONArray.fromObject(delingorders);
		System.out.println(jsonArray.toString());
		response.getWriter().append(jsonArray.toString());
	}

}
