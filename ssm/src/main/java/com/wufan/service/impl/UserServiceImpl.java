package com.wufan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.wufan.dao.UserDao;
import com.wufan.domain.User;
import com.wufan.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    @Override
    public User findUserByName(String name) {
        // TODO Auto-generated method stub
        return this.userDao.findByUsername(name);
    }
    
    public List<User> selectAll() {
        return userDao.selectAll();
    }

	@Override
	public List<User> PaginationUser(Integer start, Integer num) {
		// TODO Auto-generated method stub
		return userDao.PaginationUser(start, num);
	}

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
	public List<User> search(User user) {
		// TODO Auto-generated method stub
		return userDao.search(user);
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
	public void deleteUsers(List<String> ids) {
		// TODO Auto-generated method stub
		userDao.deleteUsers(ids);
	}


	/**@Override
	public boolean batchImport(String name, MultipartFile file) {
		// TODO Auto-generated method stub
		boolean b = false;
		//创建处理Excel
		ReadExcel readExcel = new ReadExcel();
		return false;
	}**/


}