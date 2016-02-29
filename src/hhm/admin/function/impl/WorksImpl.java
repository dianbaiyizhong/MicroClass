package hhm.admin.function.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import hhm.admin.function.pojo.Works;
import hhm.database.ExecuteDataBase;

public class WorksImpl extends ExecuteDataBase {
	public boolean UploadWork(Works works) {
		String WorkTitle = works.getWorkTitle();
		String WorkContent = works.getWorkContent();
		int AuthorID = works.getAuthorID();
		int BoardID = works.getBoardID();
		String IndexPicUrl = works.getIndexPicUrl();
		String VideoUrl = works.getVideoUrl();
		int Chapter = works.getChapter();

		String strSql = "insert into Works ";
		strSql = strSql + "(";
		strSql = strSql
				+ "WorkTitle,Introduce,UserID,BoardID,IndexPicUrl,VideoUrl,Chapter"
				+ ")";
		strSql = strSql + "values('" + WorkTitle + "','" + WorkContent + "','"
				+ AuthorID + "','" + BoardID + "','" + IndexPicUrl + "','"
				+ VideoUrl +"','" + Chapter+ "')";
		return super.exeSql(strSql);

	}

	public boolean UpdateWorks(int WorkID, String NowText, String AttrName) {
		String strSql = "update Works set " + AttrName;
		strSql = strSql + " ='" + NowText + "' where WorkID = " + "'" + WorkID
				+ "'";
		return super.exeSql(strSql);

	}
	
	
	public boolean UpdateWorksPic(int WorkID ,String IndexPicUrl) {
		String strSql = "update Works set IndexPicUrl" ;
		strSql = strSql + " ='" + IndexPicUrl + "' where WorkID = " + "'" + WorkID
				+ "'";
		return super.exeSql(strSql);

	}
	
	public ResultSet GetAllWorks() {
		String strSql = "select * from Works";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public boolean DeleteWork(int WorkID) {

		String strSql = "delete from Works";
		strSql = strSql + "  where WorkID in (" + WorkID + ")";

		boolean isDelete = super.exeSql(strSql);
		return isDelete;

	}
	
	public String GetWorkPic(String WorkID) throws SQLException {
		String strSql = "select * from Works where WorkID=";
		strSql = strSql + WorkID;
		String WorkPic = "";
		ResultSet rs = super.exeSqlQuery(strSql);
		if (rs.next()) {
			WorkPic = rs.getString("IndexPicUrl");
		}

		return WorkPic;

	}
}
