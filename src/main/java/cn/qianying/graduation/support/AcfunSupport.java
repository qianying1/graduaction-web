package cn.qianying.graduation.support;

import java.util.List;

import org.jsoup.nodes.Element;

public interface AcfunSupport extends CommonSupport {

	public List<String> analizeHeader(Element header);

	public void analizeBodyMain(Element bodyMain);

	public void analyWebByBF();
}
