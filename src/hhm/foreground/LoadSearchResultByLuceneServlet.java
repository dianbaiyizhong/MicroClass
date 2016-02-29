package hhm.foreground;

import hhm.Lucene.IndexDao;
import hhm.Lucene.QueryResult;
import hhm.Lucene.pojo.SearchResult;
import hhm.foreground.impl.WorksImpl;
import hhm.foreground.pojo.Pages;
import hhm.foreground.pojo.Works;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.document.Document;

public class LoadSearchResultByLuceneServlet extends HttpServlet {
	IndexDao indexDao = new IndexDao();

	WorksImpl worksImpl = new WorksImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/xml;charset=utf-8");

		String KeyWord = request.getParameter("KeyWord");
		String str_page = request.getParameter("page");
		System.out.println(KeyWord + "   " + str_page);

		int showPage = 1;
		Pages pagesPojo = new Pages();

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

		}

		int firstResult = 0;
		int maxResult = 5;
		if (showPage == 1) {
			firstResult = showPage - 1;
			maxResult = showPage * 5;
		} else {
			firstResult = (showPage - 1) * 5;
			maxResult = showPage * 5 + 1;

		}

		// 开始搜索
		String queryString = KeyWord;
		System.out.println(firstResult + "   " + maxResult);
		
		HttpSession session = request.getSession();
		String path = request.getRealPath("");
		QueryResult qr = indexDao.search(queryString, firstResult, maxResult,path+"\\luceneIndex");
		int RecordCount = qr.getRecordCount();
		System.out.println("总共有【" + qr.getRecordCount() + "】条匹配结果");
		// 总页数
		List<SearchResult> SearchResultList = new ArrayList<SearchResult>();
		for (Document doc : qr.getRecordList()) {

			SearchResult searchResult = new SearchResult();
			Works works = new Works();
			works.setWorkID(Integer.parseInt(doc.get("WorkID")));
			if(worksImpl.WorkIDTOinit(works)){
			searchResult.setWorkTitle(works.getWorkTitle());
			searchResult.setBoardID(works.getBoardID());
			searchResult.setWorkID(works.getWorkID());
			if (works.getIntroduce().length() > 50) {
				searchResult.setWorkContent(works.getIntroduce().substring(0, 50));
			} else {
				searchResult.setWorkContent(works.getIntroduce());

			}
			searchResult.setReadCount(works.getReadCount());
			searchResult.setReCount(works.getReCount());
			searchResult.setIndexPic(works.getIndexPicUrl());
			}else{
				System.out.println("数据加载失败");
			}
			SearchResultList.add(searchResult);
		}

		Pages pages = new Pages();
		// 设置总的记录数
		pages.setRowCount(RecordCount);
		if (RecordCount % 5 == 0) {
			pages.setPageCount(RecordCount / 5);
		} else if (RecordCount % 5 != 0) {
			pages.setPageCount(RecordCount / 5 + 1);
		}
		pages.setShowPage(showPage);

		request.setAttribute("SearchResultList", SearchResultList);
		request.setAttribute("pages", pages);

		request.getRequestDispatcher("/xml/Index/SearchResultXml.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
