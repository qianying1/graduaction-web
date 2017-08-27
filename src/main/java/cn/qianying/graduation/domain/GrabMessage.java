package cn.qianying.graduation.domain;

public class GrabMessage {

	private Integer msgId;
	private String videoName; // 视频名称
	private String playCount; // 视频播放次数
	private Integer likeCount; // 收藏数
	private Integer commentCount; // 评论数
	private Integer barrage; // 弹幕数
	private Integer bananaCount; // 投蕉数
	private String videoAddTime; // 视频发布时间
	private Integer authorId; // 作者
	private String videoType; // 视频类型

	public GrabMessage() {
		super();

	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getPlayCount() {
		return playCount;
	}

	public void setPlayCount(String playCount) {
		this.playCount = playCount;
	}

	public Integer getBarrage() {
		return barrage;
	}

	public void setBarrage(Integer barrage) {
		this.barrage = barrage;
	}

	public Integer getBananaCount() {
		return bananaCount;
	}

	public void setBananaCount(Integer bananaCount) {
		this.bananaCount = bananaCount;
	}

	public String getVideoAddTime() {
		return videoAddTime;
	}

	public void setVideoAddTime(String videoAddTime) {
		this.videoAddTime = videoAddTime;
	}

	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

}
