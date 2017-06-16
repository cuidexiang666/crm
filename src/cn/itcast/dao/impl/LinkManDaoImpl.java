package cn.itcast.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.dao.LinkManDao;
import cn.itcast.entity.LinkMan;

public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {
/*
	@Override
	public void save(LinkMan linkman) {
		this.getHibernateTemplate().save(linkman);
	}

	@Override
	public Integer findTotalCount(DetachedCriteria dc) {
		
		//设置投影
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(dc);
		return list.get(0).intValue();
	}

	@Override
	public List<LinkMan> findByPage(int i, Integer pageSize, DetachedCriteria dc) {
		
		//取消投影
		dc.setProjection(null);
		//把customer对象放到LinkMan对象中
		dc.setResultTransformer(dc.ROOT_ENTITY);
		List<LinkMan> list = (List<LinkMan>) this.getHibernateTemplate().findByCriteria(dc, i, pageSize);
		
		return list;
	}

	@Override
	public LinkMan findOne(Integer lkmId) {
		return this.getHibernateTemplate().get(LinkMan.class, lkmId);
	}

	@Override
	public void update(LinkMan man) {

		this.getHibernateTemplate().update(man);
	}

	@Override
	public void delete(LinkMan linkmanExist) {
		this.getHibernateTemplate().delete(linkmanExist);
	}

	@Override
	public List<LinkMan> findAll() {
		return null;
	}*/

}
