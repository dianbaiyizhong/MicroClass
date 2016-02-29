package hhm.user.function;

import hhm.foreground.pojo.Users;
import hhm.user.impl.NotesImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClickLikeForNoteServlet extends HttpServlet {
	NotesImpl notesImpl = new NotesImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String s_UserName = (String)session.getAttribute("UserName");

		int NoteID = Integer.parseInt(request.getParameter("NoteID"));
		try {
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			StringBuilder builder = new StringBuilder();
			builder.append("<message>");

			if (s_UserName == null || s_UserName == "") {
				builder.append("isNotLoginUser").append("</message>");
			} else {

				if (notesImpl.ModifyLikeCountForNote(NoteID)) {
					builder.append("ModifyLikeCountForNoteSuccess").append("</message>");
				}else{
					builder.append("ModifyLikeCountForNoteFailure").append("</message>");

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
