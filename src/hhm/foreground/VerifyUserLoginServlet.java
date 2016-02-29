package hhm.foreground;

import hhm.foreground.impl.UsersImpl;
import hhm.foreground.pojo.Users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerifyUserLoginServlet extends HttpServlet {
	UsersImpl usersImpl = new UsersImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();

			String s_UserName = request.getParameter("UserName");
			String s_UserPassword = request.getParameter("Password");
			Users users = new Users();
			users.setUserName(s_UserName);
			users.setUserPassword(s_UserPassword);

			StringBuilder builder = new StringBuilder();
			StringBuilder builder1 = new StringBuilder();

			builder.append("<message>");

			if (usersImpl.userValid(users)) {
				session.setAttribute("UserID", users.getUserID());

				session.setAttribute("UserName", users.getUserName());

				if (s_UserName.compareTo("admin") == 0) {

					System.out.println("管理员");
					builder.append("IsMaster").append("</message>");

				} else {

					// 非管理员用户
					long l_UserID = usersImpl.GetUserID(s_UserName);
					String UserHeadPic = usersImpl.GetUserHeadPicByID(l_UserID);
					builder.append("LoginSucess").append("<message>").append(
							UserHeadPic).append("</message>").append(
							"</message>");

				}
			} else {

				// 用户名不正确

				builder.append("LoginFail").append("</message>");

			}

			out.println(builder.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
