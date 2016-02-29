package hhm.user.pojo;

public class CompileResult {

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getUseTime() {
		return useTime;
	}

	public void setUseTime(long useTime) {
		this.useTime = useTime;
	}

	private long startTime;
	private long endTime;
	private long useTime;

	public double getUseMemory() {
		return useMemory;
	}

	public void setUseMemory(double useMemory) {
		this.useMemory = useMemory;
	}

	private double useMemory;

	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private String content;
}
