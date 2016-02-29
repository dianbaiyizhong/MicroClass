package hhm.user.impl;

import hhm.database.ExecuteDataBase;
import hhm.user.pojo.Users;

import java.sql.*;

public class UsersImpl extends ExecuteDataBase {

	// 修改UserID对应的用户的信息
	public boolean modifyUserInfo(Users users) {
		String strSql = users.getStrSql();
		String Email = users.getEmail();
		String Sex = users.getSex();
		String NickName = users.getNickName();
		String Sign = users.getSign();
		String CreateTime = users.getCreateTime();
		long UserID = users.getUserID();

		strSql = "update users set";
		strSql = strSql + " EmailImpl=" + "'" + Email + "',";
		strSql = strSql + " Sex=" + "'" + Sex + "',";
		strSql = strSql + " NickName=" + "'" + NickName + "',";
		strSql = strSql + " Sign=" + "'" + Sign + "',";
		strSql = strSql + " CreateTime=" + "'" + CreateTime + "'";
		strSql = strSql + " where UserID='" + UserID + "'";
		boolean isUpdate = super.exeSql(strSql);

		return isUpdate;
	}

	// 添加新用户，往users数据表中添加一条新记录
	public boolean addUser(Users users) {

		String strSql = users.getStrSql();
		String UserName = users.getUserName();
		String UserPassword = users.getUserPassword();
		String Email = users.getEmail();

		strSql = "insert into users ";
		strSql = strSql + "(UserName,Password,Mail) ";
		strSql = strSql + "values('" + UserName + "','" + UserPassword + "','"
				+ Email + "')";
		System.out.println("开始执行数据库语句:" + strSql);
		boolean isAdd = super.exeSql(strSql);

		return isAdd;
	}

	// 修改UserID对应的用户的密码
	public boolean modifyUserPassword(String Password, long UserID) {

		String strSql = "update users set ";
		strSql = strSql + "Password=" + "'" + Password + "'";
		strSql = strSql + " where UserID='" + UserID + "'";
		
		boolean isUpdatekey = super.exeSql(strSql);

		return isUpdatekey;
	}

	// 获得UserID对应的用户的信息，将这些信息赋值给相应的类变量
	public boolean UserIDToInit(Users users) {

		long UserID = users.getUserID();
		String strSql = "select * from Users where UserID=";
		strSql = strSql + "'" + UserID + "'";

		try {
			ResultSet rs = super.exeSqlQuery(strSql);
			if (rs.next()) {
				users.setUserID(rs.getLong("UserID"));
				users.setUserName(rs.getString("UserName"));
				users.setUserPassword(rs.getString("Password"));
				users.setEmail(rs.getString("Mail"));
				users.setCreateTime(rs.getString("CreateTime"));
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}

	// 获得UserName对应的用户的信息，将这些信息赋值给相应的类变量
	public boolean init(String UserName) {
		Users users = new Users();

		String strSql = "select * from users where UserName like ";
		strSql = strSql + "'" + UserName + "'";

		try {
			ResultSet rs = super.exeSqlQuery(strSql);
			if (rs.next()) {
				users.setUserID(rs.getLong("UserID"));
				users.setUserName(rs.getString("UserName"));
				users.setUserPassword(rs.getString("UserPassword"));
				users.setSex(rs.getString("Sex"));
				users.setNickName(rs.getString("NickName"));
				users.setSign(rs.getString("Sign"));
				users.setEmail(rs.getString("EmailImpl"));
				users.setCreateTime(rs.getString("CreateTime"));
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}

	// 验证用户名和密码是否正确
	public boolean userValid(Users users) {
		String strSql = users.getStrSql();
		String UserName = users.getUserName();
		String UserPassword = users.getUserPassword();
		strSql = "select UserID,UserName from users ";
		strSql = strSql + " where UserName='" + UserName + "'";
		strSql = strSql + "  and UserPassword='" + UserPassword + "'";

		try {
			ResultSet rs = super.exeSqlQuery(strSql);
			if (rs.next()) {
				users.setUserID(rs.getLong("UserID"));
				users.setUserName(rs.getString("UserName"));
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	// 判断用户名是否存在
	public boolean isExist(Users users) {
		String UserName = users.getUserName();
		String strSql = "select * from users ";
		strSql = strSql + " where UserName='" + UserName + "'";

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

	// 验证用户名和密码是否正确
	public boolean userValid(long UserID, String Password) {

		String strSql = "select UserID,Password from Users ";
		strSql = strSql + "where Password='" + Password + "'";
		strSql = strSql + "  and UserID='" + UserID + "'";

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
