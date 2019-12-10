package com.wufan.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.wufan.dao.UserDao;
import com.wufan.domain.User;
import com.wufan.service.UserService;
import com.wufan.utils.ExcelUtils;

@Service
@Transactional
public  class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    
    @Override
    public User findUserByName(String name) {
        // TODO Auto-generated method stub
        return userDao.findByUsername(name);
    }
    
    @Override
    public List<User> selectAll(Integer pageNum,Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<User> users = userDao.selectAll();
		return users;
    }

	/*@Override
	public List<User> PaginationUser(Integer start, Integer num) {
		// TODO Auto-generated method stub
		return userDao.PaginationUser(start, num);
	}*/

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
	public List<User> search(String username,String phone,String email,String address,String department, Integer page,Integer limit) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,limit);
		List <User> search = userDao.search(username, phone, email, address, department);
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
	public String ajaxUploadExcel(MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		if(file.isEmpty()) {
			try {
				throw new Exception("文件不存在！");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		InputStream in = null;
		try {
			in = file.getInputStream();
		}catch (IOException e) {
			e.printStackTrace();
		}
		List<List<Object>> listob = null;
		try {
			listob = new ExcelUtils().getUserListByExcel(in,file.getOriginalFilename());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//该处调用方法进行数据保存到数据库中
		for(int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			User vo = new User();
			User j = null;
			try {
				j = userDao.selectById(Long.valueOf(String.valueOf(lo.get(0))));
			}catch(NumberFormatException e) {
				System.out.println("数据库中无数据，新增");
			}
			
			vo.setId(Long.valueOf(String.valueOf(lo.get(0))));
			vo.setUsername(String.valueOf(lo.get(1)));
			vo.setPassword(String.valueOf(lo.get(2)));
			vo.setPhone(String.valueOf(lo.get(3)));
			vo.setEmail(String.valueOf(lo.get(4)));
			vo.setIdcard(String.valueOf(lo.get(5)));
			vo.setAddress(String.valueOf(lo.get(6)));
			vo.setSex(String.valueOf(lo.get(7)));
			vo.setDepartment(String.valueOf(lo.get(8)));
			vo.setEducation(String.valueOf(lo.get(9)));
			vo.setFork(String.valueOf(lo.get(10)));
			
			if(j == null) {
				userDao.insert(vo);
				System.out.println("success");
			}
			else {
				userDao.update(vo);
			}
		}
		return "文件导入成功";
		
	}

	@Override
	public User selectById(Long id) {
		// TODO Auto-generated method stub
		return userDao.selectById(id);
	}

	@Override
	public List<HashMap<String, Object>> ExcelOut(User user) {
		// TODO Auto-generated method stub
		return userDao.ExcelOut(user);
	}

	@Override
	public int countAllBy(String username, String phone, String email, String address, String department) {
		// TODO Auto-generated method stub
		return userDao.countAllBy(username, phone, email, address, department);
	}


}