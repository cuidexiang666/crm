package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.User;

public interface UserDao  {

	void save(User user);

	User findUserByUsercode(User user);

	User findUser(User user);

	List<User> findAllUsers();

}
