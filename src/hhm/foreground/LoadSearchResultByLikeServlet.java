package hhm.foreground;

import hhm.Lucene.pojo.SearchResult;
import hhm.foreground.impl.WorksImpl;
import hhm.foreground.pojo.Pages;
import hhm.splitPage.SplitPage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadSearchResultByLikeServlet extends HttpServlet {

	WorksImpl worksImpl = new WorksImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/xml;charset=utf-8");
		String KeyWord = request.getParameter("KeyWord");

		ResultSet rs = null;
		rs = worksImpl.GetSearchResultByLike(KeyWord);

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

		List<SearchResult> SearchResultList = new ArrayList<SearchResult>();

		for (int j = 0; j < vData.size(); j++) {
			String[] sData = (String[]) vData.get(j);
			SearchResult searchResult = new SearchResult();
			searchResult.setWorkTitle(sData[2]);
			searchResult.setBoardID(Long.parseLong(sData[1]));
			searchResult.setWorkID(Long.parseLong(sData[0]));
			searchResult.setIndexPic(sData[6]);
			searchResult.setReCount(Integer.parseInt(sData[4]));
			searchResult.setReadCount(Integer.parseInt(sData[5]));

			if (sData[3].length() > 50) {
				searchResult.setWorkContent(sData[3].substring(0, 50));
			} else {
				searchResult.setWorkContent(sData[3]);

			}
			SearchResultList.add(searchResult);

		}

		request.setAttribute("SearchResultList", SearchResultList);
		request.setAttribute("pages", pagesPojo);

		request.getRequestDispatcher("/xml/Index/SearchResultXml.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
