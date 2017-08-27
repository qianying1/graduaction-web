package cn.qianying.graduation.dao.mapper;

import org.springframework.stereotype.Repository;

import cn.qianying.graduation.domain.WebSites;

@Repository("websitesMapper")
public interface WebsitesMapper extends CommonMapper<WebSites> {

	boolean acfunIsInserted(String webUrl);

}
