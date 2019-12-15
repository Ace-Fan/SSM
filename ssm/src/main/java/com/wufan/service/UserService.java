package com.wufan.service;

import java.util.List;
import com.wufan.domain.User;

public interface UserService {
	
	/**
	 * �����û�����ѯ�û�
	 * @param username
	 * @return
	 */
	User findUserByName(String name);

	/**
	 * ��ѯ�����û�
	 * @return
	 */
	List<User> selectAll(Integer pageNum, Integer pageSize);

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
	 * ����id��ѯ�û�
	 * @param id
	 * @return
	 */
	User selectById(Long id);

	/**
	 * �����û�
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
	 * �����û�����
	 * @return
	 */
	int countAll();

	/**
	 * ���������û�����
	 * @return
	 */
	int countAllBy(String username, String phone, String email, String address, String department);

	/**
	 * �����û�
	 * @param user
	 */
	void insert(User user);



}