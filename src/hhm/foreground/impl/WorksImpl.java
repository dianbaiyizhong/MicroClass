package hhm.foreground.impl;

import java.sql.ResultSet;

import hhm.database.ExecuteDataBase;
import hhm.foreground.pojo.Works;

public class WorksImpl extends ExecuteDataBase {
	public int boardWorksCount(Works works) {
		long boardID = works.getBoardID();
		String strSql = "select WorkID,WorkTitle,UserID,ReadCount,ReCount,CreateTime from works";
		strSql = strSql + " where BoardID = '" + boardID + "'";
		strSql = strSql + " and FatherID = '0'";
		ResultSet rs = null;
		try {

			rs = super.exeSqlQuery(strSql);

			rs.last();

			return rs.getRow();

		} catch (Exception ex) {

			System.out.println("错误指示" + " " + ex.toString());
			return 0;
		}
	}

	// 获得BoardID对应的讨论区的所有精华文章信息，返回一个ResultSet类型对象
	public ResultSet GetAllGoodWorks(Works works) {

		long boardID = works.getBoardID();

		String strSql = "select WorkID,WorkTitle,UserID,ReadCount,ReCount,CreateTime from works";
		strSql = strSql + " where BoardID = '" + boardID + "'";
		strSql = strSql + " and GoodWork = '1' order by CreateTime desc";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	// 这个是按照发表日期的显示的
	public ResultSet GetAllWorks(Works works) {

		long boardID = works.getBoardID();

		String strSql = "select WorkID,WorkTitle,UserID,ReadCount,ReCount,CreateTime from works";
		strSql = strSql + " where BoardID = '" + boardID + "'";
		strSql = strSql + " and FatherID = '0' order by CreateTime desc";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public boolean WorkIDTOinit(Works works) {
		long workID = works.getWorkID();

		String strSql = "select * from works where WorkID=";

		strSql = strSql + "'" + workID + "'";
		try {
			ResultSet rs = super.exeSqlQuery(strSql);
			if (rs.next()) {
				works.setWorkID(rs.getInt("WorkID"));
				works.setWorkTitle(rs.getString("WorkTitle"));
				String Introduce = rs.getString("Introduce").replaceAll("\n", "").replaceAll("r", "");
				works.setIntroduce(Introduce);
				works.setUserID(rs.getInt("UserID"));
				works.setCreateTime(rs.getString("CreateTime"));
				works.setBoardID(rs.getInt("BoardID"));
				works.setReadCount(rs.getInt("ReadCount"));
				works.setReCount(rs.getInt("ReCount"));
				works.setVideoUrl(rs.getString("VideoUrl"));
				works.setIndexPicUrl(rs.getString("IndexPicUrl"));
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	

	public boolean modifyReadCount(Works works) {
		long WorkID = works.getWorkID();
		int readCount = works.getReadCount();
		String strSql = "update Works set";
		strSql = strSql + " ReadCount = '" + readCount + "'";
		strSql = strSql + " where WorkID='" + WorkID + "'";
		boolean isModify = super.exeSql(strSql);
		return isModify;
	}

	public boolean modifyReCount(Works works) {
		int ReCount = works.getReCount();
		long workID = works.getWorkID();
		String strSql = "update works set";
		strSql = strSql + " ReCount = '" + ReCount + "'";
		strSql = strSql + " where WorkID='" + workID + "'";
		boolean isModify = super.exeSql(strSql);
		return isModify;

	}

	// 按照评论的数量进行排行
	public ResultSet GetHotWorks(Works works) {

		long boardID = works.getBoardID();

		String strSql = "select * from works,worksType where BoardID= ";
		strSql = strSql
				+ boardID
				+ " and works.workID = worksType.workID order by ReadCount desc";

		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public ResultSet GetNewlyWorks(Works works) {

		long boardID = works.getBoardID();

		String strSql = "select * from works,worksType where BoardID= ";
		strSql = strSql + boardID
				+ " and works.workID = worksType.workID order by CreateTime";

		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public ResultSet GetAllHotWorks() {
		String strSql = "select * from works,worksType where works.workID = worksType.workID order by ReadCount desc";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public ResultSet GetAllNewlyWorksByBoardID(int BoardID) {
		String strSql = "select * from Works where BoardID = " + BoardID;
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public ResultSet GetSearchResultByLike(String KeyWord) {
		String strSql = "select * from Works where WorkTitle like " + "'%"
				+ KeyWord + "%'" + " or Introduce like " + "'%" + KeyWord
				+ "%'";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public ResultSet GetWorksByID(int BoardID) {
		String strSql = "select * from Works where BoardID = " + BoardID;
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public ResultSet GetWorksByChapterAndBoardID(int Chapter, int BoardID) {
		String strSql = "select * from Works where BoardID = " + BoardID
				+ " and Chapter=" + Chapter;
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public ResultSet GetWorksByID(long BoardID) {
		String strSql = "select * from Works where BoardID = " + BoardID;
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

	public ResultSet GetAllWorksByIDOfReCount(int BoardID) {

		String strSql = "select * from Works where BoardID = " + BoardID
				+ " order by ReCount";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return rs;
	}

	public ResultSet GetAllWorksByIDOfReadCount(int BoardID) {

		String strSql = "select * from Works where BoardID = " + BoardID
				+ " order by ReadCount";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return rs;
	}

}
