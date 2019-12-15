package com.wufan.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.wufan.domain.User;

public interface UserDao {
	
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	User selectById(Long id);

	/**
	 * 新增用户
	 * @param user
	 */
	void insert(User user);

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
	 * 搜索用户
	 * @param username
	 * @param phone
	 * @param email
	 * @param address
	 * @param department
	 * @return
	 */
	List<User> search(@Param("username") String username, @Param("phone") String phone, @Param("email") String email,
			@Param("address") String address, @Param("department") String department);

	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> selectAll();

	/**
	 * 导出用户为excel
	 * @param user
	 * @return
	 */
	List<HashMap<String, Object>> ExcelOut(User user);

	// List<User> PaginationUser(@Param("start")Integer start,@Param("num")Integer
	// num);
	/**
	 * 计算用户总数
	 * @return
	 */
	int countAll();

	/**
	 * 计算搜索的用户总数
	 * @param username
	 * @param phone
	 * @param email
	 * @param address
	 * @param department
	 * @return
	 */
	int countAllBy(@Param("username") String username, @Param("phone") String phone, @Param("email") String email,
			@Param("address") String address, @Param("department") String department);
}