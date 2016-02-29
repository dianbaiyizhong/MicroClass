package hhm.Lucene.pojo;

public class SearchResult {

	public long getBoardID() {
		return boardID;
	}

	public void setBoardID(long boardID) {
		this.boardID = boardID;
	}

	public long getWorkID() {
		return workID;
	}

	public void setWorkID(long workID) {
		this.workID = workID;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public String getWorkTitle() {
		return workTitle;
	}

	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}

	public String getIndexPic() {
		return indexPic;
	}

	public void setIndexPic(String indexPic) {
		this.indexPic = indexPic;
	}



	private long boardID;
	private long workID;
	private String workContent;

	private String workTitle;
	private String indexPic;

	private int reCount;
	public int getReCount() {
		return reCount;
	}

	public void setReCount(int reCount) {
		this.reCount = reCount;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}



	private int readCount;

}
