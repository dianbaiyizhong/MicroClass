package hhm.user.function;

import hhm.user.impl.CollectionsImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteUserCollectionServlet extends HttpServlet {

	CollectionsImpl collectionsImpl = new CollectionsImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long l_UserID = 0;
		HttpSession session = request.getSession();
		try{
		 l_UserID = (Long) session.getAttribute("UserID");
		}catch (Exception e) {
			request.setAttribute("TipsInfo", "请登录");
			request.getRequestDispatcher("/tipsPage/TipsPage.jsp").forward(
					request, response);
			return;
			
		}
		String s_CollectionID = request.getParameter("CollectionID");
		if (collectionsImpl.DeleteCollection(Integer.parseInt(s_CollectionID),
				l_UserID)) {

		} else {
			return;
		}

		response.sendRedirect("/MicroClass/UserFunction/UserCollection.jsp?UserID="+l_UserID);

	}

}
