package com.wufan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.wufan.dao.UserDao;
import com.wufan.domain.User;
import com.wufan.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public User findUserByName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

	@Override
	public List<User> selectAll(Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		//获得分页数目和总条数
		PageHelper.startPage(pageNum, pageSize);
		List<User> users = userDao.selectAll();
		return users;
	}

	/*
	 * @Override public List<User> PaginationUser(Integer start, Integer num) { //
	 * TODO Auto-generated method stub return userDao.PaginationUser(start, num); }
	 */

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return userDao.countAll();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}

	@Override
	public List<User> search(String username, String phone, String email, String address, String department,
			Integer page, Integer limit) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, limit);
		List<User> search = userDao.search(username, phone, email, address, department);
		return search;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public void deleteUsers(List<Integer> ids) {
		userDao.deleteUsers(ids);
	}

	@Override
	public User selectById(Long id) {
		// TODO Auto-generated method stub
		return userDao.selectById(id);
	}

	@Override
	public int countAllBy(String username, String phone, String email, String address, String department) {
		// TODO Auto-generated method stub
		return userDao.countAllBy(username, phone, email, address, department);
	}

}