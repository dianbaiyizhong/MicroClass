package hhm.admin.function;

import hhm.admin.function.impl.BoardsImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteBoardServlet extends HttpServlet {
	BoardsImpl boardsImpl = new BoardsImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		long l_UserID = 0;
		try {
			l_UserID = (Long) session.getAttribute("UserID");
		} catch (Exception e) {
			request.setAttribute("TipsInfo", "用户登录失效,请重新登录");
			request.getRequestDispatcher("/tipsPage/TipsPage.jsp").forward(
					request, response);
			return;
		}

		String s_BoardID = request.getParameter("BoardID");
		if (boardsImpl.DeleteBoard(Integer.parseInt(s_BoardID))) {

		} else {

		}

		response.sendRedirect("../AdminFunction/AddBoard.jsp");

	}

}
