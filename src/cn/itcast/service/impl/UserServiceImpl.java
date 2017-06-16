package cn.itcast.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.UserDao;
import cn.itcast.entity.User;
import cn.itcast.service.UserService;
import cn.itcast.utils.Md5Util;
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public void save(User user) {

		userDao.save(user);
	}


	@Override
	public Boolean findUserByUsercode(User user) {
		User user2 = userDao.findUserByUsercode(user);
		
		if (user2==null) {
			return false;
		}
		return true;
	}


	@Override
	public User findUser(User user) {
		String encode = Md5Util.encode(user.getPassword());
		user.setPassword(encode);
		User existUser = userDao.findUser(user);
		return existUser;
	}


	@Override
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

}
