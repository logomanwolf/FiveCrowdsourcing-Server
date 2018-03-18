package com.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.ToolsClass.EstimateUtils;
import com.alibaba.fastjson.JSON;
import com.dao.OrderDao;
import com.entity.Deliveryorder;
import com.entity.Merchant;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class EditOrder
 */
@WebServlet(description = "�½����񵥣�����Ԥ��ʱ��ͼ۸�", urlPatterns = { "/AddDelOrderServlet" })
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**
		 * ��ֹ����
		 */
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		/**
		 * �������json���ݶ�ȡ������
		 */
		 InputStream is = request.getInputStream();
		 BufferedReader reader = new BufferedReader(new InputStreamReader(is,
		 "utf-8"));
		 String line = null;
		 StringBuffer sb = new StringBuffer();
		 while ((line = reader.readLine()) != null) {
		 sb.append(line);
		 }
		 String result;
		 System.out.println(sb.toString());
		 String DeliveryorderJson = sb.toString();
		 System.out.println(DeliveryorderJson);//
		// ����sbΪjson���ݰ�����merchantid,address,latitude,longitude,phone,storename,tofgid
		 Deliveryorder order = JSON.parseObject(DeliveryorderJson,
		 Deliveryorder.class);
		 System.out.println(order.getMerchantid());
		
		 // ģ��APP���͹����Ķ���JSON����
		 Long merchantid = order.getMerchantid();
		 String cusname = order.getCusName();
		 String cusphone = order.getCusPhone();
		 String cusaddress = order.getCusAddress();
		 double cuslat = order.getCuslat();
		 double cuslng = order.getCuslog();
		 String things = order.getThings();
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String ordertime = df.format(System.currentTimeMillis());
		 // Deliveryorder deliveryorder = new Deliveryorder();
		 OrderDao orderdao = new OrderDao();
		 // ���Ԥ��ʱ��ͼ۸�
		 int extraTime = 15;// Ԥ������ʱ��
		 Double[] location = orderdao.getMerchantLocation(merchantid);//
		// ����̻���γ��
		 String url = "http://api.map.baidu.com/routematrix/v2/riding";// ���нӿ�
		 // �ӿڲ���
		 String param = "output=json&origins=" + cuslat + "," + cuslng
		 + "&destinations=" + location[0] + "," + location[1]
		 + "&ak=Gsj9D1Ih7RV00jypSLk8osnircS4NRPA";
		 Long estimatedtime = (long)
		 (Math.ceil(EstimateUtils.getEstimatedTime(
		 url, param) / 60) + extraTime);// ��λ������
		 int estimateddistance = EstimateUtils.getEstimatedDistance(url,
		 param);
		 double estimatedtotalprice = EstimateUtils.getEstimatedPrice(
		 estimateddistance, ordertime,
		 orderdao.getDelmethodid(merchantid));
		
		 // ת�������͵����󲢱��浽���ݿ�
		 Deliveryorder deliveryorderNew = new Deliveryorder();
		 deliveryorderNew.setMerchantid(merchantid);
		 deliveryorderNew.setDelmethodid(orderdao.getDelmethodid(merchantid));
		 System.out.println(orderdao.getDelmethodid(merchantid));
		 deliveryorderNew.setCusName(cusname);
		 deliveryorderNew.setCusAddress(cusaddress);
		 deliveryorderNew.setCusPhone(cusphone);
		 deliveryorderNew.setThings(things);
		 deliveryorderNew.setEstimatedtime(estimatedtime);
		 deliveryorderNew.setEstimatedtotalprice(estimatedtotalprice);
		 deliveryorderNew.setDistance(estimateddistance);
		 deliveryorderNew.setOrdertime(ordertime);
		 deliveryorderNew.setCuslat(cuslat);
		 deliveryorderNew.setCuslog(cuslng);
		 // deliveryorderNew.setStatus(1);
		 deliveryorderNew.setStatus(2);
		 String message = null;
		 try {
		 orderdao.insertDeliveryorder(deliveryorderNew);
		 result = "success";
		 } catch (Exception e) {
		 result = "failed";
		 }
		
		 // ��Ԥ��ʱ��ͼ۸񷢸�APP
		 // String timeAndPriceJSONStr =
		 // "{\"estimatedtime\":"+estimatedtime+",\"estimatedtotalprice\":"+
		 // +estimatedtotalprice+",\"result\":"+result+"}";
		 // System.out.println(timeAndPriceJSONStr);
		 // JSONObject timeAndPriceJSON = new JSONObject(timeAndPriceJSONStr);
		 // response.getWriter().append(timeAndPriceJSON.toString());
		 /*
		 * //������͵���� long delorderid =
		 * orderdao.getDeliveryorderId(deliveryorderNew);
		 *
		 * deliveryorderNew.setDelorderid(delorderid);
		 */

		List<Deliveryorder> deliveryorders = new ArrayList<>();
		//OrderDao orderdao = new OrderDao();
		 deliveryorders = orderdao.getAllInactiveDelOrder(merchantid);
//		deliveryorders = orderdao.getAllInactiveDelOrder(1L);
		//String result;
		if (deliveryorders == null)
			result = "failed";
		else if (deliveryorders.size() == 0)
			result = "failed";
		else
			result = "success";
		JSONArray jsonArray = JSONArray.fromObject(deliveryorders);
		String delOrdersStr = JSON.toJSONString(deliveryorders);
		System.out.println(jsonArray.toString());
		response.getWriter().append(jsonArray.toString());

	}

}
