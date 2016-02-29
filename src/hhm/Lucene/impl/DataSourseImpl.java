package hhm.Lucene.impl;

import java.sql.ResultSet;
import hhm.database.*;

public class DataSourseImpl extends ExecuteDataBase {

	public ResultSet GetWorkAllContent() {

		String strSql = "select WorkID,WorkTitle,BoardID,Introduce from works";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}

}
