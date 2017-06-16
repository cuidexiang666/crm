package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.BaseDict;


public interface BaseDictDao {

	public List<BaseDict> findBaseDictByTypeCode(String typeCode);
}
