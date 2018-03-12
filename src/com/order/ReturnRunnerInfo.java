package com.order;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.OrderDao;
import com.dao.RunnerDao;
import com.entity.Deliveryorder;
import com.entity.Runner;
import org.json.JSONObject;
/**
 * Servlet implementation class ReturnRunnerInfo
 */
@WebServlet(description = "������������Ϣ", urlPatterns = { "/ReturnRunnerInfo" })
public class ReturnRunnerInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReturnRunnerInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ģ��APP������delorderIid
		Long delorderid = (long) 6;
		
		OrderDao orderdao = new OrderDao();
		RunnerDao runnerdao = new RunnerDao();
		
		Deliveryorder deliveryorder = orderdao.getOrderById(delorderid);
		Runner runner = new Runner();
		Long runnerid = deliveryorder.getRunnerid();
		System.out.println(runnerid);
		runner = runnerdao.getRunnerById(runnerid);
		
		String name = runner.getName();//����
		String phone = runner.getPhone();//�绰
		
		//ģ�����application�����еľ�γ������
		ServletContext application = request.getServletContext();
		Double[] location = new Double[2];
		location[0] = 30.2;location[1] = 63.2;
		application.setAttribute(runnerid.toString(), location);
		
		//��þ�γ��
		Double[] location2 = new Double[2];
		location2 = (Double[]) application.getAttribute(runnerid.toString());
		
		//����JSON������APP
		JSONObject json = new JSONObject();
		json.append("name", name).append("phone", phone).append("lat", location2[0]).append("lng", location2[1]);
		
		System.out.println(json.toString());
		response.getWriter().append(json.toString());
	}

}
