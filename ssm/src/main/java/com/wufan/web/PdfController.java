package com.wufan.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.wufan.domain.User;
import com.wufan.service.FileService;
import com.wufan.utils.PDFUtils;

@Controller
@RequestMapping(value = "pdf")
public class PdfController {

	@Autowired
	private FileService fileService;

	/**
	 * 用户信息导出为Pdf
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws DocumentException
	 */
	@RequestMapping(value = "pdfExport", method = RequestMethod.GET)
	@ResponseBody
	public void PdfPrint(HttpServletRequest request, HttpServletResponse response)
			throws IOException, DocumentException {

		// 创建当前时间
		Date date = new Date();
		DateFormat nomaldate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String dateStr = nomaldate.format(date);

		// 创建一个文件目录
		String filePath = "D:/songw/";
		// 创建文件名
		String fileName = "用户列表" + dateStr + ".pdf";
		// 创建文件路径
		String fullPath = filePath + fileName;

		// 水印添加后路径
		String LastPath = "D:/songw/用户列表水印版" + dateStr + ".pdf";

		// 创建文件
		Document document = new Document(PageSize.A4, PDFUtils.marginX, PDFUtils.marginX, PDFUtils.marginY, PDFUtils.marginY);

		// 建立书写器
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fullPath));

		writer.setPageEvent(new PDFUtils(new String[]{"融通科技",dateStr}));
		
		// 打开文件
		document.open();

		/*
		 * // 参数 1.线宽度 2.直线长度，是个百分百，0-100之间 3.直线颜色 4.直线位置 5.上下移动位置 document.add(new
		 * LineSeparator(4f, 100, BaseColor.GRAY, Element.ALIGN_CENTER, -15f)); // 画线
		 * document.add(new LineSeparator(1f, 100, BaseColor.GRAY, Element.ALIGN_CENTER,
		 * -20f));
		 */
		
		// 中文字体，解决中文不能显示问题
		BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H,
				BaseFont.NOT_EMBEDDED);

		// 标题字体风格
		Font titleFont = new Font(bfChinese, 20, Font.BOLD);
		Font listFont = new Font(bfChinese, 10, Font.BOLD);

		// 标题内容
		Paragraph title = new Paragraph("用户信息表", titleFont);
		title.setSpacingBefore(20); // 离上一段落（标题）空的行数
		title.setAlignment(Element.ALIGN_CENTER); // 设置标题格式对齐方式
		document.add(title); // // 文档中加入标题字段

		// 创建11列的表格
		PdfPTable table = new PdfPTable(11);
		table.setWidthPercentage(100); // 宽度100%填充
		table.setSpacingBefore(10f); // 前间距
		table.setSpacingAfter(10f); // 后间距
		table.getDefaultCell().setMinimumHeight(30);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		List<PdfPRow> listRow = table.getRows();

		PdfPCell cells1[] = new PdfPCell[11];
		PdfPRow row1 = new PdfPRow(cells1);

		// 单元格
		cells1[0] = new PdfPCell(new Paragraph("ID", listFont));
		cells1[0].setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cells1[0].setBackgroundColor(BaseColor.GRAY);
		cells1[0].setMinimumHeight(20);

		cells1[1] = new PdfPCell(new Paragraph("用户名", listFont));
		cells1[1].setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cells1[1].setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cells1[1].setBackgroundColor(BaseColor.GRAY);
		cells1[1].setMinimumHeight(20);

		cells1[2] = new PdfPCell(new Paragraph("密码", listFont));
		cells1[2].setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cells1[2].setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cells1[2].setBackgroundColor(BaseColor.GRAY);
		cells1[2].setMinimumHeight(20);

		cells1[3] = new PdfPCell(new Paragraph("手机号", listFont));
		cells1[3].setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cells1[3].setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cells1[3].setBackgroundColor(BaseColor.GRAY);
		cells1[3].setMinimumHeight(20);

		cells1[4] = new PdfPCell(new Paragraph("邮箱号", listFont));
		cells1[4].setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cells1[4].setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cells1[4].setBackgroundColor(BaseColor.GRAY);
		cells1[4].setMinimumHeight(20);

		cells1[5] = new PdfPCell(new Paragraph("身份证号", listFont));
		cells1[5].setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cells1[5].setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cells1[5].setBackgroundColor(BaseColor.GRAY);
		cells1[5].setMinimumHeight(20);

		cells1[6] = new PdfPCell(new Paragraph("籍贯", listFont));
		cells1[6].setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cells1[6].setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cells1[6].setBackgroundColor(BaseColor.GRAY);
		cells1[6].setMinimumHeight(20);

		cells1[7] = new PdfPCell(new Paragraph("性别", listFont));
		cells1[7].setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cells1[7].setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cells1[7].setBackgroundColor(BaseColor.GRAY);
		cells1[7].setMinimumHeight(20);

		cells1[8] = new PdfPCell(new Paragraph("部门", listFont));
		cells1[8].setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cells1[8].setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cells1[8].setBackgroundColor(BaseColor.GRAY);
		cells1[8].setMinimumHeight(20);

		cells1[9] = new PdfPCell(new Paragraph("学历", listFont));
		cells1[9].setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cells1[9].setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cells1[9].setBackgroundColor(BaseColor.GRAY);
		cells1[9].setMinimumHeight(20);

		cells1[10] = new PdfPCell(new Paragraph("民族", listFont));
		cells1[10].setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
		cells1[10].setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
		cells1[10].setBackgroundColor(BaseColor.GRAY);
		cells1[10].setMinimumHeight(20);

		// 把表头行添加到集合
		listRow.add(row1);

		// 接收数据库的查询遍历出来的数据
		List<User> userList = new ArrayList<User>();

		// 将数据库查询到的数据存储到集合中
		userList = fileService.showAll();

		// 向表格填充list集合数据
		for (int i = 0; i < userList.size(); i++) {
			User pdf = userList.get(i);
			table.addCell(new Paragraph(String.valueOf(i + 1), listFont));
			table.addCell(new Paragraph(pdf.getUsername(), listFont));
			table.addCell(new Paragraph(pdf.getPassword(), listFont));
			table.addCell(new Paragraph(pdf.getPhone(), listFont));
			table.addCell(new Paragraph(pdf.getEmail(), listFont));
			table.addCell(new Paragraph(pdf.getIdcard(), listFont));
			table.addCell(new Paragraph(pdf.getAddress(), listFont));
			table.addCell(new Paragraph(pdf.getSex(), listFont));
			table.addCell(new Paragraph(pdf.getDepartment(), listFont));
			table.addCell(new Paragraph(pdf.getEducation(), listFont));
			table.addCell(new Paragraph(pdf.getFork(), listFont));
		}

		// 把表格添加到文件中
		document.add(table);

		// 关闭文档
		document.close();

		// 关闭书写器
		writer.close();

		// 添加水印
		PDFUtils.waterMark(fullPath, LastPath, "融通科技版权所有,未经许可不得用于商业用途");

		// 新建文件
		File f = new File(LastPath);

		// 判断文件是否存在
		if (!f.exists()) {
			response.getWriter().write("文件不存在！");
			return;
		}

		// 解决中文名称乱码问题
		final String userAgent = request.getHeader("USER-AGENT");
		if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("Edge")) { // IE浏览器
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} else if (userAgent.contains("Mozilla")) { // google,火狐浏览器
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} else {
			fileName = URLEncoder.encode(fileName, "UTF-8"); // 其他浏览器
		}

		// 设置客户端响应数据类型
		response.setContentType("application/pdf;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

		// 读取文件绝对路径，输出文件
		try (FileInputStream fileInputStream = new FileInputStream(LastPath)) {
			OutputStream outputStream = response.getOutputStream();
			byte[] b = new byte[1024];
			int j;
			while ((j = fileInputStream.read(b)) > 0) {
				outputStream.write(b, 0, j);
			}
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
