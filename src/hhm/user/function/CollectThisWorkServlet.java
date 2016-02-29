package hhm.user.function;

import hhm.user.impl.CollectionsImpl;
import hhm.user.impl.WorksImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CollectThisWorkServlet extends HttpServlet {

	WorksImpl worksImpl = new WorksImpl();
	CollectionsImpl collectionsImpl = new CollectionsImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String s_UserName = (String) session.getAttribute("UserName");

		int WorkID = Integer.parseInt(request.getParameter("WorkID"));

		try {
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			StringBuilder builder = new StringBuilder();
			builder.append("<message>");

			if (s_UserName == null || s_UserName == "") {
				builder.append("isNotLoginUser").append("</message>");
			} else {
				long l_UserID = (Long) session.getAttribute("UserID");

				if (!collectionsImpl
						.isUserHaveCollectThisWork(WorkID, l_UserID)) {

					if (collectionsImpl.addCollection(WorkID, l_UserID)) {
						builder.append("CollectThisWorkSuccess").append(
								"</message>");
					} else {
						builder.append("CollectThisWorkFailure").append(
								"</message>");

					}
				} else {
					builder.append("UserHaveCollectThisWork").append(
							"</message>");

				}

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
