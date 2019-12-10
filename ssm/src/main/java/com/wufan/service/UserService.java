package com.wufan.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.wufan.domain.User;

public interface UserService {
    User findUserByName(String name); 
    List<User> selectAll(Integer pageNum,Integer pageSize);
    String ajaxUploadExcel(MultipartFile file,HttpServletRequest request,HttpServletResponse response);
    void delete(Long id);
    void deleteUsers(List<Integer> ids);
    void update(User user);
    User selectById(Long id);
    List<User> search(String username,String phone,String email,String address,String department,Integer page, Integer limit);
    //List<User> PaginationUser(@Param("start")Integer start,@Param("num")Integer num);
	int countAll();
	int countAllBy(String username,String phone,String email,String address,String department);
	void insert(User user);
	List<HashMap<String,Object>> ExcelOut(User user);
}