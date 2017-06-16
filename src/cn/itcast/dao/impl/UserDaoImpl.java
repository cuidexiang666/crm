package cn.itcast.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.dao.UserDao;
import cn.itcast.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public void save(User user) {

		this.getHibernateTemplate().save(user);
	}

	@Override
	public User findUserByUsercode(User user) {
		
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where usercode =?", user.getUsercode());
		
		if (list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public User findUser(User user) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where usercode=? and password = ?", user.getUsercode(),user.getPassword());
		if (list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<User> findAllUsers() {
		return (List<User>) this.getHibernateTemplate().find("from User");
	}

}
