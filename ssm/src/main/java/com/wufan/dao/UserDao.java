package com.wufan.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.wufan.domain.User;

public interface UserDao {
    User findByUsername(String username);
    User selectById(Long id);
    void insert(User user);
    void delete(Long id);
    void deleteUsers(List<Integer> ids);
    void update(User user);
    List<User> search(@Param("username") String username,@Param("phone") String phone,@Param("email") String email,@Param("address") String address,@Param("department") String department);
    List<User> selectAll();
    List<HashMap<String,Object>> ExcelOut(User user);
    //List<User> PaginationUser(@Param("start")Integer start,@Param("num")Integer num);
    int countAll();
    int countAllBy(@Param("username") String username,@Param("phone") String phone,@Param("email") String email,@Param("address") String address,@Param("department") String department);
}