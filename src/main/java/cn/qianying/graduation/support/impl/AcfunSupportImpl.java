package cn.qianying.graduation.support.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.qianying.graduation.dao.mapper.GrabLibMapper;
import cn.qianying.graduation.dao.mapper.GrabMessageMapper;
import cn.qianying.graduation.dao.mapper.VideoAuthorMapper;
import cn.qianying.graduation.domain.GrabMessage;
import cn.qianying.graduation.domain.VideoAuthor;
import cn.qianying.graduation.support.AcfunSupport;

/**
 * 对acfun视频网站进行叶面抓取分析
 * @author qianying
 *
 */
@Component("acfunSupportImpl")
public class AcfunSupportImpl extends CommonSupportImpl implements AcfunSupport {

	@Autowired
	GrabMessageMapper grabMessageMapper;
	@Autowired
	VideoAuthorMapper videoAuthorMapper;
	@Autowired
	GrabLibMapper grabLibMapper;

	/**
	 * 分析主页面的头部信息
	 */
	@Override
	public List<String> analizeHeader(Element header) {

		Element nav = header.getElementById("nav");
		Elements ahrefs = nav.select("a");

		List<String> urls = new ArrayList<String>();
		for (Element ahref : ahrefs) {

			String url = ahref.attr("abs:href");
			if (null != url && !"".equals(url)) {
				urls.add(url);
			}
		}

		return urls;
	}

	/**
	 * 分析页面体中的主要信息部分
	 */
	@Override
	public void analizeBodyMain(Element bodyMain) {

		Element firstSection = bodyMain.select("section").first();
		Elements otherSections = bodyMain.select("section");
		otherSections.remove(0);
		analizeFirstSection(firstSection);
		analizeOtherSections(otherSections);
	}

	/**
	 * 分析除了第一个section以外的其他section的信息
	 * @param otherSections
	 */
	private void analizeOtherSections(Elements otherSections) {

	}

	/**
	 * 使用广度优先算法进行页面抓取
	 */
	@Override
	public void analyWebByBF() {

	}

	/**
	 * 分析页面的第一个section节点信息
	 * @param firstSection
	 */
	private void analizeFirstSection(Element firstSection) {

		//获取第一个Section中的内容节点
		Element sliderWrap = firstSection.getElementsByClass("slider-wrap").get(0);
		Element sliderRight = firstSection.getElementsByClass("slider-right-x6").get(0);

		// 处理左边轮播
		Elements hrefEls = sliderWrap.select("a");
		List<String> urList = new ArrayList<String>();
		for (Element hrefEl : hrefEls) {

			String url = hrefEl.attr("abs:href");
			urList.add(url);
		}

		handleSliderWrap(urList);

		// 处理右边6个小视频

	}

	/**
	 *  处理主页左边轮播链接得到的页面
	 * @param urList
	 */
	private void handleSliderWrap(List<String> urList) {

		for (String url : urList) {

			if (isVideoPageAnalized(url)) {

				continue;
			}
			videoPageAnalize(url);
		}
	}

	/**
	 * 判断当前视频页是否已经被分析过
	 * @param url
	 * @return
	 */
	private boolean isVideoPageAnalized(String url) {

		if (grabLibMapper.selectGrabLibs(url)) {

			return true;
		}
		return false;
	}

