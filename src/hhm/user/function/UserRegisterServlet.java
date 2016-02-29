package hhm.user.function;

import hhm.user.impl.UsersImpl;
import hhm.user.pojo.Users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserRegisterServlet extends HttpServlet {

	UsersImpl usersImpl = new UsersImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			String UserName = request.getParameter("userName");
			StringBuilder builder = new StringBuilder();
			builder.append("<message>");

			Users users = new Users();
			users.setUserName(UserName);
			if (usersImpl.isExist(users)) {

				builder.append("用户名[" + UserName + "]已经存在，请使用其他用户名").append(
						"</message>");
			} else {
				builder.append("用户名[" + UserName + "]尚未存在，可以使用该用户名注册").append(
						"</message>");
			}
			out.println(builder.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String status = request.getParameter("status") + "";
		if (status.equals("register")) {
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
			String s_Rand_1 = (String) session.getAttribute("Rand");
			String s_Rand_2 = request.getParameter("Rand");

			if (s_Rand_1.compareTo(s_Rand_2) == 0) {
				String s_UserName = request.getParameter("UserName");
				Users users = new Users();
				users.setUserName(s_UserName);
				if (usersImpl.isExist(users)) {
					System.out.println("该用户名已经存在");
				} else {
					String s_UserPassword = request
							.getParameter("UserPassword");
//					String s_NickName = request.getParameter("NickName");
//					String s_Sex = request.getParameter("Sex");
//					String s_Sign = request.getParameter("Sign");

					String s_Email = request.getParameter("Email");
					users.setUserPassword(s_UserPassword);
//					users.setNickName(s_NickName);
//					users.setSex(s_Sex);
//					users.setSign(s_Sign);

					users.setEmail(s_Email);
					// 无论是失败还是成功，这里先跳转再说
					if (usersImpl.addUser(users)) {

						System.out.println("用户注册成功");
//						RequestDispatcher dispatcher = request
//								.getRequestDispatcher("/foreground/index.jsp");
//						dispatcher.forward(request, response);
						response.sendRedirect("/MicroClass/foreground/index.jsp");
					} else {
						System.out.println("用户注册失败");

					}
				}
			}
			session.removeAttribute("Rand");

		} else {
			doGet(request, response);

		}

	}

}
