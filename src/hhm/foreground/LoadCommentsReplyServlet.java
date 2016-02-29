package hhm.foreground;

import hhm.foreground.impl.CommentsImpl;
import hhm.foreground.impl.UsersImpl;
import hhm.foreground.pojo.Comments;
import hhm.foreground.pojo.Users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadCommentsReplyServlet extends HttpServlet {

	CommentsImpl commentsImpl = new CommentsImpl();
	UsersImpl usersImpl = new UsersImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String s_CommentID = request.getParameter("CommentID");

		ResultSet rs = commentsImpl.GetCommentsReply(Integer.parseInt(s_CommentID));
		List<Comments> commentsList = new ArrayList<Comments>();
		try {
			while (rs.next()) {
				Comments comments = new Comments();
				comments.setContents(rs.getString("Contents"));
				comments.setCreateTime(rs.getString("CreateTime"));
				comments.setCommentID(rs.getInt("CommentID"));
				int UserID = rs.getInt("UserID");
				comments.setUserID(UserID);
				Users users = new Users();
				users.setUserID(UserID);
				usersImpl.UserIDToInit(users);
				//获取回复者的用户名
				comments.setUserName(users.getUserName());
				//获取回复者的用户头像
				comments.setUserHeadPicture(users.getUserHeadPicture());

				comments.setCommentRepliedID(rs.getInt("RepliedCommentID"));
				//获取被回复者的用户名
				int RepliedCommentID = rs.getInt("RepliedCommentID");
				int CommentsRepliedUserID = commentsImpl.GetUserIDByCommentID(RepliedCommentID);
				users.setUserID(CommentsRepliedUserID);
				usersImpl.UserIDToInit(users);
				comments.setCommentRepliedUserName(users.getUserName());
				commentsList.add(comments);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("commentsList", commentsList);
		request.getRequestDispatcher("/xml/WorkDetail/LoadCommentsReplyXml.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
