package com.validate;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.RunnerDao;
import com.entity.Runner;

public class insertValidatedRunner extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session=request.getSession();
        if(session.getAttribute("validatedRunners")==null){
      	  response.sendRedirect("/FiveCrowdsourcing-Server/runner/validaterunner.do");
        }
        else{
      	  RunnerDao runnerDao=new RunnerDao();
      	  ArrayList<Runner> validatedRunners=(ArrayList<Runner>)session.getAttribute("validatedRunners");
      	  try {
			runnerDao.insertValidatedRunners(validatedRunners);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	  session.removeAttribute("validatedRunners");
      	  session.removeAttribute("runners");
      	  session.removeAttribute("runner");
      	  response.sendRedirect("/FiveCrowdsourcing-Server/runner/validaterunner.do");
        }
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doGet(request, response);
	}

}
