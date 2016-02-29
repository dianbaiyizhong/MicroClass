package hhm.user.function;

import hhm.user.impl.CommentsImpl;
import hhm.user.pojo.Comments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubmitFloorCommentServlet extends HttpServlet {
	CommentsImpl commentsImpl = new CommentsImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();

		long l_UserID = (Long) session.getAttribute("UserID");
		int i_UserID = (int) l_UserID;
		String s_CommentContent = request.getParameter("Content");
		String s_WorkID = request.getParameter("WorkID");
		int i_WorkID = Integer.parseInt(s_WorkID);
		StringBuilder builder = new StringBuilder();
		builder.append("<message>");

		Comments comments = new Comments();
		comments.setContents(s_CommentContent);
		comments.setUserID((i_UserID));
		comments.setWorkID(i_WorkID);
		if (commentsImpl.AddFloorComment(comments)) {
			builder.append("AddCommentSuccess").append("</message>");
		} else {
			builder.append("AddCommentFail").append("</message>");
		}

		out.println(builder.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
