package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.BaseDict;


public interface BaseDictService {
	public List<BaseDict> findBaseDictByTypeCode(String typeCode);
}
