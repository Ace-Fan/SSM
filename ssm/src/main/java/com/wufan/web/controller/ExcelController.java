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
	 * ����excel�û���Ϣ
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
	 * �û���Ϣ����excel
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

		// ����ʱ�亯��
		Date date = new Date();
		DateFormat nomaldate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = nomaldate.format(date);
		String s = "�û���Ϣ�б�";

		// ����һ���ļ���
		String path = s + "-" + dateStr + ".xlsx";

		// �����ݿ��в������
		List<HashMap<String, Object>> lists = fileService.ExcelOut(user);
		String[] title = { "ID", "�û���","����","�ֻ���", "�����", "���֤��", "����", "�Ա�", "����", "ѧ��", "����" };// ����EXCEL�ĵ�һ�еı���ͷ���ģ�

		// ����excel������
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();

		// ����һ��������sheet
		XSSFSheet sheet = workbook.createSheet("��Ϣ��1");
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

		// ������ͷ
		XSSFRow firstRow = sheet.createRow(0);
		XSSFCell firstCell = firstRow.createCell(0);
		XSSFCellStyle cellstyle = workbook.createCellStyle();
		firstCell.setCellValue("�û���Ϣ��");
		XSSFFont font = workbook.createFont();
		cellstyle.setFont(font);
		font.setFontHeightInPoints((short) 16);// �����ֺ�
		cellstyle.setAlignment(HorizontalAlignment.CENTER); // ˮƽ����
		cellstyle.setVerticalAlignment(VerticalAlignment.CENTER); // ��ֱ����
		CellRangeAddress region = new CellRangeAddress(0, 2, 0, 10);
		RegionUtil.setBorderBottom(1, region, sheet);
		RegionUtil.setBorderLeft(1, region, sheet);
		RegionUtil.setBorderTop(1, region, sheet);
		RegionUtil.setBorderRight(1, region, sheet);
		sheet.addMergedRegion(region);
		firstCell.setCellStyle(cellstyle);

		// ������һ��
		XSSFRow row = sheet.createRow(3);
		XSSFCell cell = null;
		XSSFCellStyle row1 = workbook.createCellStyle();
		row1.setBorderBottom(BorderStyle.THIN); // �±߿�
		row1.setBorderLeft(BorderStyle.THIN);// ��߿�
		row1.setBorderTop(BorderStyle.THIN);// �ϱ߿�
		row1.setBorderRight(BorderStyle.THIN);// �ұ߿�
		row1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		row1.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);

		// �����һ������ id
		for (int i = 0; i < title.length; i++) {
			// ����һ�е�һ��
			cell = row.createCell(i);
			// ��ֵ
			cell.setCellValue(title[i]);
			cell.setCellStyle(cellstyle);
			cell.setCellStyle(row1);
		}

		// ׷����������
		@SuppressWarnings("unused")
		int j = 1;
		XSSFCellStyle row2 = workbook.createCellStyle();
		row2.setBorderLeft(BorderStyle.THIN);
		row2.setBorderRight(BorderStyle.THIN);
		row2.setBorderBottom(BorderStyle.THIN);
		HashMap<String, Object> list = null;
		for (int i = 0; i < lists.size(); i++) {

			// �Ӽ����еõ�һ������
			list = lists.get(i);

			// ������2��
			XSSFRow nextrow = sheet.createRow(i + 4);

			// ������1�в���ֵ
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

		// �ж��ļ����Ƿ����
		if (path.equals("")) {
			response.getWriter().write("ʧ�ܣ�����Ϊ�գ�");
			return;
		}

		// ���ÿͻ�����Ӧ��������
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=utf-8");// �Զ���·��
		response.setHeader("Content-disposition", "attachment;filename=" + new String((path).getBytes(), "iso-8859-1"));

		// ��ȡ�ļ�����·��������ļ�
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
