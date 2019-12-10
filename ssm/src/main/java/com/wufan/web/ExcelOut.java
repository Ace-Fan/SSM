package com.wufan.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wufan.domain.User;
import com.wufan.service.UserService;

@Controller
@RequestMapping(value = "excel")
public class ExcelOut {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/ExcelOut",method = RequestMethod.GET)
	@ResponseBody
	public void ExcelOut(User user,HttpServletRequest request, HttpServletResponse response) throws IOException {
		String s="用户信息列表";
		// 创建一个文件名
		String path =  s+ ".xlsx";
		System.out.println(path);
		//从数据库中查出数据
		List<HashMap<String, Object>> lists = userService.ExcelOut(user);
		String[] title = { "ID", "用户名", "密码","手机号", "邮箱号","身份证号","籍贯","性别","部门","学历","民族"};//设置EXCEL的第一行的标题头（改）
		// 创建excel工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 创建一个工作表sheet
		XSSFSheet sheet = workbook.createSheet();
		// 创建第一行
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = null;

		// 插入第一行数据 id 
		for (int i = 0; i < title.length; i++) {
			//创建一行的一格
			cell = row.createCell(i);
			//赋值
			cell.setCellValue(title[i]);
		}
		// 追加数据行数
		int j = 1;
		HashMap<String, Object> list = null;
		for (int i = 0; i < lists.size(); i++) {
			//从集合中得到一个对象
			list = lists.get(i);
			//创建第2行
			XSSFRow nextrow = sheet.createRow(i+1);
			// 创建第1列并赋值
			XSSFCell cessk = nextrow.createCell(0);
			cessk.setCellValue((Long) list.get("id"));

			cessk = nextrow.createCell(1);
			cessk.setCellValue((String) list.get("username"));

			cessk = nextrow.createCell(2);
			cessk.setCellValue((String) list.get("password"));

			cessk = nextrow.createCell(3);
			cessk.setCellValue((String) list.get("phone"));

			cessk = nextrow.createCell(4);
			cessk.setCellValue((String) list.get("email"));
			cessk = nextrow.createCell(5);
			cessk.setCellValue((String) list.get("idcard"));
			cessk = nextrow.createCell(6);
			cessk.setCellValue((String) list.get("address"));
			cessk = nextrow.createCell(7);
			cessk.setCellValue((String) list.get("sex"));
			cessk = nextrow.createCell(8);
			cessk.setCellValue((String) list.get("department"));
			cessk = nextrow.createCell(9);
			cessk.setCellValue((String) list.get("education"));
			cessk = nextrow.createCell(10);
			cessk.setCellValue((String) list.get("fork"));
			j++;
		}
		if (path.equals("")) {
			response.getWriter().write("失败：参数为空！");
			return;
		}	
		response.setContentType("application/vnd.ms-excel; charset=utf-8");//自定义路径
		response.setHeader("Content-disposition", "attachment;filename="+new String((path).getBytes(), "iso-8859-1"));
		OutputStream ouputStream=null;
		try {
		ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
		} catch (IOException e) {
		}		
	}
	}

