package hhm.database;

import java.sql.*;

//这个类继承自Open_DB类
public class ExecuteDataBase extends DataBaseConnection {
	// 数据库连接对象
	private Connection dbConn;
	private Statement stmt;
	private ResultSet rs;
	// 描述 错误号 0=无错误，-1有错误
	private int errNum;
	// error 描述 错误信息
	private String errDesc;

	// 初始化操作
	public ExecuteDataBase() {
		dbConn = super.getConnection();
		stmt = null;
		rs = null;
		errNum = 0;
		errDesc = "";
	}

	// 执行sql 执行语句，主要是执行插入和删除的SQL语句
	public boolean exeSql(String strSql) {
		System.out.println("开始执行数据库语句:" + strSql);
		try {
			stmt = dbConn.createStatement();
			stmt.executeUpdate(strSql);
			stmt.close();
			return true;
		} catch (Exception ex) {
			this.errNum = -1;
			this.errDesc = ex.toString();
			return false;
		}
	}

	/*
	 * Statement st= con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE );
	 * ResultSet.TYPE_FORWARD_ONLY：在结果集遍历时光标索引只能向前的 ResultSet.TYPE_SCROLL_INSENSITIVE：在结果集遍历时光标索引可以上下移动
	 * ResultSet.TYPE_SCROLL_SENSITIVE：在结果集遍历时光标索引可以上下移动，同时数据要是有并发修改，会立即更新到结果集。 同时也有两种并发类型
	 * ：CONCUR_READ_ONLY：只读CONCUR_UPDATABLE：可更新，对于ResultSet结果集的修改会被更新到数据库。
	 */

	// 执行sql 查询语句
	public ResultSet exeSqlQuery(String strSql) {
		System.out.println("开始执行数据库语句:" + strSql);

		try {
			stmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(strSql);
		} catch (Exception ex) {

			this.errNum = -1;
			this.errDesc = ex.toString();
			rs = null;
		}
		return rs;
	}

	// 取得错误号码
	public int getErrNum() {
		return errNum;
	}

	// 取得错误信息
	public String getErrDesc() {
		return errDesc;
	}
}