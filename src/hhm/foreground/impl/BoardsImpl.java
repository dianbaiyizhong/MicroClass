package hhm.foreground.impl;

import hhm.database.ExecuteDataBase;
import hhm.foreground.pojo.Boards;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardsImpl extends ExecuteDataBase {
	public ResultSet GetAllBoards() {
		String strSql = "select * from Boards";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public ResultSet GetGoodBoards() {
		String strSql = "select * from Boards where IsGood='1'";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	// 获得BoardID对应的讨论区的信息，将这些信息赋值给相应的类变量
	public boolean BoardIDToInit(Boards boards) {
		long BoardID = boards.getBoardID();

		String strSql = "select * from Boards where BoardID=";
		strSql = strSql + "'" + BoardID + "'";
		try {
			ResultSet rs = super.exeSqlQuery(strSql);
			if (rs.next()) {
				boards.setBoardID(rs.getInt("BoardID"));
				boards.setBoardName(rs.getString("BoardName"));
				boards.setBoardMasterID(rs.getInt("BoardMasterID"));
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}

	}

	// 已经WorkID，获得BoardID
	public Long GetBoardID(String WorkID) throws SQLException {
		String strSql = "select * from works where WorkID=";
		strSql = strSql + WorkID;
		Long BoardID = null;
		ResultSet rs = super.exeSqlQuery(strSql);
		if (rs.next()) {
			BoardID = rs.getLong("BoardID");
		}

		return BoardID;

	}

	public boolean BoardNameToInit(Boards boards) {
		String BoardName = boards.getBoardName();
		String strSql = "select * from boards where BoardName=";
		strSql = strSql + "'" + BoardName + "'";
		try {
			ResultSet rs = super.exeSqlQuery(strSql);
			if (rs.next()) {
				boards.setBoardID(rs.getInt("BoardID"));
				boards.setBoardName(rs.getString("BoardName"));

				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}
	
	public ResultSet GetBoardByID(int BoardID) {
		String strSql = "select * from Boards where BoardID = " + BoardID;
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}
	
	
	public int GetBoardReCountByID(int BoardID) throws SQLException {
		String strSql = "select sum(ReCount) as 'ReCount' from works where BoardID=";
		strSql = strSql + BoardID;
		int ReCount = 0;
		ResultSet rs = super.exeSqlQuery(strSql);
		if (rs.next()) {
			ReCount = rs.getInt("ReCount");
		}

		return ReCount;

	}
	
	
	public int GetBoardReadCountByID(int BoardID) throws SQLException {
		String strSql = "select sum(ReadCount) as 'ReadCount' from works where BoardID=";
		strSql = strSql + BoardID;
		int ReadCount = 0;
		ResultSet rs = super.exeSqlQuery(strSql);
		if (rs.next()) {
			ReadCount = rs.getInt("ReadCount");
		}

		return ReadCount;

	}




}
