package hhm.general;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IsUserLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		String s_UserName = (String) session.getAttribute("UserName");
		StringBuilder builder = new StringBuilder();

		if (s_UserName == null || s_UserName == "") {

			builder.append("isNotLoginUser");
		} else {

			builder.append("isLoginUser");

		}
		out.print(builder.toString());

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
