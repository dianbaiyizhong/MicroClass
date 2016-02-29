package hhm.Lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

public class ContentDirectory {
	String filepath = "D:\\我的资源库\\Workspaces\\MyEclipse 8.5\\LuceneDemo\\datasourse\\IndexWriter addDocument's a javadoc .txt";

	String indexpath = "D:\\我的资源库\\Workspaces\\MyEclipse 8.5\\LuceneDemo\\luceneIndex";

	Analyzer analyzer = new StandardAnalyzer();

	@Test
	public void test1() throws Exception {
		// 代表磁盘上的:Directory dir = FSDirectory.getDirectory(indexpath);
		// 代表内存当中的索引目录,速度快，但是退出就没了
		Directory dir = new RAMDirectory();
		Document doc = File2DocumentUtils.file2Document(filepath);
		IndexWriter indexWriter = new IndexWriter(dir, analyzer, true, MaxFieldLength.LIMITED);
		indexWriter.addDocument(doc);
		indexWriter.close();

	}

	@Test
	public void test2() throws Exception {
		Directory fsDir = FSDirectory.getDirectory(indexpath);

		// 1.启动的时候读取
		Directory ramDir = new RAMDirectory(fsDir);

		// 运行程序时操作ramDir
		IndexWriter ramIndexWriter = new IndexWriter(ramDir, analyzer, MaxFieldLength.LIMITED);

		// 添加一个文档
		Document doc = File2DocumentUtils.file2Document(filepath);
		ramIndexWriter.addDocument(doc);
		ramIndexWriter.close();
		// 2.退出的时候保存
		IndexWriter fsIndexWriter = new IndexWriter(fsDir, analyzer, true, MaxFieldLength.LIMITED);
		fsIndexWriter.addIndexesNoOptimize(new Directory[] { ramDir });

		// fsIndexWriter.flush();

		fsIndexWriter.close();

	}

	/*
	 * 优化，可以减少文件，也就是合并文件
	 */
	@Test
	public void test3() throws Exception {
		Directory fsDir = FSDirectory.getDirectory(indexpath);

		IndexWriter fsIndexWriter = new IndexWriter(fsDir, analyzer, true, MaxFieldLength.LIMITED);

		fsIndexWriter.optimize();
		fsIndexWriter.close();

	}

}
