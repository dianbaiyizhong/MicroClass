package hhm.user.impl;

import hhm.database.ExecuteDataBase;
import hhm.user.pojo.Works;

import java.sql.ResultSet;

public class WorksImpl extends ExecuteDataBase {

	// 获取WorkID对应的作品的信息，将这些信息赋值给相应的类变量
	public boolean WorkIDTOInit(Works works) {
		String strSql = "";
		int workID = works.getWorkID();

		strSql = "select * from Works where WorkID=";

		strSql = strSql + "'" + workID + "'";
		try {
			ResultSet rs = super.exeSqlQuery(strSql);
			if (rs.next()) {

				works.setBoardID(rs.getInt("BoardID"));
				works.setWorkID(rs.getInt("WorkID"));
				works.setWorkIntroduce(rs.getString("Introduce"));
				works.setWorkName(rs.getString("WorkName"));
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}
	
}
