package hhm.foreground.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import hhm.database.ExecuteDataBase;
import hhm.foreground.pojo.Users;

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
		strSql = strSql + " Email=" + "'" + Email + "',";
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
		String Sex = users.getSex();
		String NickName = users.getNickName();
		String Sign = users.getSign();
		String CreateTime = users.getCreateTime();

		strSql = "insert into users ";
		strSql = strSql + "(UserName,UserPassword,Email,Sex,NickName,Sign,CreateTime) ";
		strSql = strSql + "values('" + UserName + "','" + UserPassword + "','" + Email + "','" + Sex + "','" + NickName + "','"
				+ Sign + "','" + CreateTime + "')";
		boolean isAdd = super.exeSql(strSql);

		return isAdd;
	}

	// 修改UserID对应的用户的密码
	public boolean modifyUserPassword(Users users) {
		String strSql = users.getStrSql();
		String UserPassword = users.getUserPassword();
		long UserID = users.getUserID();

		strSql = "update users set ";
		strSql = strSql + "UserPassword=" + "'" + UserPassword + "'";
		strSql = strSql + " where UserID='" + UserID + "'";
		boolean isUpdatekey = super.exeSql(strSql);

		return isUpdatekey;
	}

	// 删除属于某个集合中的用户信息
	public boolean deleteUser(String s_UserID) {

		String strSql = "delete from users";
		strSql = strSql + "  where UserID in (" + s_UserID + ")";

		boolean isDelete = super.exeSql(strSql);
		return isDelete;

	}


	
	public boolean UserIDToInit(Users users) {
		long UserID = users.getUserID();

		String strSql = "select * from users where UserID=";
		strSql = strSql + "'" + UserID + "'";

		try {
			ResultSet rs = super.exeSqlQuery(strSql);
			if (rs.next()) {
				users.setUserID(rs.getInt("UserID"));
				users.setUserName(rs.getString("UserName"));
				users.setUserPassword(rs.getString("Password"));
//				users.setSex(rs.getString("Sex"));
//				users.setNickName(rs.getString("NickName"));
//				users.setSign(rs.getString("Sign"));
				users.setEmail(rs.getString("Mail"));
				users.setCreateTime(rs.getString("CreateTime"));
				users.setUserHeadPicture(rs.getString("UserHeadPicture"));
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
	public boolean UserNameToInit(Users users) {

		String UserName = users.getUserName();

		String strSql = "select * from users where UserName=";
		strSql = strSql + "'" + UserName + "'";

		try {
			ResultSet rs = super.exeSqlQuery(strSql);
			if (rs.next()) {
				users.setUserID(rs.getInt("UserID"));
				users.setUserName(rs.getString("UserName"));
				users.setUserPassword(rs.getString("UserPassword"));
				users.setSex(rs.getString("Sex"));
				users.setNickName(rs.getString("NickName"));
				users.setSign(rs.getString("Sign"));
				users.setEmail(rs.getString("Email"));
				users.setCreateTime(rs.getString("CreateTime"));
				users.setUserHeadPicture(rs.getString("UserHeadPicture"));
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}

	// 获得UserName对应的用户的信息，从而返回UserID
	public long GetUserID(String UserName) {
		String strSql = "select * from users where UserName like ";
		strSql = strSql + "'" + UserName + "'";
		long UserID = 0;
		ResultSet rs = super.exeSqlQuery(strSql);
		try {
			if (rs.next()) {
				UserID = rs.getLong("UserID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return UserID;

	}

	// 验证用户名和密码是否正确
	public boolean userValid(Users users) {
		String strSql = users.getStrSql();
		String UserName = users.getUserName();
		String UserPassword = users.getUserPassword();
		strSql = "select UserID,UserName from Users ";
		strSql = strSql + "where UserName='" + UserName + "'";
		strSql = strSql + "  and Password='" + UserPassword + "'";

		try {
			ResultSet rs = super.exeSqlQuery(strSql);
			if (rs.next()) {
				users.setUserID(rs.getInt("UserID"));
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

	// 获得所有用户信息，返回一个ResultSet类型对象
	public ResultSet GetAllUsers() {
		String strSql = "select * from users order by UserName asc";
		ResultSet rs = null;
		try {
			rs = super.exeSqlQuery(strSql);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return rs;
	}
	
	public String GetUserHeadPicByID(long UserID) {
		String strSql = "select * from Users where UserID = ";
		strSql = strSql + "'" + UserID + "'";
		ResultSet rs = super.exeSqlQuery(strSql);
		String Pic = "";
		try {
			if (rs.next()) {
				Pic = rs.getString("UserHeadPicture");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Pic;

	}
	
	
	
	
	
	
	
	
	
	

}
