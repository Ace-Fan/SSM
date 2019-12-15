package com.wufan.service;

import java.util.List;
import com.wufan.domain.User;

public interface UserService {
	
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	User findUserByName(String name);

	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> selectAll(Integer pageNum, Integer pageSize);

	/**
	 * 删除用户
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 批量删除用户
	 * @param ids
	 */
	void deleteUsers(List<Integer> ids);

	/**
	 * 更新用户信息
	 * @param user
	 */
	void update(User user);

	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	User selectById(Long id);

	/**
	 * 搜索用户
	 * @param username
	 * @param phone
	 * @param email
	 * @param address
	 * @param department
	 * @return
	 */
	List<User> search(String username, String phone, String email, String address, String department, Integer page,
			Integer limit);

	// List<User> PaginationUser(@Param("start")Integer start,@Param("num")Integer
	// num);
	/**
	 * 计算用户总数
	 * @return
	 */
	int countAll();

	/**
	 * 计算搜索用户总数
	 * @return
	 */
	int countAllBy(String username, String phone, String email, String address, String department);

	/**
	 * 新增用户
	 * @param user
	 */
	void insert(User user);



}