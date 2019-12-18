package com.wufan.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wufan.commons.utils.ExcelUtils;
import com.wufan.dao.UserDao;
import com.wufan.domain.User;
import com.wufan.service.FileService;

@Service
@Transactional
public class FileServiceImpl implements FileService {

	@Autowired
	private UserDao userDao;

	public List<User> showAll() {
		// TODO Auto-generated method stub
		return userDao.selectAll();
	}

	public List<HashMap<String, Object>> ExcelOut(User user) {
		// TODO Auto-generated method stub
		return userDao.ExcelOut(user);
	}

	public String ajaxUploadExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		// 判断文件是否存在
		if (file.isEmpty()) {
			try {
				throw new Exception("文件不存在！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 声明一个输入流变量
		InputStream in = null;

		// 获取导入文件流
		try {
			in = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 声明一个接收参数的list变量
		List<List<Object>> listob = null;

		// 通过excel工具类,取出excel文件中的用户列表
		try {
			listob = new ExcelUtils().getUserListByExcel(in, file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 遍历用户列表数据，实例化用户
		for (int i = 3; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			User vo = new User();
			User j = null;

			// 根据ID判断用户是否存在
			try {
				j = userDao.selectById(Long.valueOf(String.valueOf(lo.get(0))));
			} catch (NumberFormatException e) {
				System.out.println("数据库中无数据，新增");
			}

			// 将用户数据变量转换为字符串类型
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

			// 如果存在新增用户
			if (j == null) {
				userDao.insert(vo);
				System.out.println("success");
			}
			// 不存在更新用户
			else {
				userDao.update(vo);
			}
		}
		return "文件导入成功";

	}
}
