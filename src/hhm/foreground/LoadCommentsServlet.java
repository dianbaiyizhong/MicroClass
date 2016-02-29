package hhm.foreground;

import hhm.foreground.impl.CommentsImpl;
import hhm.foreground.impl.UsersImpl;
import hhm.foreground.pojo.Comments;
import hhm.foreground.pojo.Users;
import hhm.splitPage.SplitPage;
import hhm.user.pojo.Notes;
import hhm.user.pojo.Pages;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadCommentsServlet extends HttpServlet {

	CommentsImpl commentsImpl = new CommentsImpl();
	UsersImpl usersImpl = new UsersImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		String s_workID = request.getParameter("WorkID");
		int i_workID = Integer.parseInt(s_workID);
		String ShowType = request.getParameter("ShowType");

		ResultSet rs = null;
		if (ShowType.equals("All")) {

			rs = commentsImpl.GetWorkAllComments(i_workID);

		}
		SplitPage splitPage = new SplitPage();
		splitPage.initialize(rs, 10);

		int showPage = 1;

		Pages pagesPojo = new Pages();

		String str_page = request.getParameter("page");
		if (str_page == null) {
			showPage = 1;
			pagesPojo.setShowPage(showPage);
		} else {
			try {
				showPage = Integer.parseInt(str_page);
				pagesPojo.setShowPage(showPage);

			} catch (NumberFormatException e) {
				showPage = 1;
				pagesPojo.setShowPage(showPage);

			}
			if (showPage < 1) {
				showPage = 1;
				pagesPojo.setShowPage(showPage);

			}
			if (showPage > splitPage.getPageCount()) {
				showPage = splitPage.getPageCount();
				pagesPojo.setShowPage(showPage);

			}

		}

		// 获得作品中回复的个数
		pagesPojo.setRowCount(splitPage.getRowCount());
		// 获得回复的总页数

		pagesPojo.setPageCount(splitPage.getPageCount());
		// 获取要显示的数据集合

		Vector vData = splitPage.getPage(showPage);

		List<Comments> commentsList = new ArrayList<Comments>();
		for (int j = 0; j < vData.size(); j++) {
			String[] sData = (String[]) vData.get(j);
			Comments comments = new Comments();
			Users users = new Users();
			
			users.setUserID(Integer.parseInt(sData[0]));
			if (usersImpl.UserIDToInit(users)) {
				comments.setUserName(users.getUserName());
				comments.setUserHeadPicture(users.getUserHeadPicture());
			}
			comments.setCreateTime(sData[2]);
			comments.setContents(sData[1]);
			comments.setCommentID(Integer.parseInt(sData[3]));
			// notes.setIsOpen(Integer.parseInt(sData[5]));
			commentsList.add(comments);

		}

		request.setAttribute("commentsList", commentsList);
		request.setAttribute("pages", pagesPojo);
		request.getRequestDispatcher("/xml/WorkDetail/LoadCommentsXml.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
