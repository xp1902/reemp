package com.neusoft.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.services.impl.Ab01ServiceImpl;
import com.neusoft.system.tools.Tools;


@WebServlet("/queryEmp.html")
public class QueryEmpServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			//1.获取DTO
			Map<String,Object> dto=Tools.createDto(request);
			//2.实例化Services
			Ab01ServiceImpl services=new Ab01ServiceImpl(dto);
			//3.调用方法
			List<Map<String,String>> rows=services.query();
			//4.判断是否存在查询结果
			if(rows.size()>0)
			{
				//将查询到的数据,反馈给页面
				request.setAttribute("rows",rows);
			}
			else
			{
				request.setAttribute("msg","没有符合条件的数据!");
			}	
		}
		catch(Exception ex)
		{
			request.setAttribute("msg", "网络故障!");
			ex.printStackTrace();
		}
		request.getRequestDispatcher("/queryEmp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doGet(request, response);
	}

}
