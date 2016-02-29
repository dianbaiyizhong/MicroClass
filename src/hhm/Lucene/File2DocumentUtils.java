package hhm.Lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumberTools;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

public class File2DocumentUtils {

	public static Document file2Document(String path) throws IOException {
		File file = new File(path);
		Document doc = new Document();
		Map<String, String> map = readFileAllMessage(file);

		doc.add(new Field("WorkTitle", file.getName(), Store.YES,
				Index.ANALYZED));
		doc.add(new Field("WorkContent", map.get("WorkContent"), Store.YES,
				Index.ANALYZED));
		doc.add(new Field("BoardID", map.get("BoardID"), Store.YES,
				Index.NOT_ANALYZED));
		doc.add(new Field("WorkID", map.get("WorkID"), Store.YES,
				Index.NOT_ANALYZED));
		doc.add(new Field("size", NumberTools.longToString(file.length()),
				Store.YES, Index.NOT_ANALYZED));
		doc.add(new Field("path", file.getAbsolutePath(), Store.YES,
				Index.NOT_ANALYZED));
		return doc;
	}

	public static Map<String, String> readFileAllMessage(File file)
			throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(
					new FileInputStream(file), "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String line = bufferedReader.readLine();
			map.put("WorkTitle", line);
			line = bufferedReader.readLine();

			map.put("BoardID", line);
			line = bufferedReader.readLine();
			map.put("WorkID", line);

			String WorkContentLine = "";
			while ((line = bufferedReader.readLine()) != null) {

				WorkContentLine = WorkContentLine + line;

			}
			map.put("WorkContent", WorkContentLine);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;

	}

	/**
	 * 读取文件内容
	 */
	public static String readFileContent(File file) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "utf-8"));
			StringBuffer content = new StringBuffer();

			for (String line = null; (line = reader.readLine()) != null;) {
				content.append(line).append("\n");
			}

			return content.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <pre>
	 * 获取 name 属性的值的两种方法：
	 * 1，Field f = doc.getField(&quot;name&quot;);
	 *    f.stringValue();
	 * 2，doc.get(&quot;name&quot;);
	 * </pre>
	 * 
	 * @param doc
	 */
	public static void printDocumentInfo(Document doc) {
		// Field f = doc.getField("name");
		// f.stringValue();
		System.out.println("------------------------------");
		System.out.println("name     = " + doc.get("name"));
		System.out.println("content  = " + doc.get("content"));
		System.out.println("size     = "
				+ NumberTools.stringToLong(doc.get("size")));
		System.out.println("path     = " + doc.get("path"));
	}

}
