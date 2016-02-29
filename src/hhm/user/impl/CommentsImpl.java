package hhm.user.impl;

import hhm.database.ExecuteDataBase;
import hhm.user.pojo.Comments;

public class CommentsImpl extends ExecuteDataBase {

	public boolean AddReplyComment(Comments comments) {
		String Content = comments.getContents();
		long userID = comments.getUserID();
		int workID = comments.getWorkID();
		int RepliedCommentID = comments.getCommentID();
		String strSql = "insert into Comments";
		strSql = strSql + "(";
		strSql = strSql + "Contents,UserID,RepliedCommentID,WorkID" + ")";
		strSql = strSql + " values('" + Content + "','" + userID + "','"
				+ RepliedCommentID + "','" + workID + "')";
		boolean isAdd = super.exeSql(strSql);
		return isAdd;
	}

	public boolean AddFloorComment(Comments comments) {
		String Content = comments.getContents();
		long userID = comments.getUserID();
		int workID = comments.getWorkID();
		String strSql = "insert into Comments";
		strSql = strSql + "(";
		strSql = strSql + "Contents,UserID,WorkID" + ")";
		strSql = strSql + " values('" + Content + "','" + userID + "','"
				+ workID + "')";
		boolean isAdd = super.exeSql(strSql);
		return isAdd;
	}

}
