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
	private final static String excel2003L = ".xls"; //2003�汾
	private final static String excel2007U = ".xlsx"; //2007�汾
	
	/**
	 * ��ȡIO�����ݣ���װList Object��������
	 * @param in
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public List<List<Object>> getUserListByExcel(InputStream in ,String fileName) throws Exception{
		List<List<Object>> list = null;
		
		//����Excel��
		Workbook work = this.getWorkbook(in,fileName);
		if(null == work) {
			throw new Exception("����excel���Ϊ��");
		}
	
		Sheet sheet = null; //ҳ��
		Row row = null; //����
		Cell cell = null; //����
		
		list = new ArrayList<List<Object>>();
		
		//��������ҳ��
		for(int i = 0;i < work.getNumberOfSheets();i++) {
			sheet = work.getSheetAt(i);
			if(sheet == null) {
				continue;
			}
			//������ǰҳ������
			for(int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum();j++) {
				row = sheet.getRow(j);
				if(row == null || row.getFirstCellNum() ==j) {
					continue;
				}
				//����������
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
	 * �����ļ���׺��ƥ���ϴ��汾
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
			throw new Exception("�����ļ���ʽ����");
		}
		return wb;
	}
	
	/**
	 * �Ա������ֵ���и�ʽ��
	 * @param cell
	 * @return
	 */
	//���excel �������⣬�����ֵ
	@SuppressWarnings("deprecation")
	private Object getValue(Cell cell) {
		// TODO Auto-generated method stub
		String value = "";
		if(null == cell) {
		return value;
	}
	switch(cell.getCellType()) {
	//��ֵ��
	case Cell.CELL_TYPE_NUMERIC:
	if(HSSFDateUtil.isCellDateFormatted(cell)) {
		//����������������ȡ����cell��dateֵ
		Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		value = format.format(date);
	}else {
		//����������
		BigDecimal big = new BigDecimal(cell.getNumericCellValue());
		value = big.toString();
		//ȥ��������С��λ
		if(null !=value&&!"".equals(value.trim())) {
			String[] item = value.split("[.]");
			if(1<item.length&&"0".equals(item[1])) {
				value = item[0];
			}
		}
	}
	break;
	//�ַ�������
	case Cell.CELL_TYPE_STRING:
		value = cell.getStringCellValue().toString();
		break;
		//��ʽ����
	case Cell.CELL_TYPE_FORMULA:
		value = String.valueOf(cell.getNumericCellValue());
		//�����ȡ����ֵΪ�Ƿ�ֵ��ת��Ϊ��ȡ�ַ���
		if(value.equals("NaN")) {
			value = cell.getStringCellValue().toString();
		}
		break;
		//��������
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
