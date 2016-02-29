package hhm.foreground.pojo;

public class Comments {

	private String contents;
	private String createTime;
	private String userHeadPicture;
	private int userID;
	private String userName;
	private String commentRepliedUserName;
	private int commentID;
	public int getCommentRepliedID() {
		return commentRepliedID;
	}

	public void setCommentRepliedID(int commentRepliedID) {
		this.commentRepliedID = commentRepliedID;
	}

	private int commentRepliedID;


	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getCommentRepliedUserName() {
		return commentRepliedUserName;
	}

	public void setCommentRepliedUserName(String commentRepliedUserName) {
		this.commentRepliedUserName = commentRepliedUserName;
	}
	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String content) {
		this.contents = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserHeadPicture() {
		return userHeadPicture;
	}

	public void setUserHeadPicture(String userHeadPicture) {
		this.userHeadPicture = userHeadPicture;
	}

}
