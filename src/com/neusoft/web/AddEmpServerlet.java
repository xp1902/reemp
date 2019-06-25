package com.neusoft.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.services.impl.Ab01ServiceImpl;
import com.neusoft.system.tools.Tools;


@WebServlet("/addEmp.html")
public class AddEmpServerlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			Map<String, Object> dto = Tools.createDto(request);
			Ab01ServiceImpl services = new Ab01ServiceImpl(dto);
			String msg = services.addEmp()? "success": "wrong";
			request.setAttribute("msg", msg);
			
		}catch(Exception e){
			request.setAttribute("msg", "Õ¯¬Áπ ’œ");
			e.printStackTrace();
		}
		request.getRequestDispatcher("/addEmp.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);	
	}
}
