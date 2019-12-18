package com.wufan.commons.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class PDFUtils extends PdfPageEventHelper {

	private Phrase leftHeader;
	private Phrase rigntHeader;

	public static final int marginX = 25;
	public static final int marginY = 25;

	private static BaseFont baseFont;
	// 生成下划线空白占位符
	private static String Blank;
	// 页眉字体
	private static Font font;
	// 下划线字体
	private static Phrase blankPhrase;

	public PDFUtils(String[] header) {
		this.leftHeader = new Phrase(header[0], PDFUtils.font);
		this.rigntHeader = new Phrase(header[1], PDFUtils.font);
	}

	static {
		try {
			// 设置中文字体
			baseFont = BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 168; i++) {
				sb.append("_");
			}
			Blank = sb.toString();
			font = new Font(PDFUtils.baseFont, 11, Font.UNDEFINED);
			blankPhrase = new Phrase(PDFUtils.Blank, new Font(PDFUtils.baseFont, Font.DEFAULTSIZE, Font.UNDERLINE));
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成页眉页脚页码
	 * 
	 * @param writer
	 * @param document
	 */
	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		int yMargin = -10;
		float top = document.top(yMargin);
		// 第一页不生成页眉页脚
		if (document.getPageNumber() == 0) {
			return;
		}
		// 生成下划线，使用空格占位
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, PDFUtils.blankPhrase, document.left(),
				top, 0);
		// 生成左侧页眉
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, leftHeader, document.left(), top, 0);
		// 生成右侧页眉
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, rigntHeader, document.right(), top,
				0);
		// 生成页脚页数
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
				new Phrase(String.valueOf(document.getPageNumber()), PDFUtils.font), document.right(),
				document.bottomMargin(), 0);
	}

	/**
	 * 添加水印
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @param waterMarkName
	 * @return
	 */
	public static boolean waterMark(String inputFile, String outputFile, String waterMarkName) {
		try {
			PdfReader reader = new PdfReader(inputFile);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));
			// 设置中文字体
			BaseFont base = BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);// 使用系统字体
			int total = reader.getNumberOfPages() + 1;

			PdfContentByte under;
			Rectangle pageRect = null;
			for (int i = 1; i < total; i++) {
				pageRect = stamper.getReader().getPageSizeWithRotation(i);

				// 获得PDF最顶层
				under = stamper.getOverContent(i);
				under.saveState();

				// 设置透明度
				PdfGState gs = new PdfGState();
				gs.setFillOpacity(0.4f);
				under.setGState(gs);
				under.beginText();
				under.setFontAndSize(base, 35);
				under.setTextMatrix(30, 30);
				under.setColorFill(BaseColor.GRAY);

				// 水印文字成45度角倾斜
				// 计算水印X,Y坐标(单个)
				float x = pageRect.getWidth() / 10;
				float y = pageRect.getHeight() / 10 - 10;
				under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, x, y, 45);

				// 添加水印文字
				under.endText();
				under.setLineWidth(1f);
				under.stroke();
			}
			stamper.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}