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

@WebServlet ("deleteEmp.html")
public class DeleteEmpServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			Map<String, Object> dto = Tools.createDto(request);
			Ab01ServiceImpl services = new Ab01ServiceImpl(dto);
			String msg = services.deleteEmp()? "É¾³ý³É¹¦": "É¾³ýÊ§°Ü";
			request.setAttribute("msg", msg);
			
		}catch(Exception e){
			request.setAttribute("msg", "ÍøÂç¹ÊÕÏ");
			e.printStackTrace();
		}
		request.getRequestDispatcher("/addEmp.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		this.doGet(request, response);	
	}
}
