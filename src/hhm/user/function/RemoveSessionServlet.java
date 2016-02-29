package hhm.user.function;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveSessionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		StringBuilder builder = new StringBuilder();
		HttpSession session = request.getSession();
		
		session.removeAttribute("UserName");
		session.removeAttribute("UserID");
		session.invalidate();
		builder.append("LogoutSucess");
		out.print(builder.toString());

	}

}
