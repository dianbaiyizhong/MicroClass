package hhm.ffmpeg;

import java.io.InputStream;
import java.util.List;

public class Ffmpeg {

	public static void ProductVideoIndexPicture(String videoRealPath,
			String imageRealPath, String FfmpegPath) {

		// 方法三：调用系统中的可执行程序调用ffmpeg 提取视屏缩略图
		List<String> commend = new java.util.ArrayList<String>();
		commend.add(FfmpegPath + "\\Ffmpeg\\ffmpeg.exe");
		commend.add("-i");
		commend.add(videoRealPath);
		commend.add("-y");
		commend.add("-f");
		commend.add("image2");
		commend.add("-ss");
		commend.add("8");
		commend.add("-t");
		commend.add("0.001");

		commend.add("-s");
		commend.add("350*240");
		commend.add(imageRealPath);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.redirectErrorStream(true);
			System.out.println("视频截图开始...");
			// builder.start();
			Process process = builder.start();
			InputStream in = process.getInputStream();
			byte[] re = new byte[1024];
			System.out.print("正在进行截图，请稍候");
			while (in.read(re) != -1) {
				System.out.print(".");
			}
			System.out.println("");
			in.close();
			System.out.println("视频截图完成...");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("视频截图失败！");
		}
	}
}
