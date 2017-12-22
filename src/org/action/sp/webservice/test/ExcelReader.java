package org.action.sp.webservice.test;

import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader{
public static void main(String[] args) throws IOException {
	
// 构造 XSSFWorkbook 对象，strPath 传入文件路径
        XSSFWorkbook xwb = new XSSFWorkbook("D:\\operators.xlsx");
        // 读取第一章表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);
        // 定义 row、cell
        XSSFRow row;
        XSSFCell cell;
        // 循环输出表格中的内容
        for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
                // 通过 row.getCell(j).toString() 获取单元格内容，
            	cell=row.getCell(j);
            	if(i>0&&(j==2||j==1)){
            	DecimalFormat df = new DecimalFormat("0");//使用DecimalFormat类对科学计数法格式的数字进行格式化
                String contents = df.format(cell.getNumericCellValue());
                System.out.print(contents + "\t");
            }else{
            	System.out.print(cell.getStringCellValue()+"\t");
            }
            }
            System.out.println("");
        }
        }
}