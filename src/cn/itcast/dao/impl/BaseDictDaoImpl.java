package cn.itcast.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.dao.BaseDictDao;
import cn.itcast.entity.BaseDict;


public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {

	@Override
	public List<BaseDict> findBaseDictByTypeCode(String typeCode) {
		return (List<BaseDict>) this.getHibernateTemplate().find(" from BaseDict where dictTypeCode=?", typeCode);
	}

}
