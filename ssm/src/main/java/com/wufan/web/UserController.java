package com.wufan.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wufan.domain.User;
import com.wufan.service.UserService;

/**
 * 用户控制器
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(User model, HttpSession session) {
		User user = userService.findUserByName(model.getUsername());

		if (user == null || !user.getPassword().equals(model.getPassword())) {
			return new ModelAndView("redirect:/login.jsp");
		} else {
			session.setAttribute("user", user);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("index");
			return mav;
		}
	}

	@RequestMapping(value="/insert",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String insert(User user) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", 1);
		try {
			userService.insert(user);
		}catch(Exception e) {
			jsonObject.put("result", 0);
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return jsonObject.toString();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(User user) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result","1");
		try {
			userService.update(user);
		}catch(Exception e) {
			jsonObject.put("result", 0);
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return jsonObject.toString();
	}
	
	@RequestMapping("/deleteUsers")
	@ResponseBody
	public String deleteUsers(User user) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", 1);
		try {
			userService.deleteUsers(null);
		}catch(Exception e) {
			jsonObject.put("result", 0);
		}
		return null;
		
	}
	@RequestMapping("delete")
	@ResponseBody
	public String delete(Long id) {
		userService.delete(id);
		return "删除成功";
		
	}
	
	/*@RequestMapping(value = "batchimport",method = RequestMethod.POST)
	public String batchimport(@RequestParam(value = "filename") MultipartFile file ,HttpServletRequest request
			,HttpServletResponse response) throws IOException{
		
		//判断文件是否为空
		if(file == null) {
			return null;
		}
		//获取文件名
		String name = file.getOriginalFilename();
		
		//判断文件大小及名称
		long size = file.getSize();
		if(name == null || ("").equals(name) && size == 0) {
			return null;
		}
		//批量导入
		boolean b = customer
		return "Customer/addCustomer3";
		
	}*/
	
	@RequestMapping(value="/search",produces = "text/html;charset=utf-8")
	@ResponseBody
	public String search(User user) {
		List<User> users = userService.search(user);
		return "{ \"code\":200,\"message\":\"成功\",\"data\":" + JSONObject.toJSONString(users) + "}";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String showAll() {
		return "/list";
	}
	
	@RequestMapping("/edit")
    public String edit(){
        return "edit";
    }
	
	@RequestMapping("/details")
	public String details() {
		return "details";
	}
	
	@RequestMapping(value = "/listData",produces = "text/html;charset=utf-8")
	@ResponseBody
	public String listData() {
        List<User> users = userService.selectAll();
    	int count = userService.countAll();//行数
    	/*Map<String,Object> map = new HashMap<String,Object>();
    	map.put("code", 200);
    	map.put("message", "成功");
    	map.put("count", count);
    	map.put("data",users);
    	*/
        return  "{ \"code\":200,\"message\":\"成功\",\"count\":"+count+",\"data\":" + JSONObject.toJSONString(users) +"}";
    }

	@RequestMapping(value = "/PaginationUser",produces = "text/html;charset=utf-8")
	@ResponseBody
	public String PaginationUser(@RequestParam(name = "page") Integer page) {
		int start = 1; //默认第一条数据为1
		int num = 6 ;//返回数据量
		List<User> users;
		if(page == 1) {
			users = userService.PaginationUser(0, num);//从0开始
		}else {
			users = userService.PaginationUser((page * num) - 1,num);
		}
		int count = userService.countAll();//行数
		//数据封装
		if(users.size() != 0) {
			return "{ \"code\":200,\"message\":\"成功\",\"data\":" + JSONObject.toJSONString(users) + ",\"count\" : "+count+"}";
		}else {
			return "{ \"code\":100,\"message\":\"失败\",\"data\": [],\"count\" : 0}";
		}
		
	}
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form() {
		return "/form";
	}

	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public String loginout(HttpServletRequest httpServletRequest) {
		httpServletRequest.getSession().invalidate();
		return login();
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/index";
	}

	@RequestMapping(value = { "", "login" }, method = RequestMethod.GET)
	public String login() {
		// TODO Auto-generated method stub
		return "/login";
	}
}