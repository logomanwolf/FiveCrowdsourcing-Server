//package com.merchant;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.utils.DatabaseUtil;
//import com.utils.LogUtil;
//
///**
// * Servlet implementation class RegisterServlet
// */
//@WebServlet("/RegisterServlet")
//public class RegisterServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private String message;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public RegisterServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		//Test
//		String phone = request.getParameter("phone");
//        String password = request.getParameter("password");
//        System.out.println("phone" + "," + phone);
//        System.out.println("password" + "," + password);
//        
//        Connection connect = DatabaseUtil.getConnection();  
//        try {  
//            Statement statement = connect.createStatement();  
//            String sql = "select phone from merchant where phone='" + phone + "'";  
//            LogUtil.log(sql);  
//            ResultSet result = statement.executeQuery(sql);  
//            if (result.next()) { // �ܲ鵽���˺ţ�˵���Ѿ�ע�����  
//                message = "���˺��Ѵ���";  
//            } else {  
//                String sqlInsert = "insert into merchant(phone, password) values('"  
//                        + phone + "', '" + password + "')";  
//                LogUtil.log(sqlInsert);  
//                if (statement.executeUpdate(sqlInsert) > 0) { // �������ע���߼����������˺����뵽���ݿ�  
//                     
//                    message = "ע��ɹ�";  
//                } else {  
//                     
//                    message = "ע��ʧ��";  
//                }  
//            }  
//        } catch (SQLException e) {  
//            e.printStackTrace();  
//        }  
//  
//        response.getWriter().append("code:").append(code).append(";message:").append(message);  
//    }
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
