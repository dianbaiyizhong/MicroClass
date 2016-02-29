package hhm.user.function;

import hhm.user.impl.NotesImpl;
import hhm.user.pojo.Notes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubmitNotesServlet extends HttpServlet {
	NotesImpl notesImpl = new NotesImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();

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

		String s_UserID = Long.toString(l_UserID);
		int i_UserID = Integer.parseInt(s_UserID);
		
		String s_VideoTime = request.getParameter("VideoTime");
		String NotesContents = request.getParameter("NotesContents");
		String s_IsOpen = request.getParameter("IsOpen");

		String s_WorkID = request.getParameter("WorkID");
		int i_WorkID = Integer.parseInt(s_WorkID);
		Notes notes = new Notes();
		notes.setContents(NotesContents);
		if (s_IsOpen.equals("true")) {
			notes.setIsOpen(1);
		} else {
			notes.setIsOpen(0);

		}
		notes.setTime(s_VideoTime);
		notes.setUserID(i_UserID);
		notes.setWorkID(i_WorkID);

		StringBuilder builder = new StringBuilder();

		if (notesImpl.addNotes(notes)) {
			builder.append("SubmitNotesSuccess");
		} else {
			builder.append("SubmitNotesFail");
		}
		out.print(builder.toString());

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
