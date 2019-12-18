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

		// �ж��ļ��Ƿ����
		if (file.isEmpty()) {
			try {
				throw new Exception("�ļ������ڣ�");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ����һ������������
		InputStream in = null;

		// ��ȡ�����ļ���
		try {
			in = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ����һ�����ղ�����list����
		List<List<Object>> listob = null;

		// ͨ��excel������,ȡ��excel�ļ��е��û��б�
		try {
			listob = new ExcelUtils().getUserListByExcel(in, file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// �����û��б����ݣ�ʵ�����û�
		for (int i = 3; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			User vo = new User();
			User j = null;

			// ����ID�ж��û��Ƿ����
			try {
				j = userDao.selectById(Long.valueOf(String.valueOf(lo.get(0))));
			} catch (NumberFormatException e) {
				System.out.println("���ݿ��������ݣ�����");
			}

			// ���û����ݱ���ת��Ϊ�ַ�������
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

			// ������������û�
			if (j == null) {
				userDao.insert(vo);
				System.out.println("success");
			}
			// �����ڸ����û�
			else {
				userDao.update(vo);
			}
		}
		return "�ļ�����ɹ�";

	}
}
