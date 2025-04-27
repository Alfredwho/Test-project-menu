package com.textexcel.report;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;//注意这个是ss才是正确的，还有个sl的，需要注意是否导错包
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.OutputStream;
import java.util.List;

public class ReportUtils {
    // 动态生成EXCEL表格，生成报表并保存
    public static void generateEmployeeReport(List<Employee>employees , OutputStream output)throws Exception {
        Workbook workbook = new XSSFWorkbook();
        //建表内容
        Sheet sheet = workbook.createSheet("员工信息");
        Row header = sheet.createRow(0);

        //因为定义了header，所以这就是低0行的列内容
        header.createCell(0).setCellValue("姓名");
        header.createCell(1).setCellValue("部门");
        header.createCell(2).setCellValue("年龄");

        int rowNum = 1;

        // 遍历数据库中的内容，然后进行填充
        for (Employee emp : employees) {
            // 每次循环创建一行
            Row row = sheet.createRow(rowNum++);//先用当前值，再自增 1

            // 填写每一列的数据
            row.createCell(0).setCellValue(emp.getName());       // 第0列：姓名
            row.createCell(1).setCellValue(emp.getDepartment()); // 第1列：部门
            row.createCell(2).setCellValue(emp.getAge());         // 第2列：年龄
        }

        // 写出Excel内容到外面的流（比如浏览器下载流）
        workbook.write(output);
        workbook.close();//记得关闭释放资源
    }
}
