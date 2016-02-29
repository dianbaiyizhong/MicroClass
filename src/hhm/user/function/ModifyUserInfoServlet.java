package hhm.user.function;

import hhm.user.impl.UsersImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModifyUserInfoServlet extends HttpServlet {

	UsersImpl usersImpl = new UsersImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		long l_UserID = 0;
		try {
			l_UserID = (Long) session.getAttribute("UserID");
		} catch (Exception e) {

			request.setAttribute("TipsInfo", "用户登录失效");
			request.getRequestDispatcher("/tipsPage/TipsPage.jsp").forward(request, response); 
			return;

		}
		String OriginalOldPassword = request.getParameter("OriginalOldPassword");
		String NewPassword = request.getParameter("NewPassword");
		String ConfirmNewPassword = request.getParameter("ConfirmNewPassword");

		if (NewPassword.equals(ConfirmNewPassword)) {
			if (usersImpl.userValid(l_UserID, OriginalOldPassword)) {
				
				if(usersImpl.modifyUserPassword(ConfirmNewPassword, l_UserID)){
					request.setAttribute("TipsInfo", "修改密码成功");
					request.getRequestDispatcher("/tipsPage/TipsPage.jsp").forward(request, response); 
				}else{
					request.setAttribute("TipsInfo", "修改密码失败");
					request.getRequestDispatcher("/tipsPage/TipsPage.jsp").forward(request, response); 
				}
			} else {
				request.setAttribute("TipsInfo", "用户密码错误");
				request.getRequestDispatcher("/tipsPage/TipsPage.jsp").forward(request, response); 

			}
		} else {
			request.setAttribute("TipsInfo", "密码不一致");
			request.getRequestDispatcher("/tipsPage/TipsPage.jsp").forward(request, response); 

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}
