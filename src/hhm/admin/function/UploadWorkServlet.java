package hhm.admin.function;

import hhm.admin.function.impl.WorksImpl;
import hhm.admin.function.pojo.Works;
import hhm.ffmpeg.Ffmpeg;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartUpload;

public class UploadWorkServlet extends HttpServlet {
	SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat(
			"yyyyMMddHHmmss");
	WorksImpl worksImpl = new WorksImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String path = request.getRealPath("");

		long l_UserID = 0;
		try {
			l_UserID = (Long) session.getAttribute("UserID");
		} catch (Exception e) {
			request.setAttribute("TipsInfo", "用户登录失效,请重新登录");
			request.getRequestDispatcher("/tipsPage/TipsPage.jsp").forward(
					request, response);
			return;
		}
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

			// 1.得到板块ID
			String s_BoardID = su.getRequest().getParameter("BoardID");
			int i_BoardID = Integer.parseInt(s_BoardID);
			// 2.获取作品标题
			String s_WorkTitle = su.getRequest().getParameter("WorkTitle");
			// 4.获取作品文字内容
			String s_WorkContent = su.getRequest().getParameter("WorkContent");
			// 6.获取原著作者
			String s_AuthorID = su.getRequest().getParameter("AuthorID");
			int i_AuthorID = Integer.parseInt(s_AuthorID);
			// 获取章节
			String s_Chapter = su.getRequest().getParameter("Chapter");
			int i_Chapter = Integer.parseInt(s_Chapter);

			String s_IndexPicUrl = "";
			String s_VideoUrl = "";

			// 判断是否有附件上传,获取作品视频
			com.jspsmart.upload.File file_WorkVideo = su.getFiles().getFile(1);
			if (!file_WorkVideo.isMissing()) {

				String s_NowTime = dateFormatter.format(new java.util.Date());
				s_VideoUrl = s_NowTime + "_" + l_UserID + "."
						+ file_WorkVideo.getFileExt();
				file_WorkVideo.saveAs("/Upload/UserUploadWorkVideo/"
						+ s_VideoUrl);
			}
			// 判断是否有附件上传,获取作品索引图片
			com.jspsmart.upload.File file_WorkIndexPicture = su.getFiles()
					.getFile(0);
			if (!file_WorkIndexPicture.isMissing()) {
				// String s_IndexPic = file1.getFileName();
				// 将附件以 用户索引号+当前时间+附件扩展名 作为文件名保存

				String s_NowTime = dateFormatter.format(new java.util.Date());
				s_IndexPicUrl = s_NowTime + "_" + l_UserID + "."
						+ file_WorkIndexPicture.getFileExt();
				file_WorkIndexPicture
						.saveAs("/Upload/UserUploadWorkIndexPicture/"
								+ s_IndexPicUrl);
			} else {
				// 否则就启用ffmpeg工具截取视频缩略图
				Ffmpeg ffmpeg = new Ffmpeg();
				String s_NowTime = dateFormatter.format(new java.util.Date());

				s_IndexPicUrl = s_NowTime + "_" + l_UserID + ".jpg";
				String VideoPath = path + "\\Upload\\UserUploadWorkVideo\\"
						+ s_VideoUrl;
				String ImagePath = path
						+ "\\Upload\\UserUploadWorkIndexPicture\\"
						+ s_IndexPicUrl;
				ffmpeg.ProductVideoIndexPicture(VideoPath, ImagePath, path);
			}

			// 将作品信息保存到数据库中
			Works works = new Works();
			works.setWorkTitle(s_WorkTitle);
			works.setWorkContent(s_WorkContent);
			works.setBoardID(i_BoardID);
			works.setIndexPicUrl(s_IndexPicUrl);
			works.setAuthorID(i_AuthorID);
			works.setVideoUrl(s_VideoUrl);
			works.setChapter(i_Chapter);

			if (worksImpl.UploadWork(works)) {
				System.out.println("作品发表成功");
			} else {
				System.out.println("作品发表失败，请重试");
			}
		} catch (Exception e) {
			System.out.println("您上传的文件格式不对，或者上传文件太大！");

		}

		response.sendRedirect("../AdminFunction/UploadWork.html");

	}

}
