package cn.qianying.graduation.service;

import java.io.IOException;
import java.util.List;

import cn.qianying.graduation.domain.GrabingWeb;

public interface GrabingService{

	void grabWebInBF(List<GrabingWeb> grabingWebs) throws IOException;


}
