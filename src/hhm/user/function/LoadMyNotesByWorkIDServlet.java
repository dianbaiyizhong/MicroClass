package hhm.user.function;

import hhm.general.TimeConvertTool;
import hhm.user.impl.NotesImpl;
import hhm.user.pojo.Notes;

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
import javax.servlet.http.HttpSession;

public class LoadMyNotesByWorkIDServlet extends HttpServlet {
	TimeConvertTool timeConvertTool = new TimeConvertTool();
	NotesImpl notesImpl = new NotesImpl();
	DecimalFormat df = new DecimalFormat("#");

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		HttpSession userSession = request.getSession();
		long l_UserID = 0;
		try {
			l_UserID = (Long) userSession.getAttribute("UserID");
		} catch (Exception e) {

			request.setAttribute("TipsInfo", "用户登录失效");
			request.getRequestDispatcher("/tipsPage/TipsPage.jsp").forward(
					request, response);

			return;

		}
		String s_WorkID = request.getParameter("WorkID");
		int i_WorkID = Integer.parseInt(s_WorkID);
		ResultSet rs = null;
		rs = notesImpl.GetMyNotesByWorkID(Integer.parseInt(s_WorkID), l_UserID);
		List<Notes> notesList = new ArrayList<Notes>();

		try {
			while (rs.next()) {
				Notes notes = new Notes();

				notes.setContents(rs.getString("Contents"));
				float f_Time = Float.parseFloat(rs.getString("Time"));
				notes.setTime(timeConvertTool.secToTime(Integer.parseInt(df
						.format(f_Time))));
				notes.setLikeCount(rs.getInt("LikeCount"));
				notes.setCreateTime(rs.getString("CreateTime"));
				notes.setNoteID(rs.getInt("NoteID"));
				notesList.add(notes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String IndexPicUrl = notesImpl.GetIndexPicUrlByWorkID(i_WorkID);

		request.setAttribute("indexPicUrl", IndexPicUrl);
		request.setAttribute("notesList", notesList);
		request.getRequestDispatcher(
				"/xml/UserFunction/LoadMyNotesByWorkIDXml.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
