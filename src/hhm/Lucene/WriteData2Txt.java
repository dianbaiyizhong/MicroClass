package hhm.Lucene;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteData2Txt {

	public void WriteData2Txt(String filepath, String WorkContent, String BoardID, String WorkID, String WorkTitle)
			throws IOException {

		File file = new File(filepath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "utf-8");

		BufferedWriter writer = new BufferedWriter(write);
		writer.write(WorkTitle + "\r\n");
		writer.write(BoardID + "\r\n");
		writer.write(WorkID + "\r\n");
		writer.write(WorkContent + "\r\n");

		writer.close();
	}

}
