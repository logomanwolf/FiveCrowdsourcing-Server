package com.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.OrderDao;
import com.dao.RunnerDao;
import com.entity.Deliveryorder;
import com.entity.Runner;

public class taskAccount extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public taskAccount() {
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
		String result = request.getParameter("delorderid");
		//��ʾ�ɹ����
		boolean success = false;
		Deliveryorder deliveryorder = new Deliveryorder();
		if (result != null) {
			OrderDao orderDao = new OrderDao();
			deliveryorder = orderDao.getOrderById(Long.parseLong(result));
			success = true;
		}
		//�����Ӽۣ�ʱ��Ӽۣ�������
		Double weightPrice = calWeightPrice(deliveryorder);
		Double timePrice = calTimePrice(deliveryorder);
		Double basePrice = calBasePrice(deliveryorder);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		deliveryorder.setOuttime(sdf.format(Calendar.getInstance().getTime()));
		deliveryorder
				.setExtraprice(deliveryorder.getExtraprice() + weightPrice);
		deliveryorder.setStatus(5);
		OrderDao orderDao = new OrderDao();
		RunnerDao runnerDao=new RunnerDao();
		Runner runner=runnerDao.getRunnerById(deliveryorder.getRunnerid());
		//ʵ���ⵥ�Ķ���
		Double realPay=deliveryorder.getExtraprice()+weightPrice;
		//������ӣ����÷ּ�10
		runner.setBalance(runner.getBalance()+realPay);
		runner.setIntegral(runner.getIntegral()+10);
		//�����ｲ��Ӧorder��״̬��Ϊ5�����ҽ�runner����Ӧ�����ͻ��ָı�
		if (orderDao.updateOrder(deliveryorder) == 0  || runnerDao.updateRunner(runner) )
			success = false;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", success);
		jsonObject.put("deliveryorder", deliveryorder);
		// ʱ��μӼۣ������Ӽۣ�������
		jsonObject.put("weightPrice", weightPrice);
		jsonObject.put("timePrice", timePrice);
		jsonObject.put("basePrice", basePrice);
		response.getWriter().append(jsonObject.toString());
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

	// �����Ӽ�+����Ӽ�+ʱ��μӼ�
	// toDo:����Ӽ�

	// ���������
	private Double calBasePrice(Deliveryorder deliveryorder) {
		return deliveryorder.getEstimatedtotalprice()
				- calTimePrice(deliveryorder);
	}

	// ����ʱ��μӼ�
	private Double calTimePrice(Deliveryorder deliveryorder) {
		Double timePrice = 0.0;
		String orderTime = deliveryorder.getOrdertime();
		Calendar orderCal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date;
		try {
			date = sdf.parse(orderTime);
			orderCal.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (orderCal.HOUR_OF_DAY >= 0 && orderCal.HOUR_OF_DAY < 7)
			timePrice = 8.0;
		else if (orderCal.HOUR_OF_DAY >= 22 && orderCal.HOUR_OF_DAY < 24)
			timePrice = 4.0;
		return timePrice;
	}

	// ���������Ӽ�
	private Double calWeightPrice(Deliveryorder deliveryorder) {
		Double weightPrice = 0.0;
		if (deliveryorder.getTrueweight() > 5
				&& deliveryorder.getTrueweight() <= 10)
			weightPrice = Math.ceil(deliveryorder.getTrueweight() - 5);
		else if (deliveryorder.getTrueweight() <= 5)
			;
		else
			weightPrice = Math.ceil((deliveryorder.getTrueweight() - 10) / 5);
		return weightPrice;

	}

}
