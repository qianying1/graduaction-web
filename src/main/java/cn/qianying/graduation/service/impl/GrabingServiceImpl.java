package cn.qianying.graduation.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qianying.graduation.domain.GrabingWeb;
import cn.qianying.graduation.service.GrabingService;

@Service("grabingServiceImpl")
public class GrabingServiceImpl implements GrabingService {

	private String htmlFileSaveBase = "grabedWebPages/";
	@Autowired
//	private GrabLibDao grabLibDaoImpl;

	@Override
	public void grabWebInBF(List<GrabingWeb> grabingWebs) throws IOException {

//		List<GrabingWeb> grabingWebs2 = new ArrayList<>();
//		String name = null;
//		for (GrabingWeb grabingWeb : grabingWebs) {
//
//			String webName = grabingWeb.getWebName();
//			String webUrl = grabingWeb.getUrl();
//			Document doc = null;
//			try {
//				doc = Jsoup.connect(webUrl).ignoreContentType(true)
//						.userAgent(
//								"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
//						.timeout(600000).get();
//			} catch (IOException e) {
//
//				// 等待链接超时处理
//				System.out.println("===========请求超时处理========" + webName + " " + webUrl + e.getMessage());
//				// return;
//				continue;
//			}
//
//			String docTxt = doc.html();
//			Element titleEl = doc.select("title").first();
//			String title = null;
//			if (titleEl != null)
//				title = titleEl.text();
//			else
//				title = "";
//
//			if (null != webUrl && !"".equals(webUrl) && !isGrabed(webUrl)) {
//
//				String pageTitle = writeHtmlDoc(title, webName, docTxt);
//				int contentId = pageContentDaoImpl.insert(pageTitle);
//				name = webName;
//				grabLibDaoImpl.insert(contentId, webName, webUrl, "Y");
//			}
//
//			// 抓取页面的urls
//			Elements ahrefEls = doc.select("a");
//
//			for (Element ahrefEl : ahrefEls) {
//
//				String url = ahrefEl.attr("abs:href");
//				// 判断是否为重复的url
//				if (null == url || "".equals(url) || isGrabed(url)) {
//
//					continue;
//				}
//				GrabingWeb grabingWeb2 = new GrabingWeb(webName, url);
//				grabingWebs2.add(grabingWeb2);
//			}
//			System.out.println("======web==" + webName + "====number===" + ahrefEls.size() + "==");
//
//		}
//		System.out.println("====================next==========" + name);
//		grabWebInBF(grabingWebs2);
	}

	// private boolean isGrabed(String url) {
	//
	// if (grabLibDaoImpl.isGrabed(url)) {
	// return true;
	// }
	// return false;
	// }

	private String writeHtmlDoc(String pageTitle, String webName, String htmlTxt) throws IOException {

		Calendar now = Calendar.getInstance();
		String writeTime = String.valueOf(now.get(Calendar.YEAR)) + String.valueOf((now.get(Calendar.MONTH) + 1))
				+ String.valueOf(now.get(Calendar.DAY_OF_MONTH));

		File htmlFile = new File(
				htmlFileSaveBase + webName + File.separator + writeTime + File.separator + pageTitle + ".html");
		if (!htmlFile.getParentFile().exists()) {
			htmlFile.getParentFile().mkdirs();
		}

		String exts[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
				"t", "u", "v", "w", "x", "y", "z" };
		String ext = exts[(int) (Math.random() * 26)];
		while (htmlFile.exists()) {

			pageTitle = pageTitle + ext;
			htmlFile = new File(
					htmlFileSaveBase + webName + File.separator + writeTime + File.separator + pageTitle + ".html");
		}
		htmlFile.createNewFile();

		FileOutputStream fileOutputStream = new FileOutputStream(htmlFile);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		PrintWriter printWriter = new PrintWriter(bufferedOutputStream);

		printWriter.write(htmlTxt);
		printWriter.flush();

		printWriter.close();
		bufferedOutputStream.close();
		fileOutputStream.close();

		return htmlFile.getAbsolutePath();
	}

}
