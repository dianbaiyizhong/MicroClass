package hhm.foreground;

import hhm.foreground.impl.BoardsImpl;
import hhm.foreground.impl.UsersImpl;
import hhm.foreground.impl.WorksImpl;
import hhm.foreground.pojo.Boards;
import hhm.foreground.pojo.Users;
import hhm.foreground.pojo.Works;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoadWorkDetailServlet extends HttpServlet {
	WorksImpl worksImpl = new WorksImpl();
	UsersImpl usersImpl = new UsersImpl();
	BoardsImpl boardsImpl = new BoardsImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sessionn = request.getSession();
		String s_workID = request.getParameter("WorkID");
		int i_workID = 0;
		try {
			i_workID = Integer.parseInt(s_workID);
		} catch (NumberFormatException ex) {
			System.out.println("非法操作");
			return;
		}
		// 获得作品信息
		Works works = new Works();
		works.setWorkID(i_workID);
		if (!worksImpl.WorkIDTOinit(works)) {
			System.out.println("该作品不存在");
			return;
		}

		// 获得当前板块信息(目前来说，UserIDToInit用户初始化可以省略)
		Users users = new Users();
		users.setUserID(works.getUserID());
		Boards boards = new Boards();
		boards.setBoardID(works.getBoardID());
		if (!usersImpl.UserIDToInit(users) || !boardsImpl.BoardIDToInit(boards)) {
			System.out.println("获取作品信息失败");
			return;
		}

		int count = 1;
		// 将阅读数加1
		int i_ReadCount = works.getReadCount() + 1;
		works.setReadCount(i_ReadCount);
		worksImpl.modifyReadCount(works);

		request.setAttribute("workID", String.valueOf(works.getWorkID()));
		request.setAttribute("boardID", boards.getBoardID());
		request.setAttribute("boards", boards);
		request.setAttribute("works", works);
		request.setAttribute("users", users);

		String status = request.getParameter("status");
		if (status.equals("WorkDetail")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/foreground/WorkDetail.jsp");

			dispatcher.forward(request, response);
		} else if (status.equals("GoodWorkDetail")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/foreground/goodWorkDetail.jsp");
			dispatcher.forward(request, response);
		}

	}

}
