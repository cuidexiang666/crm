package cn.itcast.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.dao.VisitDao;
import cn.itcast.entity.Visit;
import cn.itcast.service.VisitService;
import cn.itcast.utils.PageBean;

public class VisitServiceImpl implements VisitService {

	private VisitDao visitDao;

	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	@Override
	public void save(Visit visit) {
		
		visitDao.save(visit);
	}

	@Override
	public PageBean findByList(Integer currentPage, DetachedCriteria dc) {
		
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		
		Integer pageSize = 2;
		pageBean.setPageSize(pageSize);
		
		Integer totalCount = 0;
		totalCount = visitDao.findTotalCount(dc);
		pageBean.setTotalCount(totalCount);
		
		Integer totalPage = (int) Math.ceil(1.0*totalCount/pageSize);
		pageBean.setTotalPage(totalPage);
		
		List<Visit> list = visitDao.findByPage((currentPage-1)*pageSize,pageSize,dc);
		pageBean.setList(list);
		
		return pageBean;
		
	}
	
}
