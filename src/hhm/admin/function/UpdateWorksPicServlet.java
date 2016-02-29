package hhm.admin.function;

import hhm.admin.function.impl.WorksImpl;
import hhm.admin.function.pojo.Works;
import hhm.ffmpeg.Ffmpeg;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartUpload;

public class UpdateWorksPicServlet extends HttpServlet {
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

		// 获取当前用户的索引号
		long l_UserID = 0;
		try {
			l_UserID = (Long) session.getAttribute("UserID");
		} catch (Exception e) {
			System.out.println("您的登录已经失效，请重新登录");
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

			String s_WorkID = su.getRequest().getParameter("WorkID");
			int i_WorkID = Integer.parseInt(s_WorkID);

			String OriginalIndexPicUrl = su.getRequest().getParameter(
					"OriginalIndexPicUrl");
			String OriginalVideoUrl = su.getRequest().getParameter(
					"OriginalVideoUrl");

			String s_IndexPicUrl = "";
			String s_VideoUrl = "";

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
						+ OriginalVideoUrl;
				String ImagePath = path
						+ "\\Upload\\UserUploadWorkIndexPicture\\"
						+ s_IndexPicUrl;
				ffmpeg.ProductVideoIndexPicture(VideoPath, ImagePath, path);
			}

			// 将作品信息保存到数据库中
			Works works = new Works();

			works.setIndexPicUrl(s_IndexPicUrl);
			works.setWorkID(i_WorkID);

			if (worksImpl.UpdateWorksPic(i_WorkID, s_IndexPicUrl)) {
				System.out.println("作品修改成功");
				// 在这里，记得要把原来的图片删除掉咯
				File f = new File(
						path + "\\Upload\\UserUploadWorkIndexPicture\\"
								+ s_VideoUrl, OriginalIndexPicUrl);

				if (f.exists()) {
					f.delete();
					System.out.println("原图片删除成功");

				}
			} else {
				System.out.println("作品修改失败，请重试");
			}
		} catch (Exception e) {
			System.out.println("您上传的文件格式不对，或者上传文件太大！");

		}

		response.sendRedirect("../AdminFunction/UpdateWorks.jsp");

	}

}
