package cn.qianying.graduation.domain;

public class GrabLib {

	private Integer grabId;
	private Integer websiteId;
	private String webSiteAddr;
	private String grabSign;
	private String addTime;

	public GrabLib() {
		super();

	}

	public Integer getGrabId() {
		return grabId;
	}

	public void setGrabId(Integer grabId) {
		this.grabId = grabId;
	}

	public String getWebSiteAddr() {
		return webSiteAddr;
	}

	public void setWebSiteAddr(String webSiteAddr) {
		this.webSiteAddr = webSiteAddr;
	}

	public String getGrabSign() {
		return grabSign;
	}

	public void setGrabSign(String grabSign) {
		this.grabSign = grabSign;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}

}
