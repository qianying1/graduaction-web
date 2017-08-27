package cn.qianying.graduation.domain;

public class AnalizedMessage {

	private Integer msgId;
	private String videoStyle;
	private String videoName;
	private Integer playTimes;
	private String averageEval;
	private String mostLikeUserLocation;
	private Integer videoCommentTimes;

	public AnalizedMessage() {
		super();
	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getVideoStyle() {
		return videoStyle;
	}

	public void setVideoStyle(String videoStyle) {
		this.videoStyle = videoStyle;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public Integer getPlayTimes() {
		return playTimes;
	}

	public void setPlayTimes(Integer playTimes) {
		this.playTimes = playTimes;
	}

	public String getAverageEval() {
		return averageEval;
	}

	public void setAverageEval(String averageEval) {
		this.averageEval = averageEval;
	}

	public String getMostLikeUserLocation() {
		return mostLikeUserLocation;
	}

	public void setMostLikeUserLocation(String mostLikeUserLocation) {
		this.mostLikeUserLocation = mostLikeUserLocation;
	}

	public Integer getVideoCommentTimes() {
		return videoCommentTimes;
	}

	public void setVideoCommentTimes(Integer videoCommentTimes) {
		this.videoCommentTimes = videoCommentTimes;
	}

}
