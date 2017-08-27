package cn.qianying.graduation.dao.mapper;

import org.springframework.stereotype.Repository;

import cn.qianying.graduation.domain.VideoAuthor;
@Repository("videoAuthorMapper")
public interface VideoAuthorMapper extends CommonMapper<VideoAuthor> {

	int insert(VideoAuthor videoAuthor);

	int selectVideoAuthors(String authorPageUrl);

}
