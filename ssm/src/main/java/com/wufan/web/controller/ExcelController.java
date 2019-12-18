package com.wufan.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wufan.domain.User;
import com.wufan.service.FileService;

@Controller
@RequestMapping(value = "excel")
public class ExcelController {
	@Autowired
	private FileService fileService;

	/**
	 * 导入excel用户信息
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/upload", method = { RequestMethod.GET, RequestMethod.POST })
	public HashMap<String, Object> ajaxUploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			fileService.ajaxUploadExcel(file, request, response);
			map.put("msg", "ok");
			map.put("code", 200);
		} catch (Exception e) {
			map.put("msg", "error");
			map.put("code", 0);
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 用户信息导出excel
	 * 
	 * @param headNames
	 * @param colWidths
	 * @param user
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/ExcelOut", method = RequestMethod.GET)
	@ResponseBody
	public void ExcelOut(String[] headNames, int colWidths[], User user, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// 创建时间函数
		Date date = new Date();
		DateFormat nomaldate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = nomaldate.format(date);
		String s = "用户信息列表";

		// 创建一个文件名
		String path = s + "-" + dateStr + ".xlsx";

		// 从数据库中查出数据
		List<HashMap<String, Object>> lists = fileService.ExcelOut(user);
		String[] title = { "ID", "用户名","密码","手机号", "邮箱号", "身份证号", "籍贯", "性别", "部门", "学历", "民族" };// 设置EXCEL的第一行的标题头（改）

		// 创建excel工作薄
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();

		// 创建一个工作表sheet
		XSSFSheet sheet = workbook.createSheet("信息表1");
		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3500);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5500);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 1300);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 2000);
		sheet.setColumnWidth(10, 2000);

		// 创建表头
		XSSFRow firstRow = sheet.createRow(0);
		XSSFCell firstCell = firstRow.createCell(0);
		XSSFCellStyle cellstyle = workbook.createCellStyle();
		firstCell.setCellValue("用户信息表");
		XSSFFont font = workbook.createFont();
		cellstyle.setFont(font);
		font.setFontHeightInPoints((short) 16);// 设置字号
		cellstyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
		cellstyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直居中
		CellRangeAddress region = new CellRangeAddress(0, 2, 0, 10);
		RegionUtil.setBorderBottom(1, region, sheet);
		RegionUtil.setBorderLeft(1, region, sheet);
		RegionUtil.setBorderTop(1, region, sheet);
		RegionUtil.setBorderRight(1, region, sheet);
		sheet.addMergedRegion(region);
		firstCell.setCellStyle(cellstyle);

		// 创建第一行
		XSSFRow row = sheet.createRow(3);
		XSSFCell cell = null;
		XSSFCellStyle row1 = workbook.createCellStyle();
		row1.setBorderBottom(BorderStyle.THIN); // 下边框
		row1.setBorderLeft(BorderStyle.THIN);// 左边框
		row1.setBorderTop(BorderStyle.THIN);// 上边框
		row1.setBorderRight(BorderStyle.THIN);// 右边框
		row1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		row1.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);

		// 插入第一行数据 id
		for (int i = 0; i < title.length; i++) {
			// 创建一行的一格
			cell = row.createCell(i);
			// 赋值
			cell.setCellValue(title[i]);
			cell.setCellStyle(cellstyle);
			cell.setCellStyle(row1);
		}

		// 追加数据行数
		@SuppressWarnings("unused")
		int j = 1;
		XSSFCellStyle row2 = workbook.createCellStyle();
		row2.setBorderLeft(BorderStyle.THIN);
		row2.setBorderRight(BorderStyle.THIN);
		row2.setBorderBottom(BorderStyle.THIN);
		HashMap<String, Object> list = null;
		for (int i = 0; i < lists.size(); i++) {

			// 从集合中得到一个对象
			list = lists.get(i);

			// 创建第2行
			XSSFRow nextrow = sheet.createRow(i + 4);

			// 创建第1列并赋值
			XSSFCell cessk = nextrow.createCell(0);
			cessk.setCellValue((Long) list.get("id"));
			cessk.setCellStyle(row2);
			cessk = nextrow.createCell(1);
			cessk.setCellValue((String) list.get("username"));
			cessk.setCellStyle(row2);
			cessk = nextrow.createCell(2);
			cessk.setCellValue((String) list.get("password"));
			cessk.setCellStyle(row2);
			cessk = nextrow.createCell(3);
			cessk.setCellValue((String) list.get("phone"));
			cessk.setCellStyle(row2);
			cessk = nextrow.createCell(4);
			cessk.setCellValue((String) list.get("email"));
			cessk.setCellStyle(row2);
			cessk = nextrow.createCell(5);
			cessk.setCellValue((String) list.get("idcard"));
			cessk.setCellStyle(row2);
			cessk = nextrow.createCell(6);
			cessk.setCellValue((String) list.get("address"));
			cessk.setCellStyle(row2);
			cessk = nextrow.createCell(7);
			cessk.setCellValue((String) list.get("sex"));
			cessk.setCellStyle(row2);
			cessk = nextrow.createCell(8);
			cessk.setCellValue((String) list.get("department"));
			cessk.setCellStyle(row2);
			cessk = nextrow.createCell(9);
			cessk.setCellValue((String) list.get("education"));
			cessk.setCellStyle(row2);
			cessk = nextrow.createCell(10);
			cessk.setCellValue((String) list.get("fork"));
			cessk.setCellStyle(row2);
			j++;
		}

		// 判断文件名是否存在
		if (path.equals("")) {
			response.getWriter().write("失败：参数为空！");
			return;
		}

		// 设置客户端响应数据类型
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=utf-8");// 自定义路径
		response.setHeader("Content-disposition", "attachment;filename=" + new String((path).getBytes(), "iso-8859-1"));

		// 读取文件绝对路径，输出文件
		OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
			workbook.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (IOException e) {
		}
	}

}
