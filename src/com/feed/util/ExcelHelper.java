package com.feed.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

	public static String getContent(XSSFCell xssfCell){
		String value;
		switch (xssfCell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC: // 数字
			value = xssfCell.getNumericCellValue() + "";
			if (HSSFDateUtil.isCellDateFormatted(xssfCell)) {
				Date date = xssfCell.getDateCellValue();
				if (date != null) {
					value = new SimpleDateFormat("yyyy-MM-dd").format(date);
				} else {
					value = "";
				}
			} else {
				value = new DecimalFormat("0").format(xssfCell.getNumericCellValue());
			}
			break;
		case HSSFCell.CELL_TYPE_STRING: // 字符串
			value = xssfCell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
			value = xssfCell.getBooleanCellValue() + "";
			break;
		case HSSFCell.CELL_TYPE_FORMULA: // 公式
			value = xssfCell.getCellFormula() + "";
			break;
		case HSSFCell.CELL_TYPE_BLANK: // 空值
			value = "null";
			break;
		case HSSFCell.CELL_TYPE_ERROR: // 故障
			value = "非法字符";
			break;
		default:
			value = "未知类型";
			break;
		}
		return value;
	}
	
}
