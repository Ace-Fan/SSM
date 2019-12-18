package com.wufan.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.wufan.domain.User;

public interface FileService {

	/**
	 * �����û�excel
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	String ajaxUploadExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response);

	/**
	 * �����û�excel
	 * 
	 * @param user
	 * @return
	 */
	List<HashMap<String, Object>> ExcelOut(User user);

	/**
	 * ��ʾȫ���û�
	 * 
	 * @return
	 */
	List<User> showAll();

}
