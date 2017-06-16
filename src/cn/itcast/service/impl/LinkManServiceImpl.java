package cn.itcast.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.dao.LinkManDao;
import cn.itcast.entity.LinkMan;
import cn.itcast.service.LinkManService;
import cn.itcast.utils.PageBean;

public class LinkManServiceImpl implements LinkManService {

	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public void save(LinkMan linkman) {

		linkManDao.save(linkman);
	}

	@Override
	public PageBean findByList(Integer currentPage, DetachedCriteria dc) {
		
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		
		Integer pageSize = 2;
		pageBean.setPageSize(pageSize);
		
		Integer totalCount = 0;
		totalCount = linkManDao.findTotalCount(dc);
		pageBean.setTotalCount(totalCount);
		
		Integer totalPage = (int) Math.ceil(1.0*totalCount/pageSize);
		pageBean.setTotalPage(totalPage);
		
		List<LinkMan> list = linkManDao.findByPage((currentPage-1)*pageSize,pageSize,dc);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public LinkMan findOne(Integer lkmId) {
		
		return linkManDao.findOne(lkmId);
	}

	@Override
	public void update(LinkMan man) {
		linkManDao.update(man);
		
	}

	@Override
	public void delete(LinkMan linkmanExist) {
		linkManDao.delete(linkmanExist);
	}
	
}
