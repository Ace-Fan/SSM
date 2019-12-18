package com.wufan.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.wufan.domain.User;

public interface FileService {

	/**
	 * 导入用户excel
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	String ajaxUploadExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 导出用户excel
	 * 
	 * @param user
	 * @return
	 */
	List<HashMap<String, Object>> ExcelOut(User user);

	/**
	 * 显示全部用户
	 * 
	 * @return
	 */
	List<User> showAll();

}
