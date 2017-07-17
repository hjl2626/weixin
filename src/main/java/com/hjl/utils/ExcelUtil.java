package com.hjl.utils;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by hjl on 2016/12/26.
 */
public final class ExcelUtil {



	public static void writeExcel(String path ,Map<String, Object> colTitle, List<Map<String, Object>> data, String sheetTitle) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetTitle);


		XSSFCellStyle titleStyle = workbook.createCellStyle();
		Font font1 = workbook.createFont();
		font1.setBold(true);
		font1.setFontName("宋体");
		font1.setFontHeightInPoints((short)14);
		titleStyle.setFont(font1);


		XSSFRow row0 = sheet.createRow(0);
		int index = 0;
		List<String> colKeyList = new ArrayList<String>();
		Iterator<Map.Entry<String, Object>> ir = colTitle.entrySet().iterator();
		while (ir.hasNext()) {
			XSSFCell cell = row0.createCell(index);
			Map.Entry<String, Object> cellMap = ir.next();
			cell.setCellValue(String.valueOf(cellMap.getValue()));
			cell.setCellStyle(titleStyle);
			colKeyList.add(cellMap.getKey());
			index++;
		}
		for (int i = 0; i < data.size(); ++i) {
			XSSFRow row = sheet.createRow(i + 1);
			row.setHeightInPoints((short) 20);
			Map<String, Object> rowData = data.get(i);
			for (int j = 0; j < colKeyList.size(); ++j) {
				XSSFCell cell = row.createCell(j);
				cell.setCellValue(rowData.get(colKeyList.get(j)) == null ? "" : String.valueOf(rowData.get(colKeyList.get(j))));
			}
		}

		for (int i = 0; i < colTitle.size(); i++) {
			sheet.autoSizeColumn((short)i);
		}
		FileOutputStream out = new FileOutputStream(new File(path));
		workbook.write(out);
	}

	public static void main(String[] args) throws IOException {

		String filePath = "sample.xlsx";//文件路径
		XSSFWorkbook workbook = new XSSFWorkbook();//创建Excel文件(Workbook)
		XSSFSheet sheet = workbook.createSheet("Test");//创建工作表(Sheet)
		FileOutputStream out = new FileOutputStream(filePath);
		workbook.write(out);//保存Excel文件
		out.close();//关闭文件流
		System.out.println("OK!");
	}

}
