package com.wufan.service;

import java.util.List;


import com.wufan.domain.User;

public interface UserService {
    User findUserByName(String name); 
    List<User> selectAll();
    void delete(Long id);
    void deleteUsers(List<String> ids);
    void update(User user);
    List<User> search(User user);
    List<User> PaginationUser(Integer start,Integer num);
	int countAll();
	void insert(User user);
}