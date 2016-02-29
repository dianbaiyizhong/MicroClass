package hhm.user.impl;

import java.sql.ResultSet;

import hhm.database.ExecuteDataBase;
import hhm.foreground.pojo.Users;

public class CollectionsImpl extends ExecuteDataBase {

	// 为用户添加收藏
	public boolean addCollection(int WorkID, long UserID) {

		String strSql = "insert into Collections ";
		strSql = strSql + "(WorkID,UserID) ";
		strSql = strSql + "values('" + WorkID + "','" + UserID + "')";
		boolean isAdd = super.exeSql(strSql);
		return isAdd;
	}

	// 判断用户是否已经收藏了这个作品
	public boolean isUserHaveCollectThisWork(int WorkID, long UserID) {
		String strSql = "select * from Collections ";
		strSql = strSql + " where WorkID='" + WorkID + "'" + " and UserID='"
				+ UserID + "'";

		try {
			ResultSet rs = super.exeSqlQuery(strSql);
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	public ResultSet GetAllCollection(long UserID) {
		String strSql = "select * from Users,Collections,Works where Users.UserID = Collections.UserID and Collections.WorkID = Works.WorkID and Users.UserID="
				+ UserID;
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;

	}

	public ResultSet GetAllCollection(int UserID) {
		String strSql = "select * from Users,Collections,Works where Users.UserID = Collections.UserID and Collections.WorkID = Works.WorkID and Users.UserID="
				+ UserID;
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;

	}

	public boolean DeleteCollection(int CollectionID, long UserID) {

		String strSql = "delete  Collections ";
		strSql = strSql + "where UserID=" + UserID + " and CollectionID="
				+ CollectionID;
		boolean isAdd = super.exeSql(strSql);
		return isAdd;
	}

}
