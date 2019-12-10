package com.wufan.utils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {
	private final static String excel2003L = ".xls"; //2003版本
	private final static String excel2007U = ".xlsx"; //2007版本
	
	/**
	 * 获取IO流数据，组装List Object参数集合
	 * @param in
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public List<List<Object>> getUserListByExcel(InputStream in ,String fileName) throws Exception{
		List<List<Object>> list = null;
		
		//创建Excel表
		Workbook work = this.getWorkbook(in,fileName);
		if(null == work) {
			throw new Exception("创建excel表格为空");
		}
	
		Sheet sheet = null; //页数
		Row row = null; //行数
		Cell cell = null; //列数
		
		list = new ArrayList<List<Object>>();
		
		//遍历所有页数
		for(int i = 0;i < work.getNumberOfSheets();i++) {
			sheet = work.getSheetAt(i);
			if(sheet == null) {
				continue;
			}
			//遍历当前页所有行
			for(int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum();j++) {
				row = sheet.getRow(j);
				if(row == null || row.getFirstCellNum() ==j) {
					continue;
				}
				//遍历所有列
				List<Object> li = new ArrayList<Object>();
				for(int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					li.add(this.getValue(cell));
				}
				list.add(li);
			}
		}
		return list;
		
	}
	
	/**
	 * 根据文件后缀，匹配上传版本
	 * @param in
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	private Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
		// TODO Auto-generated method stub
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if(excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr); //2003
		}else if(excel2007U.equals(fileType)) {
			wb = new XSSFWorkbook(inStr); //2007
		}else {
			throw new Exception("解析文件格式有误！");
		}
		return wb;
	}
	
	/**
	 * 对表格中数值进行格式化
	 * @param cell
	 * @return
	 */
	//解决excel 类型问题，获得数值
	@SuppressWarnings("deprecation")
	private Object getValue(Cell cell) {
		// TODO Auto-generated method stub
		String value = "";
		if(null == cell) {
		return value;
	}
	switch(cell.getCellType()) {
	//数值型
	case Cell.CELL_TYPE_NUMERIC:
	if(HSSFDateUtil.isCellDateFormatted(cell)) {
		//如果是日期类型则获取该列cell的date值
		Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		value = format.format(date);
	}else {
		//纯数字类型
		BigDecimal big = new BigDecimal(cell.getNumericCellValue());
		value = big.toString();
		//去掉整数的小数位
		if(null !=value&&!"".equals(value.trim())) {
			String[] item = value.split("[.]");
			if(1<item.length&&"0".equals(item[1])) {
				value = item[0];
			}
		}
	}
	break;
	//字符串类型
	case Cell.CELL_TYPE_STRING:
		value = cell.getStringCellValue().toString();
		break;
		//公式类型
	case Cell.CELL_TYPE_FORMULA:
		value = String.valueOf(cell.getNumericCellValue());
		//如果获取数据值为非法值，转换为获取字符串
		if(value.equals("NaN")) {
			value = cell.getStringCellValue().toString();
		}
		break;
		//布尔类型
	case Cell.CELL_TYPE_BOOLEAN:
		value = ""+ cell.getBooleanCellValue();
		break;
		default:
			value = cell.getStringCellValue().toString();
	}
	if("null".endsWith(value.trim())) {
		value = "";
	}
	return value;
	}

}
