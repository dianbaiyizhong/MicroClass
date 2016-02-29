package hhm.user.impl;

import hhm.database.ExecuteDataBase;
import hhm.user.pojo.Notes;

import java.sql.ResultSet;

public class NotesImpl extends ExecuteDataBase {

	public boolean addNotes(Notes notes) {
		int UserID = notes.getUserID();
		int IsOpen = notes.getIsOpen();
		String Time = notes.getTime();
		String Contents = notes.getContents();
		int WorkID = notes.getWorkID();

		String strSql = "";

		strSql = "insert into Notes ";
		strSql = strSql + "(UserID,WorkID,Time,Contents,IsOpen) ";
		strSql = strSql + "values('" + UserID + "','" + WorkID + "','" + Time
				+ "','" + Contents + "','" + IsOpen + "')";
		boolean isAdd = super.exeSql(strSql);

		return isAdd;
	}

	public ResultSet GetWorkNewlyeNotes(int WorkID) {

		String strSql = "select Notes.UserID,Contents,Time,UserName,UserHeadPicture,LikeCount,NoteID from Notes,Users";
		strSql = strSql + " where WorkID = '" + WorkID + "'";
		strSql = strSql + " and Notes.UserID = Users.UserID ";
		strSql = strSql + " order by Notes.CreateTime DESC";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public ResultSet GetWorkGoodNotes(int WorkID) {

		String strSql = "select Notes.UserID,Contents,Time,UserName,UserHeadPicture,LikeCount,NoteID from Notes,Users";
		strSql = strSql + " where WorkID = '" + WorkID + "'";
		strSql = strSql + " and Notes.UserID = Users.UserID ";
		strSql = strSql + " order by LikeCount desc";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public ResultSet GetWorkMyNotes(int WorkID, long MyUserID) {

		String strSql = "select Notes.UserID,Contents,Time,UserName,UserHeadPicture,LikeCount,NoteID from Notes,Users";
		strSql = strSql + " where WorkID = '" + WorkID + "'";
		strSql = strSql + " and Notes.UserID = Users.UserID and Notes.UserID="
				+ "'" + MyUserID + "'";
		strSql = strSql + " order by Time";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public boolean ModifyLikeCountForNote(int NoteID) {
		String strSql = "update Notes set";
		strSql = strSql + " LikeCount = LikeCount+1";
		strSql = strSql + " where NoteID='" + NoteID + "'";
		boolean isModify = super.exeSql(strSql);
		return isModify;

	}

	// 取得获取曾经做过笔记的作品
	public ResultSet GetWorkOfMyNotes(long MyUserID) {

		String strSql = "select distinct(Works.WorkTitle),Works.WorkID  from Notes,Works where Notes.WorkID = Works.WorkID ";
		strSql = strSql + "  and Notes.UserID=" + "'" + MyUserID + "'";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public ResultSet GetMyNotesByWorkID(int WorkID, long UserID) {
		String strSql = "select * from Notes,Works where Notes.WorkID = Works.WorkID and Notes.WorkID =";
		strSql = strSql + "'" + WorkID + "'" + " and Notes.UserID=" + "'" + UserID
				+ "'";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}
	
	
	
	public String GetIndexPicUrlByWorkID(int WorkID) {

		String strSql = "select IndexPicUrl from Works";
		strSql = strSql + " where WorkID = '" + WorkID + "'";
		String IndexPic = "";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
			if (rs.next()) {
				IndexPic = rs.getString("IndexPicUrl");
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return IndexPic;
	}
	
	
	
	

}
