package com.textexcel.report;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.List;

@RestController//返回浏览器数据的接口，直接返回文件、JSON等。目的是下载文件
public class ReportController {
    private EmployeeService employeeService = new EmployeeService();//构建之前定义的获取数据的工具类
    @GetMapping("/export")
    public void exportEmployeeReport(HttpServletResponse response) throws Exception {//HttpServletResponse response就是接受的浏览器数据
        List<Employee> employees = employeeService.getAllEmployees();//引用工具类方法。这里是获取固定的list，实际可以自己在对应类中构建方法来获取数据库中信息内容。
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String fileName = URLEncoder.encode("员工信息报表.xlsx", "UTF-8");//看似没啥用但很关键的一步，不然可能乱码还查不出来问题。目的是赋予名字以及设定解码（主要原因是我看到过案例，WIN和MAC的默认解码方案不同可能导致部分乱码）
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        ReportUtils.generateEmployeeReport(employees, response.getOutputStream());//调用工具类ReportUtils，生成Excel表
    }
}
