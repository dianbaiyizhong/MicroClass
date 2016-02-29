package hhm.foreground;

import hhm.foreground.impl.WorksImpl;
import hhm.foreground.pojo.Works;
import hhm.general.TimeConvertTool;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadChapterServlet extends HttpServlet {

	WorksImpl worksImpl = new WorksImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/xml;charset=utf-8");
		
		String Chapter = request.getParameter("Chapter");
		String s_BoardID = request.getParameter("BoardID");
		int i_Chapter = Integer.parseInt(Chapter);
		int i_BoardID = Integer.parseInt(s_BoardID);
		ResultSet rs = null;
		rs = worksImpl.GetWorksByChapterAndBoardID(i_Chapter, i_BoardID);
		List<Works> worksList = new ArrayList<Works>();

		try {
			while (rs.next()) {
				Works works = new Works();
				works.setIndexPicUrl(rs.getString("IndexPicUrl"));
				works.setWorkTitle(rs.getString("WorkTitle"));
				works.setWorkID(rs.getInt("WorkID"));
				worksList.add(works);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		request.setAttribute("worksList", worksList);
		request.getRequestDispatcher(
				"/xml/WorkDetail/LoadChapterXml.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
