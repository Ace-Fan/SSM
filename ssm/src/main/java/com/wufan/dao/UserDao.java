package com.wufan.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.wufan.domain.User;

public interface UserDao {
	
	/**
	 * �����û�����ѯ�û�
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	
	/**
	 * ����id��ѯ�û�
	 * @param id
	 * @return
	 */
	User selectById(Long id);

	/**
	 * �����û�
	 * @param user
	 */
	void insert(User user);

	/**
	 * ɾ���û�
	 * @param id
	 */
	void delete(Long id);

	/**
	 * ����ɾ���û�
	 * @param ids
	 */
	void deleteUsers(List<Integer> ids);

	/**
	 * �����û���Ϣ
	 * @param user
	 */
	void update(User user);

	/**
	 * �����û�
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
	 * ��ѯ�����û�
	 * @return
	 */
	List<User> selectAll();

	/**
	 * �����û�Ϊexcel
	 * @param user
	 * @return
	 */
	List<HashMap<String, Object>> ExcelOut(User user);

	// List<User> PaginationUser(@Param("start")Integer start,@Param("num")Integer
	// num);
	/**
	 * �����û�����
	 * @return
	 */
	int countAll();

	/**
	 * �����������û�����
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