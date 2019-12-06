package com.wufan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.wufan.domain.User;

public interface UserDao {
    User findByUsername(String username);
    void insert(User user);
    void delete(Long id);
    void deleteUsers(List<String> ids);
    void update(User user);
    List<User> search(User user);
    List<User> selectAll();
    @Select("select * from tb_user_info limit #{param1},#{param2}")
    List<User> PaginationUser(Integer start,Integer num);
    int countAll();
}