package com.wufan.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
		jsonObject.put("result",1);
		try {
			userService.update(user);
		}catch(Exception e) {
			jsonObject.put("result", 0);
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return jsonObject.toString();
	}
	
	@RequestMapping(value="/deleteUsers")
	@ResponseBody
	public String deleteUsers(@RequestParam(value="ids")String userList) {
		String[] strs = userList.split(",");
		List<Integer> ids = new ArrayList<>();
		for(int i=0;i<strs.length;i++) {
			ids.add(Integer.parseInt(strs[i]));
		}
		userService.deleteUsers(ids);
		return "1";
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete(Long id) {
		System.out.print(id);
		userService.delete(id);
		return "删除成功";
		
	}
	
	@ResponseBody
	@RequestMapping(value="/upload",method= {RequestMethod.GET,RequestMethod.POST})
	public HashMap<String, Object> ajaxUploadExcel(@RequestParam("file")MultipartFile file, HttpServletRequest request,HttpServletResponse response)throws Exception{
		HashMap<String, Object> map = new HashMap<String,Object>();
		try {
			userService.ajaxUploadExcel(file, request, response);
			map.put("msg", "ok");
			map.put("code", 200);
		}catch(Exception e) {
			map.put("msg", "error");
			map.put("code", 0);
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value ="/search")
	@ResponseBody 
	public Map<String,Object> queryAll(@RequestParam("username") String username,@RequestParam( "phone") String phone,@RequestParam("email") String email,@RequestParam("address") String address,@RequestParam("department") String department,Integer page,Integer limit){
		int count = userService.countAllBy(username, phone, email, address, department);
		List<User> list = userService.search(username, phone, email, address, department,page,limit);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("msg", "");
		map.put("code", 0);
		map.put("count", count);
		map.put("data", list);
		return map;
	}
	@RequestMapping(value = "/listData",produces = "text/html;charset=utf-8")
	@ResponseBody
		public String selectAll(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize) throws Exception {
		int count = userService.countAll();//行数
	    List<User> users = userService.selectAll(pageNum, pageSize);
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("code",0);
    	jsonObject.put("msg", "");
    	jsonObject.put("data",users);
    	jsonObject.put("count", count);
    	return jsonObject.toString();
    }

	/*@RequestMapping(value = "/PaginationUser",produces = "text/html;charset=utf-8")
	@ResponseBody
	public String PaginationUser(@RequestParam("page") Integer page) {
		int start = 1;
		int num = 10 ;//返回数据量
		List<User> users = userService.selectAll();
		if (page == 1) {
            users = userService.PaginationUser(0, num); //数据库从0开始
        } else {
            users = userService.PaginationUser(num,(page * num) - 1);
        }
		
        int count = userService.countAll();
        // 数据封装
        if (users.size() != 0) {
            return "{ \"code\":200,\"message\":\"成功\",\"data\":" + JSONObject.toJSONString(users) + ",\"count\" : " +count+ "}";
            //计算页数
        } else {
            return "{ \"code\":100,\"message\":\"失败\",\"data\": [],\"count\" : 0}";
        }

	}
	*/
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form() {
		return "/form"; 
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String showAll() {
		return "/list";
	}
	
	@RequestMapping("/edit")
    public ModelAndView edit(User model,HttpSession session){
		User user = userService.findUserByName(model.getUsername());
		session.setAttribute("user", user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("edit");
		return mav;
    }
	
	@RequestMapping("/details")
	public String details() {
		return "details";
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