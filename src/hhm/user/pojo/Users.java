package hhm.user.pojo;

public class Users {
	public Users() {
		this.userID = 0;
		this.userName = "";
		this.userPassword = "";
		this.sex = "";
		this.sign = "";
		this.nickName = "";
		this.email = "";
		java.util.Date NowTime = new java.util.Date();
		this.createTime = NowTime.toLocaleString();
		this.strSql = "";
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStrSql() {
		return strSql;
	}

	public void setStrSql(String strSql) {
		this.strSql = strSql;
	}

	private long userID;
	private String userName;
	private String userPassword;
	private String email;
	private String sex;
	private String nickName;
	private String sign;
	private String createTime;
	private String strSql;

}
