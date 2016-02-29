package hhm.admin.function.pojo;

public class Works {

	private String workTitle;
	private String workContent;
	private String createTime;
	private String indexPicUrl;
	private int authorID;
	private int boardID;

	public int getChapter() {
		return Chapter;
	}

	public void setChapter(int chapter) {
		Chapter = chapter;
	}

	private int Chapter;

	public int getWorkID() {
		return workID;
	}

	public void setWorkID(int workID) {
		this.workID = workID;
	}

	private int workID;

	private String videoUrl;

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getWorkTitle() {
		return workTitle;
	}

	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getIndexPicUrl() {
		return indexPicUrl;
	}

	public void setIndexPicUrl(String indexPicUrl) {
		this.indexPicUrl = indexPicUrl;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public int getBoardID() {
		return boardID;
	}

	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}

	public Works() {
		this.workTitle = "";
		this.workContent = "";
		this.boardID = 0;
		java.util.Date NowTime = new java.util.Date();
		this.createTime = NowTime.toLocaleString();
	}

}