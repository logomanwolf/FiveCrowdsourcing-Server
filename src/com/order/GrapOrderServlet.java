package com.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.OrderDao;

/**
 * Servlet implementation class GrapOrderServlet
 */
@WebServlet(description = "����", urlPatterns = { "/GrapOrderServlet" })
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
		response.setContentType("text/html;charset=utf-8");
		Long delorderid = (long) 6;
		Long runnerid = (long) 1;
		OrderDao orderdao = new OrderDao();
		int ans = orderdao.updateOrderAfterOrderGrabbed(delorderid, runnerid);
		String result = "";
		if(ans==-1)
			result="�����Ѿ��������ˣ�";
		else if(ans!=0)
			result = "�����ɹ���";
		else
			result = "����ʧ��~";
		
		JSONObject json = new JSONObject();
		json.append("result", result);
		System.out.println(json.toString());
		response.getWriter().append(json.toString());
	}

}
