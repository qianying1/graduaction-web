package cn.qianying.graduation.dao.mapper;

import org.springframework.stereotype.Repository;

import cn.qianying.graduation.domain.AnalizedMessage;
import cn.qianying.graduation.domain.GrabMessage;

@Repository("grabMessageMapper")
public interface GrabMessageMapper extends CommonMapper<GrabMessage> {

	public int addRecord(AnalizedMessage analizedMessage);

	public int saveOrUpdate(AnalizedMessage analizedMessage);

	public AnalizedMessage getDetail(String id);

}