	/**
	 * 视频页分析器
	 * @param url
	 */
	private void videoPageAnalize(String url) {

		Connection connection = Jsoup.connect(url)
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2906.0 Safari/537.36")
				.ignoreContentType(true);
		Document document = null;
		try {
			document = connection.get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (document != null) {

			Element mainEl = document.getElementById("main");

			// 获取头部信息
			Element header = mainEl.select("section").first();
			String title = header.getElementsByClass("title").first().text();
			Element typeDatas = header.getElementsByClass("title").first().nextElementSibling()
					.getElementById("bd_crumb");
			String videoType = typeDatas.getElementsByClass("sp3").first().text() + "-"
					+ typeDatas.getElementsByClass("sp5").first().text();
			String releaseTime = typeDatas.getElementsByClass("sp7").first().previousElementSibling().text();

			// 获取视频下的各种例如评论数量等信息
			Element crumpDatas = mainEl.select("section.clearfix.wp.area.crumb").first();
			String viewCount = crumpDatas.select("span.view.f1").first().select("span.sp2").first().text();

			int danmu = Integer.valueOf(crumpDatas.select("span.danmu.f1").first().select("span.sp2").first().text());
			int commentCount = Integer.valueOf(crumpDatas.getElementById("bd_comm").select("span.sp2").first().text());
			int likeCount = Integer
					.valueOf(crumpDatas.getElementById("bd_collection").select("span.sp4").first().text());
			int bananaCount = Integer
					.valueOf(crumpDatas.select("span.banana.f1").first().select("span.sp4").first().text());

			Element userDiv = mainEl.select("div.introduction").first().select("section.clearfix.wp.area").first()
					.select("div.column-right.fr").first();
			String signature = userDiv.select("div.bottom").first().select("div.desc").first().text();
			String authorPageUrl = userDiv.select("div.user").first().select("a").first().attr("abs:href");

			int authorId;
			authorId = isVideoAuthorInserted(authorPageUrl);
			if (-1 == authorId) {

				String authorPic = userDiv.select("div.user").first().select("a").first().select("img").first()
						.attr("src");
				String authorName = userDiv.select("div.user").first().getElementById("bd_upname").select("div.title")
						.first().text();

				authorId = analizeVideoAuthorPage(signature, authorName, authorPageUrl, authorPic);
			}

			if (authorId != -1) {

				GrabMessage grabMessage = new GrabMessage();
				grabMessage.setVideoName(title);
				grabMessage.setPlayCount(viewCount);
				grabMessage.setLikeCount(likeCount);
				grabMessage.setCommentCount(commentCount);
				grabMessage.setBarrage(danmu);
				grabMessage.setBananaCount(bananaCount);
				grabMessage.setVideoAddTime(releaseTime);
				grabMessage.setAuthorId(authorId);
				grabMessage.setVideoType(videoType);

				grabMessageMapper.save(grabMessage);
			} else {

				System.out.println("插入视频信息 " + title + " 失败!");
			}
		}
	}

	/**
	 * 判断视频作者信息是否已经被插入
	 * @param authorPageUrl
	 * @return
	 */
	private int isVideoAuthorInserted(String authorPageUrl) {

		int authorId = videoAuthorMapper.selectVideoAuthors(authorPageUrl);
		if (authorId > 0) {

			return authorId;
		}
		return -1;
	}

	/**
	 * 分析视频作者页面信息
	 * @param signature
	 * @param authorName
	 * @param authorPageUrl
	 * @param authorPic
	 * @return
	 */
	private int analizeVideoAuthorPage(String signature, String authorName, String authorPageUrl, String authorPic) {

		Connection connection = Jsoup.connect(authorPageUrl).ignoreContentType(true).userAgent(
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2906.0 Safari/537.36");
		Document document = null;
		try {
			document = connection.get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (document != null) {

			Element mainEl = document.getElementById("main");
			Element informationEl = mainEl.select("section.headup.wp").first().getElementById("anchorMes")
					.select("div.information").first().select("div.mesL.fl").first().select("div.clearfix").first();
			int attentionCount = Integer.valueOf(informationEl.select("div.fl.follow").first().text());
			int audienceCount = Integer.valueOf(informationEl.select("div.fl.fans").first().text());
			int videoCount = Integer.valueOf(mainEl.select("div.contentup.wp").first().select("div.contentlist").first()
					.select("div.table.clearfix").first().select("a").first().select("span").first().text());

			VideoAuthor videoAuthor = new VideoAuthor();
			videoAuthor.setSignature(signature);
			videoAuthor.setVideoCount(videoCount);
			videoAuthor.setAttentionCount(attentionCount);
			videoAuthor.setAudienceCount(audienceCount);
			videoAuthor.setAuthorPageUrl(authorPageUrl);
			videoAuthor.setAuthorPic(authorPic);
			videoAuthor.setAuthorName(authorName);

			return videoAuthorMapper.insert(videoAuthor);
		}
		return -1;
	}

}
