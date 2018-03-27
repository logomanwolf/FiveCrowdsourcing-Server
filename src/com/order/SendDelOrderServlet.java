package com.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.dao.OrderDao;
import com.entity.Deliveryorder;
import com.entity.Runner;

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
		/**
		 * 防止乱码
		 */
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		request.setCharacterEncoding("UTF-8");
		/**
		 * 把请求的json数据读取出来。
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
		 String runnerJson = sb.toString();
		 Runner runner = JSON.parseObject(runnerJson,
				 Runner.class);
		 System.out.println(runner.getRunnerid());
		//模拟APP发送过来的经纬度信息
		OrderDao orderdao = new OrderDao();
//		Long runnerid = (long) 1;
		double lat1 = runner.getLaititude();
		double lng1 = runner.getLongtitude();
		
//		lat1 = Double.parseDouble(request.getParameter("lat1"));
//		 lng1 = Double.parseDouble(request.getParameter("lng1"));
		try
		{
		//更新跑腿人位置
		Double[] location = new Double[2];
		location[0] = lat1;location[1] = lng1;
		ServletContext application = request.getServletContext();
		application.setAttribute(runner.getRunnerid().toString(), location);
		
		List<Deliveryorder> delOrderList = new ArrayList<Deliveryorder>();
		
		delOrderList = orderdao.getNearByDelOrder(lat1, lng1);
	
		//转换成JSON格式，写给APP。
	
		JSONArray jsonArray = JSONArray.fromObject(delOrderList);
		System.out.println(jsonArray.toString());
		//System.out.println(((Double[])application.getAttribute("1"))[0]);
		response.getWriter().append(jsonArray.toString());
		}catch(Exception e)
		{
			
		}
		
	}

}
