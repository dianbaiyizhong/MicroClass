package hhm.foreground.impl;

import hhm.database.ExecuteDataBase;
import hhm.foreground.pojo.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentsImpl extends ExecuteDataBase {

	public ResultSet GetWorkAllComments(int WorkID) {

		String strSql = "select UserID,Contents,CreateTime,CommentID from Comments where  RepliedCommentID is  NULL";
		strSql = strSql + " and WorkID = '" + WorkID + "'";
		// strSql = strSql + " order by CreateTime desc";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public ResultSet GetCommentsReply(int CommentID) {
		String strSql = "select a.* from Comments a,FindCommentChildren("
				+ CommentID
				+ ") b where a.CommentID = b.CommentID and RepliedCommentID is not null order by a.CommentID";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public int GetUserIDByCommentID(int CommentID) {
		String strSql = "select * from Comments where CommentID = ";
		strSql = strSql + "'" + CommentID + "'";
		int UserID = 0;
		ResultSet rs = super.exeSqlQuery(strSql);
		try {
			if (rs.next()) {
				UserID = rs.getInt("UserID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return UserID;

	}

	public boolean isExistComment(String WorkID) {

		String strSql = "select * FROM Comments WHERE WorkID = " + WorkID;

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

}
