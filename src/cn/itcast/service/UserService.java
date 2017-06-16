package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.User;

public interface UserService {

	public void save(User user);

	Boolean findUserByUsercode(User user);

	User findUser(User user);

	public List<User> findAllUsers();

}
