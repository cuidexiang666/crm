package cn.itcast.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.entity.Visit;
import cn.itcast.utils.PageBean;

public interface VisitService {

	void save(Visit visit);

	PageBean findByList(Integer currentPage, DetachedCriteria dc);

}
