package com.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import com.dao.OrderDao;
import com.entity.Deliveryorder;
/**
 * Servlet implementation class EditOrder
 */
@WebServlet(description = "�����̼ҵ��������ɷ��͸������˵�����", urlPatterns = { "/AddDelOrderServlet" })
public class AddDelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDelOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		OrderDao orderdao = new OrderDao();
		//ģ��APP���͹����Ķ���JSON����
		Deliveryorder deliveryorderV = new Deliveryorder();
		deliveryorderV.setMerchantid((long) 1);
		deliveryorderV.setDelmethodid((long) 1);
		deliveryorderV.setEstimatedtime((long) 35);
		deliveryorderV.setEstimatedtotalprice((double) 23.3);
		deliveryorderV.setOrdertime("2015-02-12");
		JSONObject jsonObject = null;
		jsonObject = new JSONObject(deliveryorderV);
		
		//ת�������͵����󲢱��浽���ݿ�
		Deliveryorder deliveryorderNew = new Deliveryorder();
		deliveryorderNew.setMerchantid(jsonObject.getLong("merchantid"));
		deliveryorderNew.setDelmethodid(jsonObject.getLong("delmethodid"));
		deliveryorderNew.setEstimatedtime(jsonObject.getLong("estimatedtime"));
		deliveryorderNew.setEstimatedtotalprice(jsonObject.getDouble("estimatedtotalprice"));
		deliveryorderNew.setOrdertime(jsonObject.getString("ordertime"));
		
		orderdao.insertDeliveryorder(deliveryorderNew);
		
		/*//������͵����
		long delorderid = orderdao.getDeliveryorderId(deliveryorderNew);
		
		deliveryorderNew.setDelorderid(delorderid);*/
	} 

}
