package org.sec.application.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ExcelTest {

    public static void main(String[] args) {
        new ExcelTest().write();
    }

    private Map<String, List<Integer>> getData() {
		Random r = new Random();
        Map<String, List<Integer>> data = new LinkedHashMap<>();
        for (int i = 0; i < 100000; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                list.add(j * r.nextInt(10000));
            }
            data.put("line" + (i + 1), list);
        }
        return data;
    }

    private Workbook getExcel() {
		Map<String, List<Integer>> data = getData();
		Workbook wb = new SXSSFWorkbook(1000);// new HSSFWorkbook();
		for (int i = 0; i < 20; i++) {
			Sheet sheet = wb.createSheet("BIG DATA TEST" + i);
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setBold(true);
			style.setFont(font);
			Row head = sheet.createRow(0);
			Cell cell0 = head.createCell(0);
			cell0.setCellValue("NAME");
			cell0.setCellStyle(style);
			sheet.setColumnWidth(0, 4 * 2 * 256);
			int index = 1;
			String[] catalogs = {"Col1", "Col2", "Col3", "Col4", "Col5", "Col6", "Col7", "Col8", "Col9", "Col10"};
			for (String catalog : catalogs) {
				sheet.setColumnWidth(index, catalog.getBytes().length * 2 * 256);
				Cell cell = head.createCell(index++);
				cell.setCellValue(catalog);
				cell.setCellStyle(style);
			}
			index = 1;            
			for (String key : data.keySet()) {
				Row row = sheet.createRow(index++);
				row.createCell(0).setCellValue(key);
				int tmpIndex = 1;
				for (Integer value : data.get(key)) {
					row.createCell(tmpIndex++).setCellValue(value);
				}
			}
		}
        return wb;
    }

    public void write() {
        try (OutputStream out = Files.newOutputStream(Paths.get("test.xlsx"))) {
            Workbook wb = getExcel();
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
