package cn.qianying.graduation.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.qianying.graduation.domain.GrabingWeb;
import cn.qianying.graduation.service.AcfunGrabService;

@Controller
@RequestMapping("/acfun")
public class AcfunController {

	@Autowired
	private AcfunGrabService acfunGrabServiceImpl;

	private String webSiteResource = "/websites.properties";
	private String webName = "acfun";

	@RequestMapping(value = "/acfunGrabing", method = RequestMethod.POST)
	public void beginGrabing(HttpServletResponse response) throws IOException {

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				beginGrabingWeb();
			}
		};
		thread.run();

		response.setContentType("text/html");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		out = response.getWriter();

		out.print("success");
		out.flush();
		out.close();
		
	}

	private void beginGrabingWeb() {

		Properties properties = new Properties();
		InputStream in = this.getClass().getResourceAsStream(webSiteResource);

		try {
			properties.load(in);
		} catch (IOException e) {

			e.printStackTrace();
		}
		String webUrl = properties.getProperty(webName);
		GrabingWeb grabingWeb = new GrabingWeb(webName, webUrl);

		acfunGrabServiceImpl.grabAcfunWebInBF(grabingWeb);
	}
}
