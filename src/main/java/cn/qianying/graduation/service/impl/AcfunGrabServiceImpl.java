package cn.qianying.graduation.service.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qianying.graduation.dao.mapper.WebsitesMapper;
import cn.qianying.graduation.domain.GrabingWeb;
import cn.qianying.graduation.domain.WebSites;
import cn.qianying.graduation.service.AcfunGrabService;
import cn.qianying.graduation.support.AcfunSupport;

@Service("acfunGrabServiceImpl")
public class AcfunGrabServiceImpl extends CommonServiceImpl implements AcfunGrabService {

	@Autowired
	private WebsitesMapper websitesMapper;
	@Resource
	private AcfunSupport acfunSupportImpl;

	@Override
	public void grabAcfunWebInBF(GrabingWeb grabingWeb) {

		String webUrl = grabingWeb.getUrl();
		Document doc = null;
		try {
			doc = Jsoup.connect(webUrl).ignoreContentType(true)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0").post();
		} catch (IOException e) {

			e.printStackTrace();
		}

		grabMainPageAndAnalize(grabingWeb, doc);
	}

	private void grabMainPageAndAnalize(GrabingWeb grabingWeb, Document doc) {

		if (doc != null) {

			String webUrl = grabingWeb.getUrl();
			String webName = grabingWeb.getWebName();

			Element header = doc.getElementById("header");
			Element bodyMain = doc.getElementById("main");
			WebSites webSites = new WebSites();
			webSites.setWebsiteName(webName);
			webSites.setWebsiteUrl(webUrl);
			if (!websitesMapper.acfunIsInserted(webUrl)) {

				websitesMapper.save(webSites);
			}

			List<String> urls = acfunSupportImpl.analizeHeader(header);
			acfunSupportImpl.analizeBodyMain(bodyMain);
			for (String url : urls) {
				System.out.println("===" + url + "====");
			}
		}
	}

}
