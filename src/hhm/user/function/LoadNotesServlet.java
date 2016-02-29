package hhm.user.function;

import hhm.general.TimeConvertTool;
import hhm.splitPage.SplitPage;
import hhm.user.impl.NotesImpl;
import hhm.user.impl.WorksImpl;
import hhm.user.pojo.Notes;
import hhm.user.pojo.Pages;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoadNotesServlet extends HttpServlet {

	WorksImpl worksImpl = new WorksImpl();
	NotesImpl notesImpl = new NotesImpl();
	DecimalFormat df = new DecimalFormat("#");
	TimeConvertTool timeConvertTool = new TimeConvertTool();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/xml;charset=utf-8");
		String s_workID = request.getParameter("WorkID");
		int i_workID = Integer.parseInt(s_workID);
		String ShowType = request.getParameter("ShowType");

		ResultSet rs = null;
		if (ShowType.equals("GoodNotes")) {

			rs = notesImpl.GetWorkGoodNotes(i_workID);

		} else if (ShowType.equals("NewlyNotes")) {
			rs = notesImpl.GetWorkNewlyeNotes(i_workID);

		} else if (ShowType.equals("MyNotes")) {
			HttpSession session = request.getSession();

			long l_UserID = (Long) session.getAttribute("UserID");
			if (String.valueOf(l_UserID) == "") {
				System.out.println("你还没登陆");
				return;
			}

			rs = notesImpl.GetWorkMyNotes(i_workID, l_UserID);

		}
		SplitPage splitPage = new SplitPage();
		splitPage.initialize(rs, 5);

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

		List<Notes> notesList = new ArrayList<Notes>();
		for (int j = 0; j < vData.size(); j++) {
			String[] sData = (String[]) vData.get(j);
			Notes notes = new Notes();

			float f_Time = Float.parseFloat(sData[2]);
			notes.setTime(timeConvertTool.secToTime(Integer.parseInt(df
					.format(f_Time))));
			notes.setContents(sData[1]);
			notes.setUserName(sData[3]);
			notes.setUserHeadPicture(sData[4]);
			notes.setLikeCount(Integer.parseInt(sData[5]));
			notes.setNoteID(Integer.parseInt(sData[6]));
			notesList.add(notes);

		}

		request.setAttribute("notesList", notesList);
		request.setAttribute("pages", pagesPojo);
		request.getRequestDispatcher("/xml/WorkDetail/LoadNotesXml.jsp")
				.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
