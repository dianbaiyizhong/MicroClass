package hhm.foreground.pojo;

public class Boards {

	private int boardID;
	private String boardName;
	private int boardMasterID;

	public Boards() {
		this.boardID = 0;

	}

	public int getBoardMasterID() {
		return boardMasterID;
	}

	public void setBoardMasterID(int boardMasterID) {
		this.boardMasterID = boardMasterID;
	}

	public int getBoardWorkCount() {
		return boardWorkCount;
	}

	public void setBoardWorkCount(int boardWorkCount) {
		this.boardWorkCount = boardWorkCount;
	}

	private int boardWorkCount;

	public long getBoardID() {
		return boardID;
	}

	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

}
