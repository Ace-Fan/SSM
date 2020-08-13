package com.wufan.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.wufan.domain.User;
import com.wufan.service.UserService;

/**
 * �û�������
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * �����û���Ϣ
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "insert", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String insert(User user) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", 1);
		try {
			userService.insert(user);
		} catch (Exception e) {
			jsonObject.put("result", 0);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * �����û���Ϣ
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public String update(User user) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", 1);
		try {
			userService.update(user);
		} catch (Exception e) {
			jsonObject.put("result", 0);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * ����ɾ���û���Ϣ
	 * 
	 * @param userList
	 * @return
	 */
	@RequestMapping(value = "deleteUsers")
	@ResponseBody
	public String deleteUsers(@RequestParam(value = "ids") String userList) {
		String[] strs = userList.split(",");
		List<Integer> ids = new ArrayList<>();
		for (int i = 0; i < strs.length; i++) {
			ids.add(Integer.parseInt(strs[i]));
		}
		userService.deleteUsers(ids);
		return "1";
	}

	/**
	 * ɾ�������û�
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String delete(Long id) {
		System.out.print(id);
		userService.delete(id);
		return "ɾ���ɹ�";

	}

	/**
	 * �����û���Ϣ
	 * 
	 * @param username
	 * @param phone
	 * @param email
	 * @param address
	 * @param department
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "search")
	@ResponseBody
	public Map<String, Object> queryAll(@RequestParam("username") String username, @RequestParam("phone") String phone,
			@RequestParam("email") String email, @RequestParam("address") String address,
			@RequestParam("department") String department, Integer page, Integer limit) {
		int count = userService.countAllBy(username, phone, email, address, department);
		List<User> list = userService.search(username, phone, email, address, department, page, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "");
		map.put("code", 0);
		map.put("count", count);
		map.put("data", list);
		return map;
	}

	/**
	 * ��ѯ�������û���Ϣ
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "listData", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String selectAll(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize)
			throws Exception {
		int count = userService.countAll();
		List<User> users = userService.selectAll(pageNum, pageSize);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "");
		jsonObject.put("data", users);
		jsonObject.put("count", count);
		return jsonObject.toString();
	}

	/**
	 * ��ת�û���ҳ��
	 * 
	 * @return
	 */
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String form() {
		return "form";
	}

	/**
	 * ��ת�û��б�ҳ��
	 * 
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String showAll() {
		return "list";
	}

	/**
	 * ��ת�༭�û�ҳ��
	 * 
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit() {
		return "edit";
	}

	/**
	 * ��ת�鿴�û�ҳ��
	 * 
	 * @return
	 */
	@RequestMapping(value = "details", method = RequestMethod.GET)
	public String details() {
		return "details";
	}

}