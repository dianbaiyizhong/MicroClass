package hhm.admin.function;

import hhm.admin.function.impl.BoardsImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateBoardServlet extends HttpServlet {
	BoardsImpl boardsImpl = new BoardsImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();

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

		StringBuilder builder = new StringBuilder();
		String AttrName = request.getParameter("AttrName");
		String NowText = request.getParameter("NowText");
		String WorkID = request.getParameter("ID");
		if (boardsImpl.UpdateBoard(Integer.parseInt(WorkID), NowText, AttrName)) {
			builder.append("UpdateSucess");
		} else {
			builder.append("UpdateFailure");

		}

		out.print(builder.toString());

	}

}
