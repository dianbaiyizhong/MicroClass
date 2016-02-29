package hhm.admin.function.impl;

import hhm.database.ExecuteDataBase;

import java.sql.ResultSet;

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

	public boolean DeleteBoard(int BoardID) {

		String strSql = "delete from Boards";
		strSql = strSql + "  where BoardID in (" + BoardID + ")";

		boolean isDelete = super.exeSql(strSql);
		return isDelete;

	}

	public boolean AddBoard(String BoardName, String Introduce) {

		String strSql = "insert into Boards ";
		strSql = strSql + "(";
		strSql = strSql + "BoardName,Introduce" + ")";
		strSql = strSql + " values ('" + BoardName + "','" + Introduce + "')";
		return super.exeSql(strSql);

	}

	public boolean UpdateBoardPic(int BoardID, String IndexPicUrl) {
		String strSql = "update Boards set IndexPictureUrl";
		strSql = strSql + " ='" + IndexPicUrl + "' where BoardID = " + "'"
				+ BoardID + "'";
		return super.exeSql(strSql);

	}

	

	public boolean UpdateBoard(int ID, String NowText, String AttrName) {
		String strSql = "update Boards set " + AttrName;
		strSql = strSql + " ='" + NowText + "' where BoardID = " + "'" + ID
				+ "'";
		return super.exeSql(strSql);

	}
}
