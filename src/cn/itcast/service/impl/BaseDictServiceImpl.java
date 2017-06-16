package cn.itcast.service.impl;

import java.util.List;

import cn.itcast.dao.BaseDictDao;
import cn.itcast.entity.BaseDict;
import cn.itcast.service.BaseDictService;


public class BaseDictServiceImpl implements BaseDictService {

	private BaseDictDao  baseDictDao;
	 
	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}
	
	public List<BaseDict> findBaseDictByTypeCode(String typeCode) {
		return baseDictDao.findBaseDictByTypeCode(typeCode);
	}

}
