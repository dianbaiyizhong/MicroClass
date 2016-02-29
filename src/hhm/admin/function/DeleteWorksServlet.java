package hhm.admin.function;

import hhm.admin.function.impl.WorksImpl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteWorksServlet extends HttpServlet {

	WorksImpl worksImpl = new WorksImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s_WorkID = request.getParameter("WorkID");
		String WorkPic = "";
		// 在删除之前,先获取图片
		try {
			WorkPic = worksImpl.GetWorkPic(s_WorkID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (worksImpl.DeleteWork(Integer.parseInt(s_WorkID))) {
			String path = request.getRealPath("")
			+ "\\Upload\\UserUploadWorkIndexPicture\\";
			File f = new File(path, WorkPic);
			if (f.exists()) {
				f.delete();
			}
		}
		
		response.sendRedirect("../AdminFunction/UpdateWorks.jsp");

	}

}
