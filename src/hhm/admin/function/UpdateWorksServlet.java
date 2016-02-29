package hhm.admin.function;

import hhm.admin.function.impl.WorksImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateWorksServlet extends HttpServlet {

	WorksImpl worksImpl = new WorksImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		StringBuilder builder = new StringBuilder();
		String AttrName = request.getParameter("AttrName");
		String NowText = request.getParameter("NowText");
		String WorkID = request.getParameter("ID");

		if(worksImpl.UpdateWorks(Integer.parseInt(WorkID), NowText, AttrName)){
			builder.append("UpdateSucess");
	
		}else{
			builder.append("UpdateFailure");

		}

		out.print(builder.toString());
	}

}
