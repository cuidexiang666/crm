package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.entity.LinkMan;
import cn.itcast.utils.PageBean;

public interface LinkManService {

	void save(LinkMan linkman);

	PageBean findByList(Integer currentPage, DetachedCriteria dc);

	LinkMan findOne(Integer lkmId);

	void update(LinkMan man);

	void delete(LinkMan linkmanExist);

}
