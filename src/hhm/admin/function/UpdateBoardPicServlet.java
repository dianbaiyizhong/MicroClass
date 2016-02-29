package hhm.admin.function;

import hhm.admin.function.impl.BoardsImpl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartUpload;

public class UpdateBoardPicServlet extends HttpServlet {
	SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat(
			"yyyyMMddHHmmss");
	BoardsImpl boardsImpl = new BoardsImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		long l_UserID = 0;
		try {
			l_UserID = (Long) session.getAttribute("UserID");
		} catch (Exception e) {
			request.setAttribute("TipsInfo", "用户登录失效,请重新登录");
			request.getRequestDispatcher("/tipsPage/TipsPage.jsp").forward(
					request, response);
			return;
		}
		String path = request.getRealPath("");

		try {
			// 新建一个SmartUpload对象
			SmartUpload su = new SmartUpload();
			// 上传初始化
			su.initialize(this.getServletConfig(), request, response);// 初始化
			// 设定上传限制
			// 限制每个上传文件的最大长度。
			su.setMaxFileSize(1024 * 1024 * 1024 * 300);
			// 设定允许上传的文件（通过扩展名限制）
			su
					.setAllowedFilesList("jpg,gif,bmp,JPG,GIF,BMP,PNG,mp3,avi,rmvb,mov,wmv,mp4,flv,png");
			// 上传文件
			su.upload();

			String s_BoardID = su.getRequest().getParameter("BoardID");
			int i_BoardID = Integer.parseInt(s_BoardID);

			String OriginalIndexPicUrl = su.getRequest().getParameter(
					"OriginalIndexPicUrl");

			String s_WorkPic = "";
			String s_WorkPicThumb = "";

			com.jspsmart.upload.File file_WorkPic = su.getFiles().getFile(0);
			com.jspsmart.upload.File file_WorkPicThumb = su.getFiles().getFile(
					1);

			if (!(!file_WorkPic.isMissing() && !file_WorkPicThumb.isMissing())) {
				request.setAttribute("TipsInfo", "不能只上传一个或者不上传");
				request.getRequestDispatcher("/tipsPage/TipsPage.jsp").forward(
						request, response);
				return;

			}

			if (!file_WorkPic.isMissing()) {

				String s_NowTime = dateFormatter.format(new java.util.Date());
				s_WorkPic = s_NowTime + "_" + l_UserID + "."
						+ file_WorkPic.getFileExt();
				file_WorkPic.saveAs("/Upload/UserUploadWorkIndexPicture/"
						+ s_WorkPic);

			}

			if (!file_WorkPicThumb.isMissing()) {
				s_WorkPicThumb = s_WorkPic;

				file_WorkPicThumb
						.saveAs("/Upload/UserUploadWorkIndexPictureThumb/"
								+ s_WorkPicThumb);
			}

			if (boardsImpl.UpdateBoardPic(i_BoardID, s_WorkPic)) {
				System.out.println("作品修改成功");

				// 先判断是否已经之前有了什么图片了
				File f = new File(path
						+ "\\Upload\\UserUploadWorkIndexPicture\\",
						OriginalIndexPicUrl);
				File f1 = new File(path
						+ "\\Upload\\UserUploadWorkIndexPictureThumb\\",
						OriginalIndexPicUrl);

				if (f.exists()) {
					f.delete();
					System.out.println("原图片删除成功");

				}
				if (f1.exists()) {
					f1.delete();
					System.out.println("原图片删除成功");

				}
			} else {
				System.out.println("作品修改失败，请重试");
			}

		} catch (Exception e) {
			System.out.println("您上传的文件格式不对，或者上传文件太大！");

		}

		response.sendRedirect("../AdminFunction/AddBoard.jsp");

	}

}
