package hhm.Lucene;

import hhm.Lucene.impl.DataSourseImpl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;

public class MainIndex {
	IndexDao indexDao = new IndexDao();

	String basicfilepath = "D:\\Users\\Administrator\\Workspaces\\MyEclipse 8.5\\.metadata\\.me_tcat\\webapps\\MicroClass\\datasourse\\";
	//String basicfilepath = "F:\\apache-tomcat-6.0.37-windows-x86\\apache-tomcat-6.0.37\\webapps\\CDSCP\\datasourse\\";

	// String indexpath = "\\CDSCP\\luceneIndex";

	DataSourseImpl dataSourseImpl = new DataSourseImpl();
	WriteData2Txt writeData2Txt = new WriteData2Txt();
	GetFile getFile = new GetFile();
	Analyzer analyzer = new StandardAnalyzer();

	@Test
	public void start() throws IOException {
		CreateIndexFile();
		createSearchIndex();
		SearchIndex();

	}

	/**
	 * 读取数据库的信息，把他们转换为文件保存
	 */
	public void CreateIndexFile() {
		ResultSet rs = dataSourseImpl.GetWorkAllContent();
		try {
			while (rs.next()) {
				String filepath = basicfilepath + rs.getString("WorkTitle") + ".txt";
				try {
					writeData2Txt.WriteData2Txt(filepath, rs.getString("Introduce"), rs.getString("BoardID"), rs
							.getString("WorkID"), rs.getString("WorkTitle"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建索引，这里利用到了中文分词器
	 * 
	 * @throws IOException
	 */

	public void createSearchIndex() throws IOException {
		ArrayList<String> fileNameList = getFile.NameList(basicfilepath);
		for (int i = 0; i < fileNameList.size(); i++) {
			String filepath = basicfilepath + fileNameList.get(i);
			// 从file到doc的转换
			Document doc = File2DocumentUtils.file2Document(filepath);
			// 哪个文档比较重要，就在这里设置权重,doc.setBoost(3f);
			indexDao.save(doc);

		}

	}

	public void DeleteIndexFile() {
		ArrayList<String> fileNameList = getFile.NameList(basicfilepath);
		for (int i = 0; i < fileNameList.size(); i++) {
			String filepath = basicfilepath + fileNameList.get(i);
			Term term = new Term("path", filepath);
			indexDao.delete(term);

		}

	}

	public void UpdateIndex() throws IOException {
		ArrayList<String> fileNameList = getFile.NameList(basicfilepath);
		for (int i = 0; i < fileNameList.size(); i++) {
			String filepath = basicfilepath + fileNameList.get(i);
			Term term = new Term("path", filepath);
			Document doc = File2DocumentUtils.file2Document(filepath);
			doc.getField("content").setValue("这是更新后的文件内容");
			indexDao.update(term, doc);

		}

	}

	/**
	 * 开始搜索
	 */
	public void SearchIndex() {
		String queryString = "基本";
		QueryResult qr = indexDao.search(queryString, 0, 10,"");
		System.out.println("总共有【" + qr.getRecordCount() + "】条匹配结果");
		for (Document doc : qr.getRecordList()) {
			File2DocumentUtils.printDocumentInfo(doc);
		}
	}

}
