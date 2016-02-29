package hhm.Lucene;

import java.io.File;
import java.util.ArrayList;

public class GetFile {
	public ArrayList<String> NameList(String path) {
		ArrayList<String> FileNameList = new ArrayList<String>();
		String FileNameArr[];
		// 得到该路径文件夹下所有的文件
		File file = new File(path);
		FileNameArr = file.list();
		for (int i = 0; i < FileNameArr.length; i++) {
			FileNameList.add(FileNameArr[i]);

		}

		return FileNameList;
	}

	public int Num(String path) {
		File file = new File(path);
		return file.list().length;

	}
}
